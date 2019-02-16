package com.corespark.pccompiler.app

import android.app.Application
import android.os.AsyncTask
import com.corespark.pccompiler.database.Query
import com.corespark.pccompiler.model.Component
import com.corespark.pccompiler.utility.Array.Companion.componentsArray
import com.corespark.pccompiler.utility.Array.Companion.tableArray
import com.corespark.pccompiler.utility.Attribute
import com.parse.Parse
import com.parse.ParseObject


/**
 * @author Zachy Bazov.
 * @since 13/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        Parse.initialize(applicationContext)
        ParseObject.registerSubclass(Component::class.java)

        preferences = SharedPreferences(applicationContext)
        attributes = Attribute(applicationContext)

        Task().execute()
    }

    override fun onTerminate() {
        super.onTerminate()
        isSignedPresented = false
    }

    class Task : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0..11) query.fetchComponents(tableArray[i], componentsArray[i])
            return null
        }
    }

    companion object {
        lateinit var preferences: SharedPreferences
        lateinit var attributes: Attribute
        val query = Query()
        var isSignedPresented = false
    }
}
