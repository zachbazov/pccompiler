package com.corespark.pccompiler.app

import android.app.Application
import com.corespark.pccompiler.model.Component
import com.corespark.pccompiler.utility.Color
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
        lateinit var queries: Query
        lateinit var colors: Color

        var cpuList = mutableListOf<Any>()
        var coolerList = mutableListOf<Any>()
    }

    override fun onCreate() {
        super.onCreate()

        initialize(applicationContext)

        preferences = SharedPreferences(applicationContext)
        colors = Color(applicationContext)

        queries = Query(0)
        queries.retrieve("CPU", cpuList)
        queries = Query(1)
        queries.retrieve("Cooler", coolerList)
    }
}
