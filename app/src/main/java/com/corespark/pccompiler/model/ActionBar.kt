package com.corespark.pccompiler.model

import android.content.Context
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 25/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class ActionBar(val image: Int, val title: String) {

    companion object {
        val list = mutableListOf<ActionBar>()

        fun add(context: Context) {
            if (list.size < 5) {
                list.add(0, ActionBar(R.drawable.ic_profile_inactive, context.resources.getString(R.string.text_guest)))
                list.add(1, ActionBar(R.drawable.ic_menu_inactive, context.resources.getString(R.string.text_menu)))
                list.add(2, ActionBar(R.drawable.ic_add_inactive, context.resources.getString(R.string.text_add)))
                list.add(3, ActionBar(R.drawable.ic_edit_inactive, context.resources.getString(R.string.text_edit)))
                list.add(4, ActionBar(R.drawable.ic_remove_inactive, context.resources.getString(R.string.text_remove)))
            }
        }
    }
}