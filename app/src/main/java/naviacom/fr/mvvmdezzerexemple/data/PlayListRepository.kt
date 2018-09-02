package naviacom.fr.mvvmdezzerexemple.data

import com.bitbucket.stephenvinouze.betclicchallenge.models.Playlist
import com.bitbucket.stephenvinouze.betclicchallenge.models.Track
import io.reactivex.Completable
import io.reactivex.Observable
import naviacom.fr.mvvmdezzerexemple.utils.schedulers.BaseSchedulerProvider
import naviacom.fr.mvvmdezzerexemple.webservices.reponses.BaseResponse

class PlayListRepository(private val playListRemoteDataSource: IPlayListDataSource
                         , private val baseSchedulerProvider: BaseSchedulerProvider) : IPlayListDataSource {


    override fun fetchTracks(playlistId: Long, index: Int): Observable<BaseResponse<Track>> {
        return playListRemoteDataSource.fetchTracks(playlistId, index)
    }

    override fun fetchPlayLists(userId: Long, index: Int): Observable<BaseResponse<Playlist>> {
        return playListRemoteDataSource.fetchPlayLists(userId, index)
    }

    override fun savePlaylist(playlist: Playlist): Completable {
        return playListRemoteDataSource.savePlaylist(playlist).subscribeOn(baseSchedulerProvider.computation())
    }

    override fun saveTrack(track: Track): Completable {
        return playListRemoteDataSource.saveTrack(track).subscribeOn(baseSchedulerProvider.computation())
    }

    override fun deleteAllPlayList() {
        playListRemoteDataSource.deleteAllPlayList()
    }

    override fun deleteAllPlayTrack() {
        playListRemoteDataSource.deleteAllPlayTrack()
    }

    override fun deleteTrack(trackId: Long) {
        playListRemoteDataSource.deleteTrack(trackId)
    }

    override fun deletePlayList(playListId: Long) {
        playListRemoteDataSource.deletePlayList(playListId)
    }

    companion object {
        private var INSTANCE: PlayListRepository? = null

        /**
         * Returns the single instance of this class, creating it if necessary.
         * @param playListRemoteDataSource the backend data source
         *
         * *
         * @return the [PlayListRepository] instance
         */
        @JvmStatic
        fun getInstance(playListRemoteDataSource: IPlayListDataSource
                        , baseSchedulerProvider: BaseSchedulerProvider): PlayListRepository {
            if (INSTANCE == null) {
                synchronized(PlayListRepository::class.java) {
                    INSTANCE = PlayListRepository(playListRemoteDataSource, baseSchedulerProvider)
                }
            }
            return INSTANCE!!
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}