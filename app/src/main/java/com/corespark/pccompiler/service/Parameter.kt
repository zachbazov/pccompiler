package com.corespark.pccompiler.service

import android.view.View
import android.view.ViewGroup
import com.corespark.pccompiler.service.View.xdpi
import com.corespark.pccompiler.service.View.ydpi


/**
 * @author Zachy Bazov.
 * @since 19/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Parameter {

    private var px: Int = 0

    fun layoutParams(view: View, density: Float, dp: Int) {
        when (density) {
            0.75f, 1f, 1.5f, 2f, 2.625f, 3f, 3.5f -> px = (dp * (xdpi / 160)).toInt()
        }
        view.layoutParams.width = px
        view.layoutParams.height = px
    }

    fun margin(view: View, density: Float, direction: Int, dp: Int) {
        when (density) {
            0.75f, 1f, 1.5f, 2f, 2.625f, 3f, 3.5f -> px = (dp * (ydpi / 160)).toInt()
        }
        when (direction) {
            0 -> (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin = px
            1 -> (view.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = px
            2 -> (view.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = px
            3 -> (view.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = px
        }
    }

    fun decreaseHeightBy(view: View, value: Int, dp: Int) {
        view.layoutParams.height = (value - (dp * ydpi / 160)).toInt()
    }
}