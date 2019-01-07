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

    class Action(val image: Int, val title: String) {

        companion object {
            val list = mutableListOf<Action>()

            fun add(context: Context) {
                if (list.size < 3) {
                    list.add(0, Action(R.drawable.ic_profile_inactive, title = ""))
                    list.add(1, Action(R.drawable.ic_compile_inactive, context.getString(R.string.text_compile)))
                    list.add(2, Action(R.drawable.ic_edit_inactive, context.getString(R.string.text_edit)))
                }
            }
        }
    }

    class Control(val image: Int, val title: String) {

        companion object {
            val list = mutableListOf<Control>()

            fun add(context: Context) {
                if (list.size < 3) {
                    list.add(0, Control(R.drawable.ic_explore_inactive, context.getString(R.string.text_explore)))
                    list.add(1, Control(R.drawable.ic_change_inactive, context.getString(R.string.text_change)))
                    list.add(2, Control(R.drawable.ic_trash_inactive, context.getString(R.string.text_trash)))
                }
            }
        }
    }

    class CompilationBar(val id: Int, val title: String, val image: Int) {

        companion object {
            val list = mutableListOf<CompilationBar>()
            val empty = mutableListOf<EmptyBar>()

            fun add() {
                if (list.size < 1) {
                    list.add(0, CompilationBar(0, "PCCompiler", R.mipmap.ic_pccompiler))
                }
            }

            fun addEmpty(context: Context) {
                if (empty.size < 1) {
                    empty.add(0, EmptyBar(R.drawable.ic_pccompiler_inactive, context.getString(R.string.text_no_compilations)))
                }
            }
        }
    }

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

    class ComponentBar(val id: Int, val image: Int) {

        companion object {
            val list = mutableListOf<ComponentBar>()

            fun add() {
                if (list.size < 2) {
                    list.add(0, ComponentBar(0, R.mipmap.ic_cpu))
                    list.add(1, ComponentBar(1, R.mipmap.ic_optdrive))
                    list.add(2, ComponentBar(2, R.mipmap.ic_cooler))
                    list.add(3, ComponentBar(3, R.mipmap.ic_graphiccard))
                    list.add(4, ComponentBar(4, R.mipmap.ic_motherboard))
                    list.add(5, ComponentBar(5, R.mipmap.ic_soundcard))
                    list.add(6, ComponentBar(6, R.mipmap.ic_memory))
                    list.add(7, ComponentBar(11, R.mipmap.ic_case))
                    list.add(8, ComponentBar(8, R.mipmap.ic_storage))
                    list.add(9, ComponentBar(9, R.mipmap.ic_powersupply))
                    list.add(10, ComponentBar(10, R.mipmap.ic_extstorage))
                    list.add(11, ComponentBar(7, R.mipmap.ic_opsystem))
                }
            }
        }
    }

    class EmptyBar(val image: Int, val title: String)
}