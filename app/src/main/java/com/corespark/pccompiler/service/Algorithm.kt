package com.corespark.pccompiler.service

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ToggleButton
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import kotlinx.android.synthetic.main.activity_compile.view.*


/**
 * @author Zachy Bazov.
 * @since 21/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Algorithm {

    fun style(view: View, position: Int) {
        if (position % 2 == 0) view.setBackgroundColor(Compiler.colors.colorGray)
        else view.setBackgroundColor(Compiler.colors.colorWhite)
    }

    fun bind(view: ImageView, component: Int) = when (component) {
        0 -> view.setImageResource(R.mipmap.ic_cpu)
        1 -> view.setImageResource(R.mipmap.ic_optdrive)
        2 -> view.setImageResource(R.mipmap.ic_cooler)
        3 -> view.setImageResource(R.mipmap.ic_graphiccard)
        4 -> view.setImageResource(R.mipmap.ic_motherboard)
        5 -> view.setImageResource(R.mipmap.ic_soundcard)
        6 -> view.setImageResource(R.mipmap.ic_memory)
        7 -> view.setImageResource(R.mipmap.ic_powersupply)
        8 -> view.setImageResource(R.mipmap.ic_storage)
        9 -> view.setImageResource(R.mipmap.ic_case)
        10 -> view.setImageResource(R.mipmap.ic_extstorage)
        else -> view.setImageResource(R.mipmap.ic_opsystem)
    }

    fun focus(recycler: RecyclerView, current: Int) {
        for (position in 0..12) {
            recycler.layoutManager?.getChildAt(current)?.setBackgroundColor(Compiler.colors.colorAccent)
            recycler.layoutManager?.getChildAt(position)?.setBackgroundColor(Compiler.colors.colorCloud)
        }
    }

    fun selection(parent: ViewGroup, view: ToggleButton, position: Int) {
        val card = parent.rvComponentBar.layoutManager?.getChildAt(position)?.findViewById<CardView>(R.id.cvComponentBarItem)!!
        if (view.isChecked) card.setCardBackgroundColor(Compiler.colors.colorAccent)
        else card.setCardBackgroundColor(Compiler.colors.colorWhite)
    }
}