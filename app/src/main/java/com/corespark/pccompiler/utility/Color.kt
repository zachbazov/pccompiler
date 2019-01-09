package com.corespark.pccompiler.utility

import android.content.Context
import android.support.v4.content.ContextCompat
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 21/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Color(context: Context) {

    val colorPrimary = ContextCompat.getColor(context, R.color.colorPrimary)
    val colorAccent = ContextCompat.getColor(context, R.color.colorAccent)
    val colorWhite = ContextCompat.getColor(context, R.color.colorWhite)
    val colorTransparentBlack = ContextCompat.getColor(context, R.color.colorTransparentBlack)
    val colorCloud = ContextCompat.getColor(context, R.color.colorCloud)
}