package naviacom.fr.mvvmdezzerexemple.data.remote

import io.reactivex.Completable
import io.reactivex.Completable.fromAction
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import naviacom.fr.mvvmdezzerexemple.data.IPlayListDataSource
import naviacom.fr.mvvmdezzerexemple.models.Playlist
import naviacom.fr.mvvmdezzerexemple.models.Track
import naviacom.fr.mvvmdezzerexemple.webservices.RequestManager
import naviacom.fr.mvvmdezzerexemple.webservices.reponses.BaseResponse


class PlayListRemoteDataSource : IPlayListDataSource {

    private var cachedTrack: LinkedHashMap<Long, Track> = LinkedHashMap()

    private var cachedPlayList: LinkedHashMap<Long, Playlist> = LinkedHashMap()

    override fun fetchTracks(playlistId: Long, index: Int): Observable<BaseResponse<Track>> {
        return RequestManager.playlistAPI.fetchTracks(playlistId, index)
                .subscribeOn(Schedulers.io())
    }

    override fun fetchPlayLists(userId: Long, index: Int): Observable<BaseResponse<Playlist>> {
        return RequestManager.userAPI.fetchPlayLists(userId, index)
                .subscribeOn(Schedulers.io())
    }

    override fun savePlaylist(playlist: Playlist): Completable {
        return fromAction { cachedPlayList[playlist.id] = playlist }
    }

    override fun saveTrack(track: Track): Completable {
        return fromAction { cachedTrack[track.id] = track }
    }

    override fun deleteAllPlayList() {
        cachedPlayList.clear()
    }

    override fun deleteAllPlayTrack() {
        cachedTrack.clear()
    }

    override fun deleteTrack(trackId: Long) {
        cachedTrack.remove(trackId)
    }

    override fun deletePlayList(playListId: Long) {
        cachedPlayList.remove(playListId)
    }

}