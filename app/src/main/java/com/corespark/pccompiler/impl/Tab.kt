package com.corespark.pccompiler.impl

import android.content.Context
import android.view.View
import android.view.WindowManager


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
interface Tab {

    fun span(context: Context, view: View, manager: WindowManager, orientation: Int?, span: Int, complete: (Boolean) -> Unit)
}