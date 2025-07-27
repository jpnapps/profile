package com.jpn.jithish

import android.app.Application
import com.jpn.core.utils.logs.ReleaseTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/*import com.jpn.jithish.BuildConfig.DEBUG
import com.jpn.core.utils.logs.ReleaseTree*/

@HiltAndroidApp
class JpnApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree()) // logs everything
        } else {
            Timber.plant(ReleaseTree()) // custom tree — no logs or crash reporting only
        }
    }
}