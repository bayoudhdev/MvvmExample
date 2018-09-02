package naviacom.fr.mvvmdezzerexemple.activities.main

import android.os.Bundle
import android.util.Log
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.view_toolbar.*
import naviacom.fr.mvvmdezzerexemple.BuildConfig
import naviacom.fr.mvvmdezzerexemple.R
import naviacom.fr.mvvmdezzerexemple.data.PlayListRepository
import naviacom.fr.mvvmdezzerexemple.data.remote.PlayListRemoteDataSource
import naviacom.fr.mvvmdezzerexemple.utils.schedulers.SchedulerProvider
import naviacom.fr.mvvmdezzerexemple.utils.showLightStatusBar

class MainActivity : RxAppCompatActivity() {

    private lateinit var mMainViewModel: MainViewModel
    private var userId: Long = BuildConfig.MOCK_USER_ID
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showLightStatusBar()
        setSupportActionBar(id_toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowTitleEnabled(false)
        }
        val schedulerProvider = SchedulerProvider.getInstance()
        mMainViewModel = MainViewModel(PlayListRepository
                .getInstance(PlayListRemoteDataSource(), schedulerProvider), schedulerProvider)
        fetchPlayList()
    }

    private fun fetchPlayList() {
        mMainViewModel.fetchPlayLists(userId = userId)
                .compose(RxLifecycle.bindUntilEvent(this.lifecycle(), ActivityEvent.STOP))
                .subscribe({
                    it.forEach {
                        Log.e(KAY_LOG, "title : ${it.title} , creator : ${it.creator}")
                    }
                }, { t: Throwable -> t.printStackTrace() })
    }

    companion object {
        private const val KAY_LOG = "KAY_LOG"
    }
}
