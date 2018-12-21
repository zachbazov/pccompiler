package com.corespark.pccompiler.utils

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

    val colorWhite = ContextCompat.getColor(context, R.color.colorWhite)
    val colorGray = ContextCompat.getColor(context, R.color.colorSoftGray)
}