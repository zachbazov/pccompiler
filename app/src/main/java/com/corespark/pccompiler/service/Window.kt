package com.corespark.pccompiler.service

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.corespark.pccompiler.impl.Bar
import com.corespark.pccompiler.impl.Tab


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Window : Tab, Bar {

    val metrics = DisplayMetrics()

    var orientation: Int? = null

    var density = 0f
    private var densityDpi = 0
    private var dpi = 0
    var widthPx = 0
    private var heightPx = 0
    private var widthDp = 0f
    private var heightDp = 0f

    private var dividedWidthPx = 0

    fun measure(manager: WindowManager, metrics: DisplayMetrics) {
        manager.defaultDisplay.getMetrics(metrics)
        density = metrics.density
        densityDpi = metrics.densityDpi
        dpi = metrics.densityDpi
        widthPx = metrics.widthPixels
        heightPx = metrics.heightPixels
        widthDp = metrics.xdpi
        heightDp = metrics.ydpi
    }

    fun measureMultiDeviceDensity(width: Int, divide: Int) : Int {
        when (Window.metrics.density) {
            0.75f -> { dividedWidthPx = (width / divide) }
            1f -> { dividedWidthPx = (width / divide) }
            1.5f -> { dividedWidthPx = (width / divide) }
            2f -> { dividedWidthPx = (width / divide) }
            2.625f -> { dividedWidthPx = (width / divide) }
            3f -> { dividedWidthPx = (width / divide) }
            3.5f -> { dividedWidthPx = (width / divide) }
        }
        return dividedWidthPx
    }

    private fun determineLayoutMode(context: Context) : Int {
        orientation = context.resources.configuration.orientation
        return orientation as Int
    }

    override fun determineSpan(
        context: Context, view: View, manager: WindowManager, orientation: Int?, span: Int, complete: (Boolean) -> Unit
    ) {
        Window.determineLayoutMode(context)
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Window.measure(manager, Window.metrics)
            val params = view.layoutParams
            params.width = Window.measureMultiDeviceDensity(Window.widthPx, span)
            complete(true)
        } else {
            Window.measure(manager, Window.metrics)
            val params = view.layoutParams
            params.width = Window.measureMultiDeviceDensity(Window.widthPx, span)
            complete(false)
        }
    }
}