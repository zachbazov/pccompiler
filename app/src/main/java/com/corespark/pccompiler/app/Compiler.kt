package com.corespark.pccompiler.app

import android.app.Application
import com.corespark.pccompiler.utils.Color
import com.parse.Parse.initialize


/**
 * @author Zachy Bazov.
 * @since 13/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Compiler : Application() {

    companion object {
        lateinit var preferences: SharedPreferences
        lateinit var colors: Color
    }

    override fun onCreate() {
        super.onCreate()

        initialize(applicationContext)

        preferences = SharedPreferences(applicationContext)
        colors = Color(applicationContext)
    }
}
