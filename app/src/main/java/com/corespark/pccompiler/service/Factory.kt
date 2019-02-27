package com.corespark.pccompiler.service

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager.beginDelayedTransition
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application.Companion.attributes
import com.corespark.pccompiler.model.Compilation.case
import com.corespark.pccompiler.model.Compilation.cooler
import com.corespark.pccompiler.model.Compilation.cpu
import com.corespark.pccompiler.model.Compilation.extStorage
import com.corespark.pccompiler.model.Compilation.graphicCard
import com.corespark.pccompiler.model.Compilation.memory
import com.corespark.pccompiler.model.Compilation.motherboard
import com.corespark.pccompiler.model.Compilation.opSystem
import com.corespark.pccompiler.model.Compilation.optDrive
import com.corespark.pccompiler.model.Compilation.powerSupply
import com.corespark.pccompiler.model.Compilation.soundCard
import com.corespark.pccompiler.model.Compilation.storage
import com.corespark.pccompiler.model.Component
import kotlinx.android.synthetic.main.activity_compile.view.*


/**
 * @author Zachy Bazov.
 * @since 21/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Factory {

    fun style(view: View, rowPosition: Int) = when (rowPosition % 2 == 0) {
        true -> view.setBackgroundColor(attributes.colorCloud)
        else -> view.setBackgroundColor(attributes.colorWhite)
    }

    fun focus(recycler: RecyclerView, currentPosition: Int) = (0..12).forEach { position ->
        recycler.layoutManager!!.getChildAt(currentPosition)?.setBackgroundColor(attributes.colorAccent)
        recycler.layoutManager!!.getChildAt(position)?.setBackgroundColor(attributes.colorCloud)
    }

    fun mark(card: CardView, position: Int) {
        when (position) {
            0 -> if (cpu != null) card.setCardBackgroundColor(attributes.colorAccent)
            1 -> if (optDrive != null) card.setCardBackgroundColor(attributes.colorAccent)
            2 -> if (cooler != null) card.setCardBackgroundColor(attributes.colorAccent)
            3 -> if (graphicCard != null) card.setCardBackgroundColor(attributes.colorAccent)
            4 -> if (motherboard != null) card.setCardBackgroundColor(attributes.colorAccent)
            5 -> if (soundCard != null) card.setCardBackgroundColor(attributes.colorAccent)
            6 -> if (memory != null) card.setCardBackgroundColor(attributes.colorAccent)
            7 -> if (powerSupply != null) card.setCardBackgroundColor(attributes.colorAccent)
            8 -> if (storage != null) card.setCardBackgroundColor(attributes.colorAccent)
            9 -> if (case != null) card.setCardBackgroundColor(attributes.colorAccent)
            10 -> if (extStorage != null) card.setCardBackgroundColor(attributes.colorAccent)
            11 -> if (opSystem != null) card.setCardBackgroundColor(attributes.colorAccent)
        }
    }

    fun mark(parent: ViewGroup, item: Component, view: View, componentType: Int, rowPosition: Int) {
        val card: CardView
        when (componentType) {
            0 -> if (cpu?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            1 -> if (optDrive?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            2 -> if (cooler?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            3 -> if (graphicCard?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            4 -> if (motherboard?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            5 -> if (soundCard?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            6 -> if (memory?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            7 -> if (powerSupply?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            8 -> if (storage?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            9 -> if (case?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            10 -> if (extStorage?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
            11 -> if (opSystem?.component.equals(item.component)) {
                card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                card.setCardBackgroundColor(attributes.colorAccent)
                view.setBackgroundColor(attributes.colorAccent)
            } else style(view, rowPosition)
        }
    }

    fun mark(parent: ViewGroup, view: Button, componentType: Int, rowPosition: Int) {
        val card = parent.rvComponentBar.layoutManager!!.getChildAt(componentType)?.findViewById<CardView>(R.id.cvComponentBarItem)!!
        val row = parent.rvComponent.layoutManager!!.getChildAt(rowPosition)?.findViewById<ConstraintLayout>(R.id.clComponentItem)!!
        if (view.isEnabled) {
            card.setCardBackgroundColor(attributes.colorAccent)
            row.setBackgroundColor(attributes.colorAccent)
        } else {
            card.setCardBackgroundColor(attributes.colorWhite)
            style(row, rowPosition)
        }
    }

    private fun enable(boolean: Boolean, views: Array<Button>) = if (boolean) {
        views[0].isEnabled = false
        views[1].isEnabled = true
    } else {
        views[0].visibility = View.GONE
        views[1].visibility = View.GONE
        views[2].visibility = View.VISIBLE
    }

    fun utilize(item: Component, componentType: Int, views: Array<Button>) {
        when (componentType) {
            0 -> if (cpu != null) enable(cpu?.component.equals(item.component), views)
            1 -> if (optDrive != null) enable(optDrive?.component.equals(item.component), views)
            2 -> if (cooler != null) enable(cooler?.component.equals(item.component), views)
            3 -> if (graphicCard != null) enable(graphicCard?.component.equals(item.component), views)
            4 -> if (motherboard != null) enable(motherboard?.component.equals(item.component), views)
            5 -> if (soundCard != null) enable(soundCard?.component.equals(item.component), views)
            6 -> if (memory != null) enable(memory?.component.equals(item.component), views)
            7 -> if (powerSupply != null) enable(powerSupply?.component.equals(item.component), views)
            8 -> if (storage != null) enable(storage?.component.equals(item.component), views)
            9 -> if (case != null) enable(case?.component.equals(item.component), views)
            10 -> if (extStorage != null) enable(extStorage?.component.equals(item.component), views)
            11 -> if (opSystem != null) enable(opSystem?.component.equals(item.component), views)
        }
    }

    fun position(componentType: Int, oldPositionList: MutableList<Int>, rowPosition: Int) : Int {
        var position = -1
        oldPositionList.add(rowPosition)
        when (componentType) {
            componentType -> if (oldPositionList.size > 1) position = oldPositionList[oldPositionList.size - 2]
        }
        return position
    }

    fun replace(
        parent: ViewGroup, layout: View, componentType: Int, rowPosition: Int, oldPosition: Int, views: Array<Button>
    ) {
        when (componentType) {
            componentType -> (0..12).forEach { position ->
                when (position) {
                    position -> {
                        val oldRow = parent.rvComponent.layoutManager!!
                            .getChildAt(oldPosition)?.findViewById<ConstraintLayout>(R.id.clComponentItem)!!
                        style(oldRow, oldPosition)
                        mark(parent, views[2], componentType, rowPosition)
                        views[0].isEnabled = false
                        views[1].isEnabled = true
                        beginDelayedTransition(layout as ViewGroup?)
                        views[0].visibility = View.VISIBLE
                        views[1].visibility = View.VISIBLE
                        views[2].visibility = View.GONE
                    }
                }
            }
        }
    }
}