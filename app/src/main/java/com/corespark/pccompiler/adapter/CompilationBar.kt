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
import com.corespark.pccompiler.model.Compilation
import com.corespark.pccompiler.service.Window


/**
 * @author Zachy Bazov.
 * @since 20/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class CompilationBar(
    val context: Context, private val list: List<Compilation>) : RecyclerView.Adapter<CompilationBar.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item.image, item.title)
    }

    override fun onCreateViewHolder(container: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_compilation, container, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val card = itemView.findViewById<CardView>(R.id.cvCompilation)!!
        val image = itemView.findViewById<ImageView>(R.id.ivCompilation)!!
        val title = itemView.findViewById<TextView>(R.id.tvCompilation)!!

        fun bind(resId: Int, text: String) {
            image.setImageResource(resId)
            title.text = text
            card.layoutParams.width = Window.measureDividedWidthPx(Window.widthPx, 2)
        }
    }
}