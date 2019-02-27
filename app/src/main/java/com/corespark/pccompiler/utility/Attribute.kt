package com.corespark.pccompiler.utility

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat.getColor
import android.support.v4.content.res.ResourcesCompat.getDrawable
import android.widget.ImageView
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 04/02/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Attribute(context: Context) {

    private lateinit var theme: Resources.Theme
    var drawable: Drawable? = null

    val colorWhite = getColor(context, R.color.colorWhite)
    val colorCloud = getColor(context, R.color.colorCloud)
    val colorAccent = getColor(context, R.color.colorAccent)
    val colorTransparentBlack = getColor(context, R.color.colorTransparentBlack)
    val colorTransparentGray = getColor(context, R.color.colorTransparentGray)

    fun arrowIndicator(context: Context) : Drawable {
        val attr = context.theme.obtainStyledAttributes(R.style.App, intArrayOf(R.attr.homeAsUpIndicator))
        val attrResId = attr.getResourceId(0, 0)
        val drawable = context.resources.getDrawable(attrResId, context.theme)
        attr.recycle()
        return drawable
    }

    fun setTheme(context: Context, styleRes: Int, resId: Int, view: ImageView) {
        theme = context.resources.newTheme()
        when (styleRes) {
            R.style.App -> theme.applyStyle(styleRes, false)
            R.style.Vector -> theme.applyStyle(styleRes, false)
            R.style.VectorHighlight -> theme.applyStyle(styleRes, false)
        }
        drawable = getDrawable(context.resources, resId, theme)
        view.setImageDrawable(drawable)
    }
}