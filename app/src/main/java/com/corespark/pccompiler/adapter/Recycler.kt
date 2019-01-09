package com.corespark.pccompiler.adapter

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
import com.corespark.pccompiler.activity.Compile
import com.corespark.pccompiler.activity.Workspace
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.*
import com.corespark.pccompiler.service.Constraint
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.service.Parameter
import com.corespark.pccompiler.service.Window
import kotlinx.android.synthetic.main.activity_workspace.*


/**
 * @author Zachy Bazov.
 * @since 29/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Recycler(val context: Context, private val list: List<Any>, val type: Int)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        val infalter = LayoutInflater.from(context)
        when (type) {
            0 -> {
                holder = TabBar().TabBarViewHolder(infalter.inflate(R.layout.item_tab_bar, container, false))
            }
            1 -> {
                holder = ActionBarViewHolder(infalter.inflate(R.layout.item_action_bar, container, false))
            }
            2 -> {
                holder = ControlBarViewHolder(infalter.inflate(R.layout.item_control_bar, container, false))
            }
            3 -> {
                holder = ControlPanel().ControlPanelViewHolder(
                    infalter.inflate(R.layout.item_control_panel, container, false))
            }
            4 -> {
                holder = Compilation().CompilationViewHolder(
                    infalter.inflate(R.layout.item_compilation_bar, container, false))
            }
            5 -> {
                holder = CartBarViewHolder(infalter.inflate(R.layout.item_cart_bar, container, false))
            }
            6 -> {
                holder = Component().ComponentBarViewHolder(
                    infalter.inflate(R.layout.item_component_bar, container, false))
            }
            else -> {
                holder = EmptyViewHolder(infalter.inflate(R.layout.item_empty, container, false))
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (type) {
            0 -> {
                val item = list[position]
                holder as TabBar.TabBarViewHolder
                holder.span()
                holder.mapId(position)
                holder.bind(item as Bar.Tab)
                holder.customize(position)
                holder.onClick(holder.layout)
            }
            1 -> {
                val item = list[position]
                holder as ActionBarViewHolder
                holder.span()
                holder.bind(item as Bar.Action)
                holder.mapId(position)
                holder.customize(position)
                holder.activate(holder.image, position)
                holder.onClick(holder.layout)
            }
            2 -> {
                val item = list[position]
                holder as ControlBarViewHolder
                holder.span()
                holder.bind(item as Bar.Control)
                holder.activate(holder.image, position)
            }
            3 -> {
                val item = list[position]
                holder as ControlPanel.ControlPanelViewHolder
                holder.span()
                holder.bind(item as Panel.ControlPanel)
                holder.mapId(position)
                holder.customize(position)
                holder.onClick(holder.layout)
            }
            4 -> {
                val item = list[position]
                holder as Compilation.CompilationViewHolder
                holder.span()
                holder.bind(holder, item as Bar.Compilation, position)
            }
            5 -> {
                val item = list[position]
                holder as CartBarViewHolder
                holder.span()
                holder.bind(item as Bar.Cart)
            }
            6 -> {
                val item = list[position]
                holder as Component.ComponentBarViewHolder
                holder.span()
                holder.mapId(position)
                holder.bind(item as Bar.Component)
            }
            else -> {
                val item = list[position]
                holder as EmptyViewHolder
                holder.bind(item as Bar.Empty)
            }
        }
    }

    inner class TabBar {

        var clTabWorkspace: ConstraintLayout? = null
        var clTabCart: ConstraintLayout? = null
        var ivTabWorkspace: ImageView? = null
        var ivTabCart: ImageView? = null

        val clWorkspace = (context as Workspace).clWorkspace!!
        val clTabParent = (context as Workspace).clFragTabBar!!
        val clFragActionBar = (context as Workspace).clFragActionBar!!
        val clFragControlBar = (context as Workspace).clFragControlBar!!
        val clFragWorkspace = (context as Workspace).clFragWorkspace!!
        val clFragCart = (context as Workspace).clFragCart!!
        val ivTracker = (context as Workspace).ivTracker!!
        val clFragTitle = (context as Workspace).clFragTitle!!
        val tvWorkspaceTitle = (context as Workspace).tvWorkspaceTitle!!
        val tvCartTitle = (context as Workspace).tvCartTitle!!

        inner class TabBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val parent = itemView.findViewById<ConstraintLayout>(R.id.clTabBarParent)!!
            val layout = itemView.findViewById<ConstraintLayout>(R.id.clTabBar)!!
            val image = itemView.findViewById<ImageView>(R.id.ivTabBar)!!
            val divider = itemView.findViewById<View>(R.id.dvTabBar)!!

            fun span() {
                try {
                    Window.determineSpan(
                        context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }

            fun mapId(position: Int) {
                when (position) {
                    0 -> {
                        clTabWorkspace = layout
                        ivTabWorkspace = image
                        layout.id = R.id.clTabWorkspace
                        image.id = R.id.ivTabWorkspace
                    }
                    1 -> {
                        clTabCart = layout
                        ivTabCart = image
                        layout.id = R.id.clTabCart
                        image.id = R.id.ivTabCart
                    }
                }
            }

            fun bind(item: Bar.Tab) {
                image.setImageResource(item.image)
            }

            fun customize(position: Int) {
                tvWorkspaceTitle.text = context.getString(R.string.text_my_compilations)
                tvCartTitle.text = context.getString(R.string.text_my_cart)
                when (position) {
                    0 -> {
                        ivTabWorkspace!!.setImageResource(R.drawable.ic_workspace_active)
                        Parameter.set(ivTabWorkspace!!, 64)
                    }
                    1 -> {
                        ivTabCart!!.setImageResource(R.drawable.ic_cart_active)
                        Parameter.set(ivTabCart!!, 64)
                        layout.removeView(divider)
                    }
                }
            }

            fun onClick(view: View) {
                when (view.id) {
                    clTabWorkspace?.id -> {
                        try {
                            view.setOnClickListener {
                                TransitionManager.beginDelayedTransition(clWorkspace)
                                Constraint.set(clTabWorkspace!!, clTabParent, ivTracker)
                                Constraint.set(clTabWorkspace!!, clWorkspace, clFragWorkspace)
                                Constraint.set(clTabWorkspace!!, clWorkspace) {}
                                Constraint.set(clTabWorkspace!!, clFragTitle, tvWorkspaceTitle)
                            }
                        } catch (e: IllegalStateException) {
                            println(e.localizedMessage)
                        }
                    }
                    clTabCart?.id -> {
                        try {
                            view.setOnClickListener {
                                TransitionManager.beginDelayedTransition(clWorkspace)
                                Constraint.set(clTabCart!!, clTabParent, ivTracker)
                                Constraint.set(clTabCart!!, clWorkspace, clFragCart)
                                Constraint.set(clTabCart!!, clWorkspace) {}
                                Constraint.set(clTabCart!!, clFragTitle, tvCartTitle)
                            }
                        } catch (e: IllegalStateException) {
                            println(e.localizedMessage)
                        }
                    }
                }
            }
        }
    }

    inner class ActionBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clActionBarParent)!!
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

        fun bind(item: Bar.Action) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun mapId(position: Int) {
            when (position) {
                0 -> {
                    layout.id = R.id.clActionUser
                    image.id = R.id.ivActionUser
                    title.id = R.id.tvActionUser
                }
                1 -> {
                    layout.id = R.id.clActionCompile
                    image.id = R.id.ivActionCompile
                }
            }
        }

        fun customize(position: Int) {
            when (position) {
                0 -> {
                    title.text = User.username
                }
                1 -> {

                }
                2 -> {
                    layout.removeView(divider)
                }
            }
        }

        fun activate(view: View, position: Int) {
            when (position) {
                0 -> {
                    if (!view.isSelected) {
                        (view as ImageView).setImageResource(R.drawable.ic_profile_inactive)
                        view.isSelected = !view.isSelected
                    } else {
                        (view as ImageView).setImageResource(R.drawable.ic_profile_active)
                        view.isSelected = !view.isSelected
                    }
                }
                1 -> {
                    (view as ImageView).setImageResource(R.drawable.ic_compile_active)
                }
                2 -> {
                    if (Bar.Compilation.list.size > 0) {
                        (view as ImageView).setImageResource(R.drawable.ic_edit_active)
                    }
                }
            }
        }

        fun onClick(view: View) {
            view.setOnClickListener {
                when (view.id) {
                    R.id.clActionUser -> {
                        ControlPanel().constraint(view)
                        activate(image, 0)
                    }
                    R.id.clActionCompile -> {
                        val dialog = Dialog(context, 0)
                        dialog.Workspace().Compilation().create(it)
                    }
                }
            }
        }
    }

    inner class ControlBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clControlBarParent)!!
        val image = itemView.findViewById<ImageView>(R.id.ivControlBar)!!
        val title = itemView.findViewById<TextView>(R.id.tvControlBar)!!
        val divider = itemView.findViewById<View>(R.id.dvControlBar)!!

        fun span() {
            try {
                Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: Bar.Control) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun activate(view: View, position: Int) {
            when (position) {
                0 -> {
                    if (Bar.Cart.list.size == 0) {
                        (view as ImageView).setImageResource(R.drawable.ic_explore_active)
                    }
                }
                1 -> {

                }
                2 -> {

                }
            }
        }
    }

    inner class ControlPanel {

        val clWorkspace = (context as Workspace).clWorkspace!!

        fun constraint(view: View) {
            TransitionManager.beginDelayedTransition(clWorkspace)
            Constraint.set(view, clWorkspace) {
                if (it) {
                    Compilation().card0?.isClickable = false
                    view.isSelected = !view.isSelected
                } else {
                    Compilation().card0?.isClickable = true
                    view.isSelected = !view.isSelected
                }
            }
        }

        inner class ControlPanelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val layout = itemView.findViewById<ConstraintLayout>(R.id.clControlPanel)!!
            val image = itemView.findViewById<ImageView>(R.id.ivControlPanel)!!
            val title = itemView.findViewById<TextView>(R.id.tvControlPanel)!!
            val divider = itemView.findViewById<View>(R.id.dvControlPanel)!!

            fun span() {
                Window.determineSpan(
                    context, divider, (context as Workspace).windowManager, Window.orientation, list.size) {}
            }

            fun bind(item: Panel.ControlPanel) {
                image.setImageResource(item.image)
                title.text = item.title
            }

            fun mapId(position: Int) {
                when (position) {
                    1 -> {
                        layout.id = R.id.clControlPanelLogout
                    }
                }
            }

            fun customize(position: Int) {
                when (position) {
                    0 -> {}
                    1 -> {
                        divider.visibility = View.INVISIBLE
                        image.setImageResource(R.drawable.ic_logout_active)
                    }
                }
            }

            fun onClick(view: View) {
                when (view.id) {
                    R.id.clControlPanelLogout -> {
                        view.setOnClickListener {
                            com.corespark.pccompiler.service.Auth.logOut(context) { complete ->
                                if (complete) {
                                    (context as Workspace).startActivity(Intent.launch(context, R.layout.activity_auth))
                                    context.finish()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    inner class Compilation {

        var card0: ConstraintLayout? = null
        var card1: ConstraintLayout? = null

        inner class CompilationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val layout = itemView.findViewById<ConstraintLayout>(R.id.clCompilation)!!
            val image = itemView.findViewById<ImageView>(R.id.ivCompilation)!!
            val title = itemView.findViewById<TextView>(R.id.tvCompilation)!!

            fun span() {
                try {
                    Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, 1) {}
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }

            fun bind(holder: CompilationViewHolder, item: Bar.Compilation, position: Int) {
                image.setImageResource(item.image)
                title.text = item.title
                Parameter.set(image, 64)

                when (position) {
                    0 -> {
                        if (card0 == null) {
                            card0 = holder.layout
                            card0?.id = R.id.cvCompilation0
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
                            card1 = holder.layout
                            card1?.id = R.id.cvCompilation1
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
                        R.id.cvCompilation0 -> {
                            if (!it.isSelected) {
                                card0?.isSelected = true
                                card1?.isSelected = false
                                complete(true)
                            } else {
                                card0?.isSelected = false
                                complete(false)
                            }
                        }
                        R.id.cvCompilation1 -> {
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

    inner class CartBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clCartBar)!!
        val image = itemView.findViewById<ImageView>(R.id.ivCartBar)!!
        val title = itemView.findViewById<TextView>(R.id.tvComponentCartBar)!!
        private val price = itemView.findViewById<TextView>(R.id.tvPriceCartBar)!!

        fun span() {
            try {
                Window.determineSpan(context, layout, (context as Compile).windowManager, Window.orientation, 1) {}
            } catch (e: IllegalStateException) {
                println(e.localizedMessage)
            }
        }

        fun bind(item: Bar.Cart) {
            image.setImageResource(item.image)
            title.text = item.component
            price.text = item.price
        }
    }

    inner class Component {

        var cmp0: ConstraintLayout? = null
        var cmp1: ConstraintLayout? = null

        inner class ComponentBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val layout = itemView.findViewById<ConstraintLayout>(R.id.clComponent)!!
            val image = itemView.findViewById<ImageView>(R.id.ivComponent)!!

            fun span() {
                Window.determineSpan(
                    context, layout, (context as Compile).windowManager, Window.orientation, list.size/2) {}
            }

            fun mapId(position: Int) {
                when (position) {
                    0 -> {
                        cmp0 = layout
                        cmp0?.id = R.id.componentBarItem0
                        cmp0?.setOnClickListener {
                            println(cmp0?.id)
                        }
                    }
                    1 -> {
                        cmp1 = layout
                        cmp1?.id = R.id.componentBarItem1
                        cmp1?.setOnClickListener {
                            println(cmp1?.id)
                        }
                    }
//                2 -> { image.id = R.id.componentBarItem2 }
//                3 -> { image.id = R.id.componentBarItem3 }
//                4 -> { image.id = R.id.componentBarItem4 }
//                5 -> { image.id = R.id.componentBarItem5 }
//                6 -> { image.id = R.id.componentBarItem6 }
//                7 -> { image.id = R.id.componentBarItem7 }
//                8 -> { image.id = R.id.componentBarItem8 }
//                9 -> { image.id = R.id.componentBarItem9 }
//                10 -> { image.id = R.id.componentBarItem10 }
//                11 -> { image.id = R.id.componentBarItem11 }
                }
            }

            fun bind(item: Bar.Component) {
                image.setImageResource(item.image)
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val parent = itemView.findViewById<ConstraintLayout>(R.id.clCompilationEmptyParent)!!
        private val layout = itemView.findViewById<ConstraintLayout>(R.id.clCompilationEmpty)!!
        val image = itemView.findViewById<ImageView>(R.id.ivCompilationEmpty)!!
        val title = itemView.findViewById<TextView>(R.id.tvCompilationEmpty)!!

        fun bind(item: Bar.Empty) {
            layout.layoutParams.width = Window.measureMultiDeviceDensity(Window.widthPx, 1)
            image.setImageResource(item.image)
            title.text = item.title
        }
    }
}