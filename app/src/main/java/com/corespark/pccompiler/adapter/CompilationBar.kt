package com.corespark.pccompiler.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.Compilation
import com.corespark.pccompiler.service.Window


/**
 * @author Zachy Bazov.
 * @since 20/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class CompilationBar(val context: Context, private val list: List<Compilation>)
    : RecyclerView.Adapter<CompilationBar.ViewHolder>() {

    var card0: CardView? = null
    var card1: CardView? = null

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onCreateViewHolder(container: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_compilation_bar, container, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(holder, item, position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val card = itemView.findViewById<CardView>(R.id.cvCompilation)!!
        val image = itemView.findViewById<ImageView>(R.id.ivCompilation)!!
        val title = itemView.findViewById<TextView>(R.id.tvCompilation)!!

        fun bind(holder: ViewHolder, item: Compilation, position: Int) {
            holder.image.setImageResource(item.image)
            holder.title.text = item.title
            holder.card.layoutParams.width = Window.measureDividedWidthPx(Window.widthPx, 2)

            when (position) {
                0 -> {
                    if (card0 == null) {
                        card0 = holder.card
                        card0?.id = R.id.compilationBarCard0
                    }
                    onClick(card0!!) {
                        if (it) {
                            card0?.setCardBackgroundColor(Compiler.colors.colorGray)
                            card1?.setCardBackgroundColor(Compiler.colors.colorWhite)
                        } else {
                            card0?.setCardBackgroundColor(Compiler.colors.colorWhite)
                        }
                    }
                }
                1 -> {
                    if (card1 == null) {
                        card1 = holder.card
                        card1?.id = R.id.compilationBarCard1
                    }
                    onClick(card1!!) {
                        if (it) {
                            card1?.setCardBackgroundColor(Compiler.colors.colorGray)
                            card0?.setCardBackgroundColor(Compiler.colors.colorWhite)
                        } else {
                            card1?.setCardBackgroundColor(Compiler.colors.colorWhite)
                        }
                    }
                }
            }
        }

        fun onClick(view: View, complete: (Boolean) -> Unit) {
            view.setOnClickListener {
                when (view.id) {
                    R.id.compilationBarCard0 -> {
                        if (!it.isSelected) {
                            card0?.isSelected = true
                            card1?.isSelected = false
                            complete(true)
                        } else {
                            card0?.isSelected = false
                            complete(false)
                        }
                    }
                    R.id.compilationBarCard1 -> {
                        if (!it.isSelected) {
                            card0?.isSelected = false
                            card1?.isSelected = true
                            complete(true)
                        } else {
                            card1?.isSelected = false
                            complete(false)
                        }
                    }
                }
            }
        }
    }
}