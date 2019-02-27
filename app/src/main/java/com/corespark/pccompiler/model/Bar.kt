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
                    list.add(0, Tab(R.drawable.ic_workspace))
                    list.add(1, Tab(R.drawable.ic_cart))
                }
            }
        }
    }

    class Action(val image: Int, val title: String) {

        companion object {
            val list = mutableListOf<Any>()

            fun add(context: Context) {
                if (list.size < 3) {
                    list.add(0, Action(R.drawable.ic_explore, title = context.getString(R.string.text_blank)))
                    list.add(1, Action(R.drawable.ic_compile, context.getString(R.string.text_compile)))
                    list.add(2, Action(R.drawable.ic_edit, context.getString(R.string.text_edit)))
                }
            }
        }
    }

    class Control(val image: Int, val title: String) {

        companion object {
            val list = mutableListOf<Any>()

            fun add(context: Context) {
                if (list.size < 3) {
                    list.add(0, Control(R.drawable.ic_explore, context.getString(R.string.text_explore)))
                    list.add(1, Control(R.drawable.ic_clear, context.getString(R.string.text_clear_all)))
                    list.add(2, Control(R.drawable.ic_checkout, context.getString(R.string.text_checkout)))
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
                    empty.add(0, Empty(R.drawable.ic_logo, context.getString(R.string.text_no_compilations)))
            }
        }
    }

    class Cart : com.corespark.pccompiler.model.Component() {

        companion object {
            val list = mutableListOf<Any>()
            val empty = mutableListOf<Any>()

            fun addEmpty(context: Context) {
                if (empty.size < 1)
                    empty.add(0, Empty(R.drawable.ic_logo, context.getString(R.string.text_no_components)))
            }
        }
    }

    class Component(val image: Int) {

        companion object {
            val list = mutableListOf<Any>()

            fun add() {
                if (list.size < 11) {
                    list.add(Component(R.drawable.ic_cpu))
                    list.add(Component(R.drawable.ic_opt_drive))
                    list.add(Component(R.drawable.ic_cooler))
                    list.add(Component(R.drawable.ic_graphic_card))
                    list.add(Component(R.drawable.ic_motherboard))
                    list.add(Component(R.drawable.ic_sound_card))
                    list.add(Component(R.drawable.ic_memory))
                    list.add(Component(R.drawable.ic_power_supply))
                    list.add(Component(R.drawable.ic_storage))
                    list.add(Component(R.drawable.ic_case))
                    list.add(Component(R.drawable.ic_ext_storage))
                    list.add(Component(R.drawable.ic_os))
                }
            }
        }
    }

    class Empty(val image: Int, val title: String)
}