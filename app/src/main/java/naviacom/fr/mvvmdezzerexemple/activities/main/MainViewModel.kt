package naviacom.fr.mvvmdezzerexemple.activities.main

import com.bitbucket.stephenvinouze.betclicchallenge.models.Playlist
import com.bitbucket.stephenvinouze.betclicchallenge.models.Track
import io.reactivex.Observable
import naviacom.fr.mvvmdezzerexemple.data.PlayListRepository
import naviacom.fr.mvvmdezzerexemple.utils.schedulers.BaseSchedulerProvider

class MainViewModel(private val playListRepository: PlayListRepository
                    , private val baseSchedulerProvider: BaseSchedulerProvider) {

    fun fetchPlayLists(userId: Long, page: Int = 0): Observable<List<Playlist>> {
        return playListRepository.fetchPlayLists(userId, page).map { it.data }
                .subscribeOn(baseSchedulerProvider.computation())
                .observeOn(baseSchedulerProvider.ui())
    }

    fun fetchTrack(playlistId: Long, index: Int): Observable<List<Track>> {
        return playListRepository.fetchTracks(playlistId, index).map { it.data }
                .subscribeOn(baseSchedulerProvider.computation())
                .observeOn(baseSchedulerProvider.ui())
    }

    fun deleteTrack(trackId: Long) {
        playListRepository.deleteTrack(trackId)
    }

    fun deleteAllTrack() {
        playListRepository.deleteAllPlayTrack()
    }

    fun deleteAllPlayList() {
        playListRepository.deleteAllPlayList()
    }

    fun deletePlayList(playListId: Long) {
        playListRepository.deletePlayList(playListId)
    }
}