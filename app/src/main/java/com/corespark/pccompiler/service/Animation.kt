package com.corespark.pccompiler.service

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils


/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Animation {

    lateinit var animation: Animation

    fun animate(context: Context, animResId: Int, animatedView: View) {
        animation = AnimationUtils.loadAnimation(context, animResId)
        animatedView.startAnimation(animation)
    }
}