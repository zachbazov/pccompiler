package com.corespark.pccompiler.app

import android.content.Context
import com.corespark.pccompiler.R
import com.corespark.pccompiler.utils.KEY_IS_LOGGED_IN
import com.corespark.pccompiler.utils.KEY_USRENAME


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class SharedPreferences(context: Context) {

    private val fileName = context.getString(R.string.key_shared_preferences_file)
    private val sharedPreferences = context.getSharedPreferences(fileName, 0)!!

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, value)
            .apply()

    var username: String
        get() = sharedPreferences.getString(KEY_USRENAME, "")!!
        set(value) = sharedPreferences.edit().putString(KEY_USRENAME, value)
            .apply()
}