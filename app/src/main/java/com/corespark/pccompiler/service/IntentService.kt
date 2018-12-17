package com.corespark.pccompiler.service

import android.content.Context
import android.content.Intent
import com.corespark.pccompiler.activity.WorkspaceActivity


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class IntentService(val context: Context) {
    val ACTIVITY_WORKSPACE = Intent(context, WorkspaceActivity::class.java)
}