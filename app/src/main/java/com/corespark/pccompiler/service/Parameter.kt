package com.corespark.pccompiler.service

import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewGroup


/**
 * @author Zachy Bazov.
 * @since 19/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Parameter {

    private lateinit var params: ViewGroup.LayoutParams
    private lateinit var rparams: ConstraintLayout.LayoutParams

    fun set(view: View, pixel: Int) {
        params = view.layoutParams
        params.width = pixel
        params.height = pixel
    }
}