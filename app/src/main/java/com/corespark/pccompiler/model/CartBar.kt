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
class CartBar(val image: Int, val title: String) {

    companion object {
        val actionList = mutableListOf<CartBar>()

        fun addActions(context: Context) {
            if (actionList.size < 3) {
                actionList.add(0, CartBar(R.drawable.ic_explore_inactive, context.getString(R.string.text_explore)))
                actionList.add(1, CartBar(R.drawable.ic_change_inactive, context.getString(R.string.text_change)))
                actionList.add(2, CartBar(R.drawable.ic_trash_inactive, context.getString(R.string.text_trash)))
            }
        }
    }
}