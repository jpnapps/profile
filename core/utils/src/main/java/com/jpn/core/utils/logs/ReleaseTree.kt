package com.jpn.core.utils.logs

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN) {
            // Here you can forward to crashlytics or remote logger
            Log.e("ReleaseLog", "$tag: $message", t)
        }
    }
}
