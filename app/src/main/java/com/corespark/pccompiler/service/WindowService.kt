package com.corespark.pccompiler.service

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.WindowManager
import com.corespark.pccompiler.app.Compiler


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object WindowService {

    val dm = DisplayMetrics()

    var orientation: Int? = null

    var density = 0f
    var dpi = 0
    var widthPx = 0
    var heightPx = 0
    var widthDp = 0f
    var heightDp = 0f

    fun measureValues(manager: WindowManager, metrics: DisplayMetrics) {
        manager.defaultDisplay.getMetrics(metrics)
        density = metrics.density
        dpi = metrics.densityDpi
        widthPx = metrics.widthPixels
        heightPx = metrics.heightPixels
        widthDp = metrics.xdpi
        heightDp = metrics.ydpi
    }

    fun pxToDp(width: Int, density: Float) : Int {
        return (width / density).toInt()
    }

    fun determineLayoutMode(context: Context) : Int {
        orientation = context.resources.configuration.orientation
        return orientation as Int
    }
}