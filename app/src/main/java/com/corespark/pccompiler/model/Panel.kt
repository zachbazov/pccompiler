package com.corespark.pccompiler.model

import android.content.Context
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 02/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Panel {

    class ControlPanel(val image: Int, val title: String) {

        companion object {
            val list = mutableListOf<Any>()

            fun add(context: Context) {
                if (list.size < 2) {
                    list.add(0, ControlPanel(R.drawable.ic_settings_inactive, context.getString(R.string.text_settings)))
                    list.add(1, ControlPanel(R.drawable.ic_logout_inactive, context.getString(R.string.text_logout)))
                }
            }
        }
    }
}