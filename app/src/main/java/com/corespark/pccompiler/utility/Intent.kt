package com.corespark.pccompiler.utility

import android.content.Context
import android.content.Intent
import com.corespark.pccompiler.activity.AuthActivity
import com.corespark.pccompiler.activity.CompileActivity
import com.corespark.pccompiler.activity.MenuActivity
import com.corespark.pccompiler.activity.WorkspaceActivity


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Intent(val context: Context) {
    val ACTIVITY_AUTH       = Intent(context, AuthActivity::class.java)
    val ACTIVITY_WORKSPACE  = Intent(context, WorkspaceActivity::class.java)
    val ACTIVITY_MENU       = Intent(context, MenuActivity::class.java)
    val ACTIVITY_COMPILE    = Intent(context, CompileActivity::class.java)
}