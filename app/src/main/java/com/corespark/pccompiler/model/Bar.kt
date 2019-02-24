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
                    list.add(0, Action(R.drawable.ic_profile_inactive, title = context.getString(R.string.text_blank)))
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
            val empty = mutableListOf<Any>()

            fun addEmpty(context: Context) {
                if (empty.size < 1)
                    empty.add(0, Empty(R.drawable.ic_pccompiler_inactive, context.getString(R.string.text_no_compilations)))
            }
        }
    }

    class Cart : com.corespark.pccompiler.model.Component() {

        companion object {
            val list = mutableListOf<Any>()
            val empty = mutableListOf<Any>()

            fun addEmpty(context: Context) {
                if (empty.size < 1)
                    empty.add(0, Empty(R.drawable.ic_pccompiler_inactive, context.getString(R.string.text_no_components)))
            }
        }
    }

    class Component(val image: Int) {

        companion object {
            val list = mutableListOf<Any>()

            fun add() {
                if (list.size < 11) {
                    list.add(Component(R.mipmap.ic_cpu))
                    list.add(Component(R.mipmap.ic_optdrive))
                    list.add(Component(R.mipmap.ic_cooler))
                    list.add(Component(R.mipmap.ic_graphiccard))
                    list.add(Component(R.mipmap.ic_motherboard))
                    list.add(Component(R.mipmap.ic_soundcard))
                    list.add(Component(R.mipmap.ic_memory))
                    list.add(Component(R.mipmap.ic_powersupply))
                    list.add(Component(R.mipmap.ic_storage))
                    list.add(Component(R.mipmap.ic_case))
                    list.add(Component(R.mipmap.ic_extstorage))
                    list.add(Component(R.mipmap.ic_opsystem))
                }
            }
        }
    }

    class Empty(val image: Int, val title: String)
}