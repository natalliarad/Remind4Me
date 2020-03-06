package com.natallia.radaman.remind4me

import android.content.Context
import android.preference.PreferenceManager

class AppInformation {


    fun isFirstTimeLoading(context: Context) =
        PreferenceManager.getDefaultSharedPreferences(context).getBoolean(
            APP_PREF_IS_FIRST_TIME_LOADING,
            APP_PREF_DEFAULT_IS_FIRST_TIME_LOADING
        )

    companion object {
        const val APP_PREF_IS_FIRST_TIME_LOADING = "IS_FIRST_TIME_LOADING"
        const val APP_PREF_DEFAULT_IS_FIRST_TIME_LOADING = true
    }
}