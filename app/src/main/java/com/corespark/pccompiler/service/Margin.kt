package com.corespark.pccompiler.service

import android.view.View
import android.view.ViewGroup


/**
 * @author Zachy Bazov.
 * @since 27/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Margin {

    fun set(view: View, position: Int, margin: Int) {
        when (position) {
            0 -> {
                (view.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = margin
            }
            1 -> {
                (view.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = margin
            }
            2 -> {
                (view.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = margin
            }
            3 -> {
                (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin = margin
            }
        }
    }
}