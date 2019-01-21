package com.corespark.pccompiler.service

import android.support.v7.widget.RecyclerView
import android.view.View
import com.corespark.pccompiler.app.Compiler


/**
 * @author Zachy Bazov.
 * @since 21/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Algorithm {

    fun style(view: View, position: Int) {
        if (position % 2 == 0) view.setBackgroundColor(Compiler.colors.colorGray)
        else view.setBackgroundColor(Compiler.colors.colorWhite)
    }

    fun focus(recycler: RecyclerView, current: Int) {
        for (position in 0..12) {
            recycler.layoutManager?.getChildAt(current)?.setBackgroundColor(Compiler.colors.colorAccent)
            recycler.layoutManager?.getChildAt(position)?.setBackgroundColor(Compiler.colors.colorCloud)
        }
    }
}