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
class CartBar(
    val context: Context, val activity: Activity, private val list: List<com.corespark.pccompiler.model.CartBar>)
    : RecyclerView.Adapter<CartBar.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(container: ViewGroup, position: Int): ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_cart_bar, container, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.span()
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clCartBarParent)
        val image = itemView.findViewById<ImageView>(R.id.ivCartBar)
        val title = itemView.findViewById<TextView>(R.id.tvCartBar)
        val divider = itemView.findViewById<View>(R.id.dvCartBar)

        fun span() {
            try {
                Window.determineSpan(context, layout, activity.windowManager, Window.orientation, list.size) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: com.corespark.pccompiler.model.CartBar) {
            image.setImageResource(item.image)
            title.text = item.title
        }
    }
}