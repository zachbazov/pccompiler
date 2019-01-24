package com.corespark.pccompiler.model

import android.content.Context
import android.view.View
import android.widget.ToggleButton
import com.corespark.pccompiler.R
import com.parse.ParseUser


/**
 * @author Zachy Bazov.
 * @since 24/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Compilation {

    var id: String? = null
    var title: String? = null
    var user: ParseUser? = null
    var cpu: Component? = null
    var cooler: Component? = null
    var motherboard: Component? = null
    var memory: Component? = null
    var storage: Component? = null
    var extStorage: Component? = null
    var optDrive: Component? = null
    var graphicCard: Component? = null
    var soundCard: Component? = null
    var powerSupply: Component? = null
    var case: Component? = null
    var opSystem: Component? = null

    fun assignCompilation(user: ParseUser, title: String?) {
        this.user = user
        this.title = title
    }

    fun assignComponent(component: Int, item: Component?) {
        when (component) {
            0 -> cpu = item
            1 -> optDrive = item
            2 -> cooler = item
            3 -> graphicCard = item
            4 -> motherboard = item
            5 -> soundCard = item
            6 -> memory = item
            7 -> powerSupply = item
            8 -> storage = item
            9 -> case = item
            10 -> extStorage = item
            11 -> opSystem = item
        }
    }

    fun deassignComponent(component: Int) {
        when (component) {
            0 -> cpu = null
            1 -> optDrive = null
            2 -> cooler = null
            3 -> graphicCard = null
            4 -> motherboard = null
            5 -> soundCard = null
            6 -> memory = null
            7 -> powerSupply = null
            8 -> storage = null
            9 -> case = null
            10 -> extStorage = null
            11 -> opSystem = null
        }
    }

    fun updateComponent(context: Context, view: View, component: Int, item: Component?) {
        view as ToggleButton
        when (component) {
            0 -> if (Compilation.cpu != null) if (Compilation.cpu?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            1 -> if (Compilation.optDrive != null) if (Compilation.optDrive?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            2 -> if (Compilation.cooler != null) if (Compilation.cooler?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            3 -> if (Compilation.graphicCard != null) if (Compilation.graphicCard?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            4 -> if (Compilation.motherboard != null) if (Compilation.motherboard?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            5 -> if (Compilation.soundCard != null) if (Compilation.soundCard?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            6 -> if (Compilation.memory != null) if (Compilation.memory?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            7 -> if (Compilation.powerSupply != null) if (Compilation.powerSupply?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            8 -> if (Compilation.storage != null) if (Compilation.storage?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            9 -> if (Compilation.case != null) if (Compilation.case?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            10 -> if (Compilation.extStorage != null) if (Compilation.extStorage?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
            11 -> if (Compilation.opSystem != null) if (Compilation.opSystem?.component.equals(item!!.component))
                view.isChecked = true
            else {
                view.isChecked = false
                view.text = context.getString(R.string.text_replace)
            }
        }
    }
}