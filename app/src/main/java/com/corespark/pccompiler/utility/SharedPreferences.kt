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

    val fileName = SHARED_PREFERENCES_FILE_NAME
    val sharedPreferences = context.getSharedPreferences(fileName, 0)

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, value)
            .apply()

    var username: String
        get() = sharedPreferences.getString(KEY_USERNAME, "")!!
        set(value) = sharedPreferences.edit().putString(KEY_USERNAME, value)
            .apply()
}