package naviacom.fr.mvvmdezzerexemple.webservices.apis

import io.reactivex.Observable
import naviacom.fr.mvvmdezzerexemple.models.Track
import naviacom.fr.mvvmdezzerexemple.webservices.reponses.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaylistAPI {

    @GET("playlist/{playlist_id}/tracks")
    fun fetchTracks(@Path("playlist_id") playlistId: Long, @Query("index") index: Int = 0): Observable<BaseResponse<Track>>

}