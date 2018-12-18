package com.corespark.pccompiler.utility

import android.content.Context


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class SharedPreferences(context: Context) {

    val SHARED_PREFS_FILENAME = "sharedPreferences"
    val sharedPreferences = context.getSharedPreferences(SHARED_PREFS_FILENAME, 0)

    var IS_LOGGED_IN = "isLoggedIn"
    var USERNAME = "username"

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit().putBoolean(IS_LOGGED_IN, value)
            .apply()

    var username: String
        get() = sharedPreferences.getString(USERNAME, "")!!
        set(value) = sharedPreferences.edit().putString(USERNAME, value)
            .apply()
}