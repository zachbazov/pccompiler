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
object Bar {

    class Tab(val image: Int) {

        companion object {
            val list = mutableListOf<Any>()

            fun add() {
                if (list.size < 2) {
                    list.add(0, Tab(R.drawable.ic_workspace_inactive))
                    list.add(1, Tab(R.drawable.ic_cart_inactive))
                }
            }
        }
    }

    class Action(val image: Int, val title: String) {

        companion object {
            val list = mutableListOf<Any>()

            fun add(context: Context) {
                if (list.size < 3) {
                    list.add(0, Action(R.drawable.ic_profile_inactive, title = context.getString(R.string.app_blank)))
                    list.add(1, Action(R.drawable.ic_compile_inactive, context.getString(R.string.text_compile)))
                    list.add(2, Action(R.drawable.ic_edit_inactive, context.getString(R.string.text_edit)))
                }
            }
        }
    }

    class Control(val image: Int, val title: String) {

        companion object {
            val list = mutableListOf<Any>()

            fun add(context: Context) {
                if (list.size < 3) {
                    list.add(0, Control(R.drawable.ic_explore_inactive, context.getString(R.string.text_explore)))
                    list.add(1, Control(R.drawable.ic_change_inactive, context.getString(R.string.text_change)))
                    list.add(2, Control(R.drawable.ic_trash_inactive, context.getString(R.string.text_trash)))
                }
            }
        }
    }

    class Compilation(val id: String, val title: String, val image: Int) {

        companion object {
            val list = mutableListOf<Any>()
            val onGoingList = mutableListOf<Any>()
            val empty = mutableListOf<Any>()

            fun addEmpty(context: Context) {
                if (empty.size < 1)
                    empty.add(0, Empty(R.drawable.ic_pccompiler_inactive, context.getString(R.string.text_no_compilations)))
            }
        }
    }

    class Cart(val image: Int, val component: String, val price: String) {

        companion object {
            val list = mutableListOf<Any>()
            val empty = mutableListOf<Any>()

            fun addEmpty(context: Context) {
                if (Cart.empty.size < 1)
                    Cart.empty.add(0, Empty(R.drawable.ic_pccompiler_inactive, context.getString(R.string.text_no_components)))
            }
        }
    }

    class Component(val id: Int, val image: Int) {

        companion object {
            val list = mutableListOf<Any>()

            fun add() {
                if (list.size < 12) {
                    list.add(0, Component(0, R.mipmap.ic_cpu))
                    list.add(1, Component(1, R.mipmap.ic_optdrive))
                    list.add(2, Component(2, R.mipmap.ic_cooler))
                    list.add(3, Component(3, R.mipmap.ic_graphiccard))
                    list.add(4, Component(4, R.mipmap.ic_motherboard))
                    list.add(5, Component(5, R.mipmap.ic_soundcard))
                    list.add(6, Component(6, R.mipmap.ic_memory))
                    list.add(7, Component(7, R.mipmap.ic_powersupply))
                    list.add(8, Component(8, R.mipmap.ic_storage))
                    list.add(9, Component(9, R.mipmap.ic_case))
                    list.add(10, Component(10, R.mipmap.ic_extstorage))
                    list.add(11, Component(11, R.mipmap.ic_opsystem))
                }
            }
        }
    }

    class Empty(val image: Int, val title: String)
}