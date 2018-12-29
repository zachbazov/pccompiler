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
class CartBar(val image: Int, val component: String, val price: String) {

    companion object {
        val list = mutableListOf<CartBar>()
        val empty = mutableListOf<EmptyBar>()

        fun add() {
            if (list.size < 3) {
                list.add(0, CartBar(R.drawable.ic_menu_inactive, "GeForce GTX Titan 1080 GTX", "$489.99"))
                list.add(1, CartBar(R.drawable.ic_menu_inactive, "GeForce GTX Titan 1080 GTX", "$489.99"))
                list.add(2, CartBar(R.drawable.ic_menu_inactive, "GeForce GTX Titan 1080 GTX", "$489.99"))
            }
        }

        fun addEmpty(context: Context) {
            if (CartBar.empty.size < 1) {
                CartBar.empty.add(0, EmptyBar(R.drawable.ic_pccompiler_inactive, context.getString(R.string.text_no_components)))
            }
        }
    }
}