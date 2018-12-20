package com.corespark.pccompiler.service

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import com.corespark.pccompiler.impl.Tab
import com.corespark.pccompiler.utility.*


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Window : Tab {

    val metrics = DisplayMetrics()

    var orientation: Int? = null

    var density = 0f
    private var densityDpi = 0
    private var dpi = 0
    private var widthPx = 0
    private var heightPx = 0
    private var widthDp = 0f
    private var heightDp = 0f

    private var splitWidthPx = 0

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

    private fun computeSplitWindowPx(width: Int) : Int {
        when (Window.metrics.density) {
            DENSITY_0_75 -> { splitWidthPx = (width / VALUE_2) }
            DENSITY_1 -> { splitWidthPx = (width / VALUE_2) }
            DENSITY_1_5 -> { splitWidthPx = (width / VALUE_2) }
            DENSITY_2 -> { splitWidthPx = (width / VALUE_2) }
            DENSITY_2_625 -> { splitWidthPx = (width / VALUE_2) }
            DENSITY_3 -> { splitWidthPx = (width / VALUE_2) }
            DENSITY_3_5 -> { splitWidthPx = (width / VALUE_2) }
        }
        return splitWidthPx
    }

    private fun determineLayoutMode(context: Context) : Int {
        orientation = context.resources.configuration.orientation
        return orientation as Int
    }

    override fun determineTabSize(
        context: Context, view: View, manager: WindowManager, orientation: Int?, complete: (Boolean) -> Unit
    ) {
        Window.determineLayoutMode(context)
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Window.measure(manager, Window.metrics)
            val params = view.layoutParams
            params.width = Window.computeSplitWindowPx(Window.widthPx)
            complete(true)
        } else {
            Window.measure(manager, Window.metrics)
            val params = view.layoutParams
            params.width = Window.computeSplitWindowPx(Window.widthPx)
            complete(false)
        }
    }
}