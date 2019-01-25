package com.corespark.pccompiler.impl

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager


/**
 * @author Zachy Bazov.
 * @since 25/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
interface View {

    fun orientation(context: Context) : Int

    fun measure(manager: WindowManager, metrics: DisplayMetrics)

    fun measureMultipleDeviceDensity(width: Int, span: Int) : Int

    fun span(context: Context, view: View, manager: WindowManager, orientation: Int?, span: Int, complete: (Boolean) -> Unit)
}