package com.corespark.pccompiler.service

import android.content.Context
import android.content.Intent
import com.corespark.pccompiler.activity.Auth
import com.corespark.pccompiler.activity.Compile
import com.corespark.pccompiler.activity.Menu
import com.corespark.pccompiler.activity.Workspace
import com.corespark.pccompiler.utility.ACTIVITY_AUTH
import com.corespark.pccompiler.utility.ACTIVITY_COMPILE
import com.corespark.pccompiler.utility.ACTIVITY_MENU
import com.corespark.pccompiler.utility.ACTIVITY_WORKSPACE


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Intent {

    fun launch(context: Context, state: Int) : Intent {
        var intent: Intent? = null
        when (state) {
            ACTIVITY_AUTH -> {
                intent = Intent(context, Auth::class.java)
                return intent
            }
            ACTIVITY_WORKSPACE -> {
                intent = Intent(context, Workspace::class.java)
                return intent
            }
            ACTIVITY_MENU-> {
                intent = Intent(context, Menu::class.java)
                return intent
            }
            ACTIVITY_COMPILE -> {
                intent = Intent(context, Compile::class.java)
                return intent
            }
        }
        return intent!!
    }
}