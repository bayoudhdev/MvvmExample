package naviacom.fr.mvvmdezzerexemple.activities.splash

import android.content.Intent
import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import naviacom.fr.mvvmdezzerexemple.R
import naviacom.fr.mvvmdezzerexemple.activities.main.MainActivity
import naviacom.fr.mvvmdezzerexemple.utils.showLightStatusBar

class SplashActivity : RxAppCompatActivity() {

    companion object {
        private const val KEY_SPLASH_DELAY = 5L
    }

    private val splashViewModel = SplashViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        showLightStatusBar()
        splashViewModel.displaySplash(KEY_SPLASH_DELAY).observeOn(AndroidSchedulers.mainThread())
                .subscribe({ finishSplash() }, { t: Throwable -> t.printStackTrace() })
    }

    private fun finishSplash() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}