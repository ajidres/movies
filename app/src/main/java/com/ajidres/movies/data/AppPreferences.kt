package com.ajidres.movies.data

import android.content.SharedPreferences

const val PREF_TRACKING = "tracking"

class AppPreferences(private val preferences: SharedPreferences) {

    var tracking
        get() = preferences.getBoolean(PREF_TRACKING, false)
        set(value){ preferences.edit().putBoolean(PREF_TRACKING, value).apply() }

}