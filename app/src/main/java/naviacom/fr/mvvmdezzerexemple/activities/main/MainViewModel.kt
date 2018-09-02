package naviacom.fr.mvvmdezzerexemple.activities.main

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import naviacom.fr.mvvmdezzerexemple.R
import naviacom.fr.mvvmdezzerexemple.data.PlayListRepository
import naviacom.fr.mvvmdezzerexemple.models.Playlist
import naviacom.fr.mvvmdezzerexemple.models.Track
import naviacom.fr.mvvmdezzerexemple.utils.schedulers.BaseSchedulerProvider


class MainViewModel(private val playListRepository: PlayListRepository
                    , private val baseSchedulerProvider: BaseSchedulerProvider) {

    private val mLoadingIndicatorSubject  = BehaviorSubject.createDefault(false)
    private val mSnackBarText: PublishSubject<Int> = PublishSubject.create()


    fun fetchPlayLists(userId: Long, page: Int = 0): Observable<List<Playlist>> {
        return playListRepository.fetchPlayLists(userId, page).map { it.data }
                .doOnSubscribe { mLoadingIndicatorSubject.onNext(true) }
                .doOnNext { mLoadingIndicatorSubject.onNext(false)}
                .doOnError { mSnackBarText.onNext(R.string.error_fetching_playlists_title) }
                .subscribeOn(baseSchedulerProvider.computation())
                .observeOn(baseSchedulerProvider.ui())
    }

    fun fetchTrack(playlistId: Long, index: Int): Observable<List<Track>> {
        return playListRepository.fetchTracks(playlistId, index).map { it.data }
                .doOnSubscribe { mLoadingIndicatorSubject.onNext(true) }
                .doOnNext { mLoadingIndicatorSubject.onNext(false)}
                .doOnError { mSnackBarText.onNext(R.string.error_fetching_tracks_title) }
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

    /**
     * @return a stream of string ids that should be displayed in the snackbar.
     */

    fun getSnackBarMessage(): Observable<Int> {
        return mSnackBarText
    }

    /**
     * @return a stream that emits true if the progress indicator should be displayed, false otherwise.
     */
    fun getLoadingIndicatorVisibility(): Observable<Boolean> {
        return mLoadingIndicatorSubject
    }
}