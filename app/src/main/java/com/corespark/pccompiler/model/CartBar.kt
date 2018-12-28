package com.corespark.pccompiler.model

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
        val cartList = mutableListOf<CartBar>()

        fun add() {
            if (cartList.size < 3) {
                cartList.add(0, CartBar(R.drawable.ic_menu_inactive, "GeForce GTX Titan 1080 GTX", "$489.99"))
                cartList.add(1, CartBar(R.drawable.ic_menu_inactive, "GeForce GTX Titan 1080 GTX", "$489.99"))
                cartList.add(2, CartBar(R.drawable.ic_menu_inactive, "GeForce GTX Titan 1080 GTX", "$489.99"))
            }
        }
    }
}