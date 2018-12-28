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
        val actionList = mutableListOf<ActionBar>()

        fun addActions(context: Context) {
            if (actionList.size < 5) {
                actionList.add(0, ActionBar(R.drawable.ic_profile_inactive, context.resources.getString(R.string.text_guest)))
                actionList.add(1, ActionBar(R.drawable.ic_menu_inactive, context.resources.getString(R.string.text_menu)))
                actionList.add(2, ActionBar(R.drawable.ic_add_inactive, context.resources.getString(R.string.text_add)))
                actionList.add(3, ActionBar(R.drawable.ic_edit_inactive, context.resources.getString(R.string.text_edit)))
                actionList.add(4, ActionBar(R.drawable.ic_remove_inactive, context.resources.getString(R.string.text_remove)))
            }
        }
    }
}