package com.corespark.pccompiler.adapter

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
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
class ActionBar(val context: Context, val activity: Activity, private val list: List<com.corespark.pccompiler.model.ActionBar>)
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

        val parent = activity.findViewById<ConstraintLayout>(R.id.clWorkspaceParent)!!
        val fragment = LayoutInflater.from(context).inflate(R.layout.fragment_control_panel, parent, false)!!

        val dashboard = parent.findViewById<ConstraintLayout>(R.id.clDashboard)!!
        val controlPanel = fragment.findViewById<ConstraintLayout>(R.id.clControlPanelParent)!!

        var isAdded = false

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
                1 -> {
                }
                2 -> {
                }
                3 -> {
                }
                4 -> {
                }
            }
        }

        fun customize(position: Int) {
            when (position) {
                0 -> {
                    Parameter.set(image, 64)
                    title.textSize = 12f
                }
                1 -> {
                }
                2 -> {
                }
                3 -> {
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
                        TransitionManager.beginDelayedTransition(parent)
                        ControlPanel().create()
                        ControlPanel().constraint(view)
                    }
                }
            }
        }

        inner class ControlPanel {

            val card0 = (activity as Workspace).compilationBar.card0
            val cvControlPanel = fragment.findViewById<CardView>(R.id.cvControlPanel)!!
            val ivSettings = fragment.findViewById<ImageView>(R.id.ivSettings)!!
            val tvSettings = fragment.findViewById<TextView>(R.id.tvSettings)!!
            val ivLogout = fragment.findViewById<ImageView>(R.id.ivLogout)!!
            val tvLogout = fragment.findViewById<TextView>(R.id.tvLogout)!!
            val dvControlPanel = fragment.findViewById<View>(R.id.dvControlPanel)!!

            fun create() {
                if (!isAdded) {
                    fragment.id = R.id.controlPanelLayout
                    parent.addView(fragment)
                    customize()
                    isAdded = true
                }
            }

            fun constraint(view: View) {
                Constraint.set(layout, parent, dashboard, controlPanel) {
                    if (it) {
                        card0?.isClickable = false
                        view.isSelected = !view.isSelected
                    } else {
                        card0?.isClickable = true
                        view.isSelected = !view.isSelected
                    }
                }
            }

            fun customize() {
                Window.determineSpan(context, dvControlPanel, activity.windowManager, Window.orientation, 2) {}

                val values = listOf(ivSettings, tvSettings, ivLogout, tvLogout)
                for (value in values) setValue(value)
            }

            fun setValue(view: View) {
                when (view.id) {
                    ivSettings.id -> { ivSettings.setImageResource(R.drawable.ic_settings_inactive) }
                    tvSettings.id -> { tvSettings.text = context.getString(R.string.text_settings) }
                    ivLogout.id -> { ivLogout.setImageResource(R.drawable.ic_logout_inactive) }
                    tvLogout.id -> { tvLogout.text = context.getString(R.string.text_logout) }
                }
            }
        }
    }
}