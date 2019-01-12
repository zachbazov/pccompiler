package com.corespark.pccompiler.service

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.corespark.pccompiler.R.layout.*
import com.corespark.pccompiler.activity.Auth
import com.corespark.pccompiler.activity.Compile
import com.corespark.pccompiler.activity.Workspace


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Intent {

    lateinit var intent: Intent

    fun launch(context: Context, resId: Int, pipe: (Boolean) -> Unit) : Intent {
        when (resId) {
            activity_auth -> {
                intent = Intent(context, Auth::class.java)
            }
            activity_workspace -> {
                intent = Intent(context, Workspace::class.java)
            }
            activity_compile -> {
                intent = Intent(context, Compile::class.java)
            }
        }
        pipe(true)
        context.startActivity(intent)
        return intent
    }

    fun finish(context: Context) {
        (context as Activity).finish()
    }
}


