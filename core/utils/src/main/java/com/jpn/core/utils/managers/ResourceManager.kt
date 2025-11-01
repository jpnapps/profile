package com.jpn.core.utils.managers

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes resId: Int): String
    fun getString(@StringRes resId: Int, vararg args: Any): String
}
