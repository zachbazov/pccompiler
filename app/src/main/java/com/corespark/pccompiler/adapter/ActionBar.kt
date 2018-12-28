package com.corespark.pccompiler.adapter

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.activity.Workspace
import com.corespark.pccompiler.model.User
import com.corespark.pccompiler.service.Constraint
import com.corespark.pccompiler.service.Parameter
import com.corespark.pccompiler.service.Window


/**
 * @author Zachy Bazov.
 * @since 25/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class ActionBar(
    val context: Context, val activity: Activity, private val list: List<com.corespark.pccompiler.model.ActionBar>)
    : RecyclerView.Adapter<ActionBar.ViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(container: ViewGroup, position: Int): ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_action_bar, container, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.span()
        holder.bind(item, position)
        holder.mapId(position)
        holder.customize(position)
        holder.onClick(holder.layout)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clActionBarParent)!!
        val image = itemView.findViewById<ImageView>(R.id.ivActionBar)!!
        val title = itemView.findViewById<TextView>(R.id.tvActionBar)!!
        val divider = itemView.findViewById<View>(R.id.dvActionBar)!!

        fun span() {
            try {
                Window.determineSpan(context, layout, activity.windowManager, Window.orientation, list.size) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: com.corespark.pccompiler.model.ActionBar, position: Int) {
            image.setImageResource(item.image)
            title.text = item.title
            when (position) {
                0 -> {
                    title.text = User.username
                }
            }
        }

        fun mapId(position: Int) {
            when (position) {
                0 -> {
                    layout.id = R.id.actionBarLayout0
                    title.id = R.id.actionBarText0
                }
            }
        }

        fun customize(position: Int) {
            when (position) {
                0 -> {
                    Parameter.set(image, 64)
                    title.textSize = 12f
                }
                4 -> {
                    layout.removeView(divider)
                }
            }
        }

        fun onClick(view: View) {
            view.setOnClickListener {
                when (view.id) {
                    R.id.actionBarLayout0 -> {
                        ControlPanel().constraint(view)
                    }
                }
            }
        }
    }

    inner class ControlPanel {

        val parent = activity.findViewById<ConstraintLayout>(R.id.clWorkspaceParent)!!
        private val dashboard = parent.findViewById<ConstraintLayout>(R.id.clDashboard)!!
        private val controlPanel = parent.findViewById<ConstraintLayout>(R.id.clFragControlPanelParent)!!

        private val card0 = (activity as Workspace).compilationBar.card0

        fun constraint(view: View) {
            TransitionManager.beginDelayedTransition(parent)
            Constraint.set(view, parent, dashboard, controlPanel) {
                if (it) {
                    card0?.isClickable = false
                    view.isSelected = !view.isSelected
                } else {
                    card0?.isClickable = true
                    view.isSelected = !view.isSelected
                }
            }
        }
    }
}