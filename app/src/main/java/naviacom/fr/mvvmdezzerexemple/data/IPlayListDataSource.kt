package naviacom.fr.mvvmdezzerexemple.data

import io.reactivex.Completable
import io.reactivex.Observable
import naviacom.fr.mvvmdezzerexemple.models.Playlist
import naviacom.fr.mvvmdezzerexemple.models.Track
import naviacom.fr.mvvmdezzerexemple.webservices.reponses.BaseResponse

interface IPlayListDataSource {

    fun fetchTracks(playlistId: Long, index: Int): Observable<BaseResponse<Track>>

    fun fetchPlayLists(userId: Long, index: Int = 0): Observable<BaseResponse<Playlist>>

    fun savePlaylist(playlist: Playlist): Completable

    fun saveTrack(playlist: Track): Completable

    fun deleteAllPlayList()

    fun deleteAllPlayTrack()

    fun deleteTrack(trackId: Long)

    fun deletePlayList(playListId: Long)
}