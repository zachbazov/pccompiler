package com.corespark.pccompiler.utility

import android.content.Context
import android.graphics.drawable.Drawable
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 04/02/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Attribute {

    fun homeIndicator(context: Context) : Drawable {
        val attr = context.theme.obtainStyledAttributes(R.style.AppTheme, intArrayOf(R.attr.homeAsUpIndicator))
        val attrResId = attr.getResourceId(0, 0)
        val drawable = context.resources.getDrawable(attrResId, context.theme)
        attr.recycle()
        return drawable
    }
}