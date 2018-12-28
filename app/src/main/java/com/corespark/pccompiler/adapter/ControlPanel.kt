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
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.service.Window


/**
 * @author Zachy Bazov.
 * @since 28/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class ControlPanel(
    val context: Context, val activity: Activity, private val list: List<com.corespark.pccompiler.model.ControlPanel>)
    : RecyclerView.Adapter<ControlPanel.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(container: ViewGroup, position: Int): ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_control_panel, container, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.span()
        holder.bind(item)
        holder.mapId(position)
        holder.onClick(holder.clControlPanel)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val clControlPanelParent = itemView.findViewById<ConstraintLayout>(R.id.clControlPanelParent)!!
        val clControlPanel = itemView.findViewById<ConstraintLayout>(R.id.clControlPanel)!!
        val ivControlPanel = itemView.findViewById<ImageView>(R.id.ivControlPanel)!!
        val tvControlPanel = itemView.findViewById<TextView>(R.id.tvControlPanel)!!
        val dvControlPanel = itemView.findViewById<View>(R.id.dvControlPanel)!!

        fun span() {
            Window.determineSpan(context, dvControlPanel, activity.windowManager, Window.orientation, list.size) {}
        }

        fun bind(item: com.corespark.pccompiler.model.ControlPanel) {
            ivControlPanel.setImageResource(item.image)
            tvControlPanel.text = item.title
        }

        fun mapId(position: Int) {
            when (position) {
                1 -> {
                    clControlPanel.id = R.id.controlPanelLayout0
                }
            }
        }

        fun onClick(view: View) {
            when (view.id) {
                R.id.controlPanelLayout0 -> {
                    view.setOnClickListener {
                        com.corespark.pccompiler.service.Auth.logOut(context) { complete ->
                            if (complete) {
                                activity.startActivity(Intent.launch(context, R.layout.activity_auth))
                                activity.finish()
                            }
                        }
                    }
                }
            }
        }
    }
}