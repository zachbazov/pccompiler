package com.corespark.pccompiler.utility

import android.content.Context
import android.support.v4.content.ContextCompat
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 04/02/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Attribute(context: Context) {

    val colorWhite = ContextCompat.getColor(context, R.color.colorWhite)
    val colorCloud = ContextCompat.getColor(context, R.color.colorCloud)
    val colorAccent = ContextCompat.getColor(context, R.color.colorAccent)
    val colorTransparentBlack = ContextCompat.getColor(context, R.color.colorTransparentBlack)
    val colorTransparentGray = ContextCompat.getColor(context, R.color.colorTransparentGray)

    fun arrowIndicator(context: Context) : android.graphics.drawable.Drawable {
        val attr = context.theme.obtainStyledAttributes(R.style.AppTheme, intArrayOf(R.attr.homeAsUpIndicator))
        val attrResId = attr.getResourceId(0, 0)
        val drawable = context.resources.getDrawable(attrResId, context.theme)
        attr.recycle()
        return drawable
    }
}