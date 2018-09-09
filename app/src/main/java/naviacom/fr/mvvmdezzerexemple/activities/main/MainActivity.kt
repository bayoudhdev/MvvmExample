package naviacom.fr.mvvmdezzerexemple.activities.main

import android.os.Bundle
import android.os.Handler
import android.support.annotation.StringRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
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
    private val mCompositeDisposable = CompositeDisposable()
    private val mMainViewAdapter: MainViewAdapter by lazy {
        MainViewAdapter()
    }

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
        playlist_recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        playlist_recyclerview.adapter = mMainViewAdapter
    }


    override fun onResume() {
        super.onResume()
        bindViewModel()
    }

    private fun bindViewModel() {
        mCompositeDisposable.add(mMainViewModel.getLoadingIndicatorVisibility()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::progressShow))

        mCompositeDisposable.add(mMainViewModel.getSnackBarMessage()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showSnackBar))

        fetchPlayList()
    }

    /**
     * Method used to fetch list
     */
    private fun fetchPlayList() {
        mMainViewModel.fetchPlayLists(userId = userId)
                .compose(RxLifecycle.bindUntilEvent(this.lifecycle(), ActivityEvent.STOP))
                .subscribe({
                    mMainViewAdapter.playLists = it
                }, {
                    showSnackBar(R.string.error_fetching_playlists_title)
                    progressShow(false)
                })
    }

    private fun showSnackBar(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun progressShow(visibility: Boolean) {
        progress.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    private fun unbindViewModel() {
        mCompositeDisposable.dispose()
    }

    public override fun onPause() {
        unbindViewModel()
        super.onPause()
    }

    companion object {
        private const val KAY_LOG = "KAY_LOG"
    }
}
