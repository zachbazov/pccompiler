package com.corespark.pccompiler.service

import android.content.Context
import android.content.Intent
import com.corespark.pccompiler.R
import com.corespark.pccompiler.activity.Auth
import com.corespark.pccompiler.activity.Compile
import com.corespark.pccompiler.activity.Menu
import com.corespark.pccompiler.activity.Workspace


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Intent {

    fun launch(context: Context, resId: Int) : Intent {
        var intent: Intent? = null
        when (resId) {
            R.layout.activity_auth -> {
                intent = Intent(context, Auth::class.java)
                return intent
            }
            R.layout.activity_workspace -> {
                intent = Intent(context, Workspace::class.java)
                return intent
            }
            R.layout.activity_menu -> {
                intent = Intent(context, Menu::class.java)
                return intent
            }
            R.layout.activity_compile -> {
                intent = Intent(context, Compile::class.java)
                return intent
            }
        }
        return intent!!
    }
}