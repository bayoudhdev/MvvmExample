package naviacom.fr.mvvmdezzerexemple.webservices.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*


class HeaderInterceptor : Interceptor {

    companion object {
        private const val HEADER_ACCEPT_CONTENT_TYPE = "Content-Type"
        private const val HEADER_ACCEPT_CONTENT = "Accept"
    }

    private val headers = HashMap<String, String>()

    init {
        addHeader(HEADER_ACCEPT_CONTENT_TYPE, "application/json; charset=UTF-8")
        addHeader(HEADER_ACCEPT_CONTENT, "application/json")
    }

    private fun addHeader(name: String, value: String) {
        headers[name] = value
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        for ((key, value) in headers) {
            builder.header(key, value)
        }
        val request = builder.method(original.method(), original.body()).build()
        return chain.proceed(request)
    }

}