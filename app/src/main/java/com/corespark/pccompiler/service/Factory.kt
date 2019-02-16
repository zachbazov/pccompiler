package com.corespark.pccompiler.service

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application
import com.corespark.pccompiler.model.Compilation
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

    fun styleAsTable(view: View, rowPosition: Int) = when {
        rowPosition % 2 == 0 -> view.setBackgroundColor(Application.attributes.colorGray)
        else -> view.setBackgroundColor(Application.attributes.colorWhite)
    }

    fun focus(recycler: RecyclerView, currentPosition: Int) = (0..12).forEach { position ->
        recycler.layoutManager?.getChildAt(currentPosition)?.setBackgroundColor(Application.attributes.colorAccent)
        recycler.layoutManager?.getChildAt(position)?.setBackgroundColor(Application.attributes.colorCloud)
    }

    fun mark(parent: ViewGroup, view: Button, componentType: Int, rowPosition: Int) {
        val card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById<CardView>(R.id.cvComponentBarItem)!!
        val row = parent.rvComponent.layoutManager?.getChildAt(rowPosition)?.findViewById<ConstraintLayout>(R.id.clComponentItem)!!
        when {
            view.isEnabled -> {
                card.setCardBackgroundColor(Application.attributes.colorAccent)
                row.setBackgroundColor(Application.attributes.colorAccent)
            }
            else -> {
                card.setCardBackgroundColor(Application.attributes.colorWhite)
                styleAsTable(row, rowPosition)
            }
        }
    }

    fun mark(parent: ViewGroup, item: Component, view: View, componentType: Int, rowPosition: Int) {
        val card: CardView
        when (componentType) {
            0 -> when {
                com.corespark.pccompiler.model.Compilation.cpu?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            1 -> when {
                com.corespark.pccompiler.model.Compilation.optDrive?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            2 -> when {
                com.corespark.pccompiler.model.Compilation.cooler?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            3 -> when {
                com.corespark.pccompiler.model.Compilation.graphicCard?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            4 -> when {
                com.corespark.pccompiler.model.Compilation.motherboard?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            5 -> when {
                com.corespark.pccompiler.model.Compilation.soundCard?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            6 -> when {
                com.corespark.pccompiler.model.Compilation.memory?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            7 -> when {
                com.corespark.pccompiler.model.Compilation.powerSupply?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            8 -> when {
                com.corespark.pccompiler.model.Compilation.storage?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            9 -> when {
                com.corespark.pccompiler.model.Compilation.case?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            10 -> when {
                com.corespark.pccompiler.model.Compilation.extStorage?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
            else -> when {
                com.corespark.pccompiler.model.Compilation.opSystem?.component.equals(item.component) -> {
                    card = parent.rvComponentBar.layoutManager?.getChildAt(componentType)?.findViewById(R.id.cvComponentBarItem)!!
                    card.setCardBackgroundColor(Application.attributes.colorAccent)
                    view.setBackgroundColor(Application.attributes.colorAccent)
                }
                else -> Factory.styleAsTable(view, rowPosition)
            }
        }
    }

    fun mark(recycler: RecyclerView) = (0..12).forEach { i ->
        when (i) {
            0 -> when {
                com.corespark.pccompiler.model.Compilation.cpu != null -> recycler.layoutManager?.getChildAt(0)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            1 -> when {
                com.corespark.pccompiler.model.Compilation.optDrive != null -> recycler.layoutManager?.getChildAt(1)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            2 -> when {
                com.corespark.pccompiler.model.Compilation.cooler != null -> recycler.layoutManager?.getChildAt(2)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            3 -> when {
                com.corespark.pccompiler.model.Compilation.graphicCard != null -> recycler.layoutManager?.getChildAt(3)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            4 -> when {
                com.corespark.pccompiler.model.Compilation.motherboard != null -> recycler.layoutManager?.getChildAt(4)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            5 -> when {
                com.corespark.pccompiler.model.Compilation.soundCard != null -> recycler.layoutManager?.getChildAt(5)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            6 -> when {
                com.corespark.pccompiler.model.Compilation.memory != null -> recycler.layoutManager?.getChildAt(6)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            7 -> when {
                com.corespark.pccompiler.model.Compilation.powerSupply != null -> recycler.layoutManager?.getChildAt(7)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            8 -> when {
                com.corespark.pccompiler.model.Compilation.storage != null -> recycler.layoutManager?.getChildAt(8)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            9 -> when {
                com.corespark.pccompiler.model.Compilation.case != null -> recycler.layoutManager?.getChildAt(9)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            10 -> when {
                com.corespark.pccompiler.model.Compilation.extStorage != null -> recycler.layoutManager?.getChildAt(10)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
            11 -> when {
                com.corespark.pccompiler.model.Compilation.opSystem != null -> recycler.layoutManager?.getChildAt(11)
                    ?.findViewById<CardView>(R.id.cvComponentBarItem)
                    ?.setCardBackgroundColor(Application.attributes.colorAccent)
            }
        }
    }

    private fun handleSelection(boolean: Boolean, views: Array<Button>) = when {
        boolean -> {
            views[0].isEnabled = false
            views[1].isEnabled = true
        }
        else -> {
            views[0].visibility = View.GONE
            views[1].visibility = View.GONE
            views[2].visibility = View.VISIBLE
        }
    }

    fun enableSelection(item: Component, componentType: Int, views: Array<Button>) = when (componentType) {
        0 -> when { cpu != null -> handleSelection(Compilation.cpu?.component.equals(item.component), views) else -> {} }
        1 -> when { optDrive != null -> handleSelection(Compilation.optDrive?.component.equals(item.component), views) else -> {} }
        2 -> when { cooler != null -> handleSelection(Compilation.cooler?.component.equals(item.component), views) else -> {} }
        3 -> when { graphicCard != null -> handleSelection(Compilation.graphicCard?.component.equals(item.component), views) else -> {} }
        4 -> when { motherboard != null -> handleSelection(Compilation.motherboard?.component.equals(item.component), views) else -> {} }
        5 -> when { soundCard != null -> handleSelection(Compilation.soundCard?.component.equals(item.component), views) else -> {} }
        6 -> when { memory != null -> handleSelection(Compilation.memory?.component.equals(item.component), views) else -> {} }
        7 -> when { powerSupply != null -> handleSelection(Compilation.powerSupply?.component.equals(item.component), views) else -> {} }
        8 -> when { storage != null -> handleSelection(Compilation.storage?.component.equals(item.component), views) else -> {} }
        9 -> when { case != null -> handleSelection(Compilation.case?.component.equals(item.component), views) else -> {} }
        10 -> when { extStorage != null -> handleSelection(Compilation.extStorage?.component.equals(item.component), views) else -> {} }
        else -> when { opSystem != null -> handleSelection(Compilation.opSystem?.component.equals(item.component), views) else -> {} }
    }

    fun oldPosition(componentType: Int, oldPositionList: MutableList<Int>, rowPosition: Int) : Int {
        var position = -1
        oldPositionList.add(rowPosition)
        when (componentType) { componentType -> when {oldPositionList.size > 1 -> position = oldPositionList[oldPositionList.size - 2] } }
        return position
    }

    fun replacePosition(
        parent: ViewGroup, layout: View, componentType: Int, rowPosition: Int, oldPosition: Int, views: Array<Button>
    ) = when (componentType) {
        componentType -> (0..12).forEach { i ->
            when (i) {
                i -> {
                    val oldRow = parent.rvComponent.layoutManager?.getChildAt(oldPosition)
                        ?.findViewById<ConstraintLayout>(R.id.clComponentItem)!!
                    Factory.styleAsTable(oldRow, oldPosition)
                    Factory.mark(parent, views[2], componentType, rowPosition)
                    views[0].isEnabled = false
                    views[1].isEnabled = true
                    TransitionManager.beginDelayedTransition(layout as ViewGroup?)
                    views[0].visibility = View.VISIBLE
                    views[1].visibility = View.VISIBLE
                    views[2].visibility = View.GONE
                }
            }
        } else -> {}
    }
}