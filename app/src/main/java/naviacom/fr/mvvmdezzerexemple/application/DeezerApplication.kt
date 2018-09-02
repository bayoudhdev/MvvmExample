package naviacom.fr.mvvmdezzerexemple.application

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class DeezerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}