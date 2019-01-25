package com.corespark.pccompiler.app

import android.content.Context
import com.corespark.pccompiler.R
import com.corespark.pccompiler.utility.KEY_IS_AUTHENTICATED
import com.corespark.pccompiler.utility.KEY_PASSWORD
import com.corespark.pccompiler.utility.KEY_USRENAME


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class SharedPreferences(context: Context) {

    private val file = context.getString(R.string.key_shared_preferences_file)
    private val sharedPreferences = context.getSharedPreferences(file, 0)!!

    var isAuthenticated
        get() = sharedPreferences.getBoolean(KEY_IS_AUTHENTICATED, false)
        set(value) = sharedPreferences.edit().putBoolean(KEY_IS_AUTHENTICATED, value)
            .apply()

    var username
        get() = sharedPreferences.getString(KEY_USRENAME, "")!!
        set(value) = sharedPreferences.edit().putString(KEY_USRENAME, value)
            .apply()

    var password
        get() = sharedPreferences.getString(KEY_PASSWORD, "")!!
        set(value) = sharedPreferences.edit().putString(KEY_PASSWORD, value)
            .apply()
}