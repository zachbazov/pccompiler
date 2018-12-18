package com.corespark.pccompiler.impl

import android.view.View
import android.view.WindowManager


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
interface TrackController {

    fun determineTabSize(view: View, manager: WindowManager, orientation: Int?, complete: (Boolean) -> Unit)
}