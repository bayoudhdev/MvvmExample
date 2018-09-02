package naviacom.fr.mvvmdezzerexemple.activities.splash

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashViewModel {

    fun displaySplash(duration: Long): Completable = Completable.timer(duration, TimeUnit.SECONDS).subscribeOn(Schedulers.io())

}