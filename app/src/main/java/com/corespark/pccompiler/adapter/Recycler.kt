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
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.*
import com.corespark.pccompiler.service.Constraint
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.service.Parameter
import com.corespark.pccompiler.service.Window


/**
 * @author Zachy Bazov.
 * @since 29/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Recycler(val context: Context, val activity: Activity, private val list: List<Any>, val type: Int)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var card0: ConstraintLayout? = null
    var card1: ConstraintLayout? = null

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        when (type) {
            0 -> {
                holder = CompilationViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_compilation_bar, container, false))
            }
            1 -> {
                holder = CartBarViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_cart_bar, container, false))
            }
            2 -> {
                holder = ActionBarViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_action_bar, container, false))
            }
            3 -> {
                holder = ControlBarViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_control_bar, container, false))
            }
            4 -> {
                holder = ControlPanelViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_control_panel, container, false))
            }
            else -> {
                holder = EmptyViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_empty, container, false))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (type) {
            0 -> {
                val item = list[position]
                holder as CompilationViewHolder
                holder.span()
                holder.bind(holder, item as CompilationBar, position)
            }
            1 -> {
                val item = list[position]
                holder as CartBarViewHolder
                holder.span()
                holder.bind(item as CartBar)
            }
            2 -> {
                val item = list[position]
                holder as ActionBarViewHolder
                holder.span()
                holder.bind(item as ActionBar, position)
                holder.mapId(position)
                holder.customize(position)
                holder.onClick(holder.layout)
            }
            3 -> {
                val item = list[position]
                holder as ControlBarViewHolder
                holder.span()
                holder.bind(item as ControlBar)
            }
            4 -> {
                val item = list[position]
                holder as ControlPanelViewHolder
                holder.span()
                holder.bind(item as com.corespark.pccompiler.model.ControlPanel)
                holder.mapId(position)
                holder.onClick(holder.clControlPanel)
            }
            else -> {
                val item = list[position]
                holder as EmptyViewHolder
                holder.bind(item as EmptyBar)
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clCompilationEmptyParent)
        val sub = itemView.findViewById<ConstraintLayout>(R.id.clCompilationEmpty)
        val image = itemView.findViewById<ImageView>(R.id.ivCompilationEmpty)
        val title = itemView.findViewById<TextView>(R.id.tvCompilationEmpty)

        fun bind(item: EmptyBar) {
            sub.layoutParams.width = Window.measureMultiDeviceDensity(Window.widthPx, 1)
            image.setImageResource(item.image)
            title.text = item.title
        }
    }

    inner class CompilationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val card = itemView.findViewById<ConstraintLayout>(R.id.clCompilation)!!
        val image = itemView.findViewById<ImageView>(R.id.ivCompilation)!!
        val title = itemView.findViewById<TextView>(R.id.tvCompilation)!!

        fun span() {
            try {
                Window.determineSpan(context, card, (context as Workspace).windowManager, Window.orientation, 1) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(holder: CompilationViewHolder, item: CompilationBar, position: Int) {
            image.setImageResource(item.image)
            title.text = item.title
            Parameter.set(image, 64)

            when (position) {
                0 -> {
                    if (card0 == null) {
                        card0 = holder.card
                        card0?.id = R.id.compilationBarCard0
                    }
                    onClick(card0!!) {
                        if (it) {
                            card0?.setBackgroundColor(Compiler.colors.colorAccent)
                            card1?.setBackgroundColor(Compiler.colors.colorCloud)
                        } else {
                            card0?.setBackgroundColor(Compiler.colors.colorCloud)
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
                            card1?.setBackgroundColor(Compiler.colors.colorAccent)
                            card0?.setBackgroundColor(Compiler.colors.colorCloud)
                        } else {
                            card1?.setBackgroundColor(Compiler.colors.colorCloud)
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

    inner class ActionBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clActionBarParent)!!
        val sub = itemView.findViewById<ConstraintLayout>(R.id.clActionBarSub)!!
        val image = itemView.findViewById<ImageView>(R.id.ivActionBar)!!
        val title = itemView.findViewById<TextView>(R.id.tvActionBar)!!
        val divider = itemView.findViewById<View>(R.id.dvActionBar)!!

        fun span() {
            try {
                Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: ActionBar, position: Int) {
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
                    image.id = R.id.actionBarImage0
                    title.id = R.id.actionBarText0
                }
            }
        }

        fun customize(position: Int) {
            Parameter.set(image, 56)
            when (position) {
                0 -> {
                    sub.removeView(title)
                    Parameter.set(image, 64)
                    layout.layoutParams.height = ConstraintLayout.LayoutParams.MATCH_PARENT
                    Constraint.set(image, sub, image)
                }
                1 -> {

                }
                2 -> {
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

    inner class CartBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clCartBar)
        val image = itemView.findViewById<ImageView>(R.id.ivCartBar)
        val component = itemView.findViewById<TextView>(R.id.tvComponentCartBar)
        val price = itemView.findViewById<TextView>(R.id.tvPriceCartBar)

        fun span() {
            try {
                Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, 1) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: com.corespark.pccompiler.model.CartBar) {
            image.setImageResource(item.image)
            component.text = item.component
            price.text = item.price
        }
    }

    inner class ControlBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clControlBarParent)
        val image = itemView.findViewById<ImageView>(R.id.ivControlBar)
        val title = itemView.findViewById<TextView>(R.id.tvControlBar)
        val divider = itemView.findViewById<View>(R.id.dvControlBar)

        fun span() {
            try {
                Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: com.corespark.pccompiler.model.ControlBar) {
            image.setImageResource(item.image)
            title.text = item.title
        }
    }

    inner class ControlPanelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val clControlPanelParent = itemView.findViewById<ConstraintLayout>(R.id.clControlPanelParent)!!
        val clControlPanel = itemView.findViewById<ConstraintLayout>(R.id.clControlPanel)!!
        val ivControlPanel = itemView.findViewById<ImageView>(R.id.ivControlPanel)!!
        val tvControlPanel = itemView.findViewById<TextView>(R.id.tvControlPanel)!!
        val dvControlPanel = itemView.findViewById<View>(R.id.dvControlPanel)!!

        fun span() {
            Window.determineSpan(context, dvControlPanel, (context as Workspace).windowManager, Window.orientation, list.size) {}
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

    inner class ControlPanel {

        val parent = activity.findViewById<ConstraintLayout>(R.id.clWorkspace)!!
        private val dashboard = parent.findViewById<ConstraintLayout>(R.id.clFragActionBar)!!
        private val controlPanel = parent.findViewById<ConstraintLayout>(R.id.clFragControlPanel)!!

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