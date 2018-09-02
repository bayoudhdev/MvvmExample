package naviacom.fr.mvvmdezzerexemple.utils.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    companion object {
        private var INSTANCE: SchedulerProvider? = null
        @JvmStatic
        fun getInstance(): SchedulerProvider {
            if (INSTANCE == null) {
                synchronized(SchedulerProvider::class.java) {
                    INSTANCE = SchedulerProvider()
                }
            }
            return INSTANCE!!
        }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}