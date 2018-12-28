package com.corespark.pccompiler.model

import android.content.Context
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 28/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class ControlPanel(val image: Int, val title: String) {

    companion object {
        val controlList = mutableListOf<ControlPanel>()

        fun addControls(context: Context) {
            if (controlList.size < 2) {
                controlList.add(0, ControlPanel(R.drawable.ic_settings_inactive, context.getString(R.string.text_settings)))
                controlList.add(1, ControlPanel(R.drawable.ic_logout_inactive, context.getString(R.string.text_logout)))
            }
        }
    }
}