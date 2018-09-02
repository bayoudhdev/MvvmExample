package naviacom.fr.mvvmdezzerexemple.webservices.apis

import com.bitbucket.stephenvinouze.betclicchallenge.models.Playlist
import io.reactivex.Observable
import naviacom.fr.mvvmdezzerexemple.webservices.reponses.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserAPI {

    @GET("user/{user_id}/playlists")
    fun fetchPlayLists(@Path("user_id") userId: Long, @Query("index") index: Int = 0): Observable<BaseResponse<Playlist>>

}