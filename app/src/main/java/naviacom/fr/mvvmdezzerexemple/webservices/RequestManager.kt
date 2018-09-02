package naviacom.fr.mvvmdezzerexemple.webservices

import com.bitbucket.stephenvinouze.betclicchallenge.webservices.interceptors.HeaderInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import naviacom.fr.mvvmdezzerexemple.BuildConfig
import naviacom.fr.mvvmdezzerexemple.webservices.apis.PlaylistAPI
import naviacom.fr.mvvmdezzerexemple.webservices.apis.UserAPI
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestManager{


    val userAPI: UserAPI by lazy { retrofit.create(UserAPI::class.java) }
    val playlistAPI: PlaylistAPI by lazy { retrofit.create(PlaylistAPI::class.java) }

    private val retrofit: Retrofit

    init {
        val gson = GsonBuilder().create()
        retrofit= Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(buildHttpClient(HeaderInterceptor()).build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
    private fun buildHttpClient(headerInterceptor: Interceptor): OkHttpClient.Builder {
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(loggerInterceptor)
    }
}