package com.corespark.pccompiler.adapter

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.service.Window


/**
 * @author Zachy Bazov.
 * @since 28/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class ControlBar(
    val context: Context, val activity: Activity, private val list: List<com.corespark.pccompiler.model.ControlBar>)
    : RecyclerView.Adapter<ControlBar.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(container: ViewGroup, position: Int): ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_control_bar, container, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.span()
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clControlBarParent)
        val image = itemView.findViewById<ImageView>(R.id.ivControlBar)
        val title = itemView.findViewById<TextView>(R.id.tvControlBar)
        val divider = itemView.findViewById<View>(R.id.dvControlBar)

        fun span() {
            try {
                Window.determineSpan(context, layout, activity.windowManager, Window.orientation, list.size) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: com.corespark.pccompiler.model.ControlBar) {
            image.setImageResource(item.image)
            title.text = item.title
        }
    }
}