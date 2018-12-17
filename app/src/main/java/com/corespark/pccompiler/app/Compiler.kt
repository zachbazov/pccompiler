package com.corespark.pccompiler.app;

import android.app.Application
import com.parse.Parse.initialize
import com.parse.ParseInstallation
import com.parse.ParseUser
import com.parse.LogInCallback
import com.parse.ParseException


/**
 * @author Zachy Bazov.
 * @since 13/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Compiler : Application() {

    override fun onCreate() {
        super.onCreate()

        initialize(applicationContext)
    }
}
