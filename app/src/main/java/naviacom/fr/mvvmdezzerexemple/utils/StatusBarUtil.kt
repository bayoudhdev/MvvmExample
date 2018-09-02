package naviacom.fr.mvvmdezzerexemple.utils

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.View

fun AppCompatActivity.showLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}