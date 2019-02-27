package com.corespark.pccompiler.service

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.WindowManager


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object View : com.corespark.pccompiler.impl.View {

    val metrics = DisplayMetrics()

    var orientation: Int? = null

    var density = 0f
    private var densityDpi = 0
    private var dpi = 0
    private var widthPx = 0
    private var heightPx = 0
    var xdpi = 0f
    var ydpi = 0f
    private var spannedWidthPx = 0
    var spannedHeightPx = 0

    override fun orientation(context: Context) : Int {
        orientation = context.resources.configuration.orientation
        return orientation as Int
    }

    override fun measure(manager: WindowManager, metrics: DisplayMetrics) {
        manager.defaultDisplay.getMetrics(metrics)
        density = metrics.density
        densityDpi = metrics.densityDpi
        dpi = metrics.densityDpi
        widthPx = metrics.widthPixels
        heightPx = metrics.heightPixels
        xdpi = metrics.xdpi
        ydpi = metrics.ydpi
    }

    override fun measureWidthMultipleDeviceDensity(width: Int, span: Int) : Int {
        when (metrics.density) {
            0.75f, 1f, 1.5f, 2f, 2.625f, 3f, 3.5f -> spannedWidthPx = (width / span)
        }
        return spannedWidthPx
    }

    override fun measureHeightMultipleDeviceDensity(height: Int, span: Int) : Int {
        when (metrics.density) {
            0.75f, 1f, 1.5f, 2f, 2.625f, 3f, 3.5f -> spannedHeightPx = (height / span)
        }
        return spannedHeightPx
    }

    override fun widthSpan(
        context: Context, view: android.view.View, manager: WindowManager, orientation: Int?, span: Int, complete: (Boolean) -> Unit
    ) {
        View.orientation(context)
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            View.measure(manager, View.metrics)
            val params = view.layoutParams
            params.width = View.measureWidthMultipleDeviceDensity(View.widthPx, span)
            complete(true)
        } else {
            View.measure(manager, View.metrics)
            val params = view.layoutParams
            params.width = View.measureWidthMultipleDeviceDensity(View.widthPx, span)
            complete(false)
        }
    }

    override fun heightSpan(
        context: Context, view: android.view.View, manager: WindowManager, orientation: Int?, span: Int, complete: (Boolean) -> Unit
    ) {
        View.orientation(context)
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            View.measure(manager, View.metrics)
            val params = view.layoutParams
            params.height = View.measureHeightMultipleDeviceDensity(View.heightPx, span)
            complete(true)
        } else {
            View.measure(manager, View.metrics)
            val params = view.layoutParams
            params.height = View.measureHeightMultipleDeviceDensity(View.heightPx, span)
            complete(false)
        }
    }
}