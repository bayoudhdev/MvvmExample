package naviacom.fr.mvvmdezzerexemple

import io.github.plastix.rxschedulerrule.RxSchedulerRule
import naviacom.fr.mvvmdezzerexemple.activities.splash.SplashViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashUnitTest {

    @get:Rule
    val schedulerRule = RxSchedulerRule()
    lateinit var splashViewModel: SplashViewModel

    @Before
    fun setup() {
        splashViewModel = SplashViewModel()
    }

    @Test
    @Throws(Exception::class)
    fun testSplashComplete() {
        val seconds = 2L
        val observer = splashViewModel.displaySplash(seconds).test()
        observer.assertNoErrors()
        observer.assertComplete()
    }

}