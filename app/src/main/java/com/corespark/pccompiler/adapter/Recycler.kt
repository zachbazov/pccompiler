package com.corespark.pccompiler.adapter

import android.content.Context
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.corespark.pccompiler.R.layout.*
import com.corespark.pccompiler.activity.Compile
import com.corespark.pccompiler.activity.Workspace
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.*
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.service.View.orientation
import com.corespark.pccompiler.service.View.span
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_workspace.*
import kotlinx.android.synthetic.main.activity_workspace.view.*
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 29/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Recycler(val context: Context, private val list: MutableList<Any>, private val recyclerType: Int, val componentType: Int?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val dialog = Dialog(context)

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        val infalter = LayoutInflater.from(context)
        when (recyclerType) {
            0 -> holder = TabBar().TabBarViewHolder(infalter.inflate(item_tab_bar, container, false))
            1 -> holder = ActionBar().ActionBarViewHolder(infalter.inflate(item_action_bar, container, false))
            2 -> holder = ControlBarViewHolder(infalter.inflate(item_control_bar, container, false))
            3 -> holder = ControlPanel().ControlPanelViewHolder(infalter.inflate(item_control_panel, container, false))
            4 -> holder = Compilation().CompilationViewHolder(infalter.inflate(item_compilation_bar, container, false))
            5 -> holder = CartBarViewHolder(infalter.inflate(item_cart_bar, container, false))
            6 -> holder = Component().ComponentBarViewHolder(infalter.inflate(item_component_bar, container, false))
            7 -> holder = Component().ComponentViewHolder(infalter.inflate(item_component, container, false))
            else -> holder = EmptyViewHolder(infalter.inflate(item_empty, container, false))
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (recyclerType) {
            0 -> {
                holder as TabBar.TabBarViewHolder
                holder.span()
                holder.mapId(position)
                holder.bind(item as Bar.Tab)
                holder.customize(position)
                holder.onClick(holder.layout)
            }
            1 -> {
                holder as ActionBar.ActionBarViewHolder
                holder.span()
                holder.bind(item as Bar.Action)
                holder.mapId(position)
                holder.customize(position)
                holder.activate(holder.image, position)
                holder.onClick(holder.layout)
            }
            2 -> {
                holder as ControlBarViewHolder
                holder.span()
                holder.bind(item as Bar.Control)
                holder.activate(holder.image, position)
            }
            3 -> {
                holder as ControlPanel.ControlPanelViewHolder
                holder.span()
                holder.bind(item as Panel.ControlPanel)
                holder.mapId(position)
                holder.customize(position)
                holder.onClick(holder.layout)
            }
            4 -> {
                holder as Compilation.CompilationViewHolder
                holder.span()
                holder.mapId(position)
                holder.bind(item as Bar.Compilation)
                holder.customize()
            }
            5 -> {
                holder as CartBarViewHolder
                holder.span()
                holder.bind(item as Bar.Cart)
            }
            6 -> {
                holder as Component.ComponentBarViewHolder
                holder.span()
                holder.bind(item as Bar.Component)
                holder.customize()
                holder.onClick(holder.layout)
            }
            7 -> {
                holder as Component.ComponentViewHolder
                holder.span()
                holder.bind(item as com.corespark.pccompiler.model.Component)
                holder.customize(item)
                holder.onClick(holder.more, item)
            }
            else -> {
                holder as EmptyViewHolder
                holder.span()
                holder.bind(item as Bar.Empty)
            }
        }
    }

    inner class TabBar {

        val clWorkspace = (context as Workspace).clWorkspaceParent!!
        var clTabWorkspace: ConstraintLayout? = null
        var clTabCart: ConstraintLayout? = null

        inner class TabBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val parent = itemView.findViewById<ConstraintLayout>(R.id.clTabBarItemParent)!!
            val layout = itemView.findViewById<ConstraintLayout>(R.id.clTabBarItem)!!
            private val image = itemView.findViewById<ImageView>(R.id.ivTabBarItem)!!
            private val divider = itemView.findViewById<View>(R.id.dvTabBarItem)!!

            fun span() = span(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

            fun mapId(position: Int) = when (position) {
                0 -> {
                    clTabWorkspace = layout
                    layout.id = R.id.clTabWorkspace
                }
                else -> {
                    clTabCart = layout
                    layout.id = R.id.clTabCart
                }
            }

            fun bind(item: Bar.Tab) = image.setImageResource(item.image)

            fun customize(position: Int) {
                clWorkspace.tvWorkspaceTitle.text = context.getString(R.string.text_my_compilations)
                clWorkspace.tvCartTitle.text = context.getString(R.string.text_my_cart)
                Parameter.set(image, 64)
                when (position) {
                    0 -> image.setImageResource(R.drawable.ic_workspace_active)
                    1 -> {
                        image.setImageResource(R.drawable.ic_cart_active)
                        layout.removeView(divider)
                        if (com.corespark.pccompiler.model.Compilation.isRunning) {
                            Constraint.set(clTabCart!!, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                            Constraint.set(clTabCart!!, clWorkspace, clWorkspace.clFragCart)
                            Constraint.set(clTabCart!!, clWorkspace) {}
                            Constraint.set(clTabCart!!, clWorkspace.clFragTitle, clWorkspace.tvCartTitle)
                        } else {}
                    }
                }
            }

            fun onClick(view: View) {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clWorkspace)
                    when (view.id) {
                        clTabWorkspace?.id -> {
                            Constraint.set(clTabWorkspace!!, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                            Constraint.set(clTabWorkspace!!, clWorkspace, clWorkspace.clFragWorkspace)
                            Constraint.set(clTabWorkspace!!, clWorkspace) {}
                            Constraint.set(clTabWorkspace!!, clWorkspace.clFragTitle, clWorkspace.tvWorkspaceTitle)
                        }
                        clTabCart?.id -> {
                            Constraint.set(clTabCart!!, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                            Constraint.set(clTabCart!!, clWorkspace, clWorkspace.clFragCart)
                            Constraint.set(clTabCart!!, clWorkspace) {}
                            Constraint.set(clTabCart!!, clWorkspace.clFragTitle, clWorkspace.tvCartTitle)
                        }
                    }
                }
            }
        }
    }

    inner class ActionBar {

        val rvActionBar = (context as Workspace).rvActionBar!!

        inner class ActionBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val layout = itemView.findViewById<ConstraintLayout>(R.id.clActionBarItemParent)!!
            val image = itemView.findViewById<ImageView>(R.id.ivActionBarItem)!!
            private val title = itemView.findViewById<TextView>(R.id.tvActionBarItem)!!
            private val divider = itemView.findViewById<View>(R.id.dvActionBarItem)!!

            fun span() = span(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

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
                    2 -> layout.id = R.id.clActionEdit
                }
            }

            fun customize(position: Int) {
                when (position) {
                    0 -> title.text = User.username
                    1 -> {}
                    2 -> layout.removeView(divider)
                }
            }

            fun activate(view: View, position: Int) {
                when (position) {
                    0 -> if (!view.isSelected) {
                        (view as ImageView).setImageResource(R.drawable.ic_profile_inactive)
                        view.isSelected = !view.isSelected
                    } else {
                        (view as ImageView).setImageResource(R.drawable.ic_profile_active)
                        view.isSelected = !view.isSelected
                    }
                    1 -> (view as ImageView).setImageResource(R.drawable.ic_compile_active)
                    2 -> layout.isEnabled = false
                }
            }

            fun onClick(view: View) {
                view.setOnClickListener {
                    when (view.id) {
                        R.id.clActionUser -> {
                            ControlPanel().constraint(view)
                            activate(image, 0)
                        }
                        R.id.clActionCompile -> dialog.Workspace().Prelim().build()
                        R.id.clActionEdit -> view.setOnClickListener {
                            println("bla")
                        }
                    }
                }
            }
        }
    }

    inner class ControlBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clControlBarItemParent)!!
        val image = itemView.findViewById<ImageView>(R.id.ivControlBarItem)!!
        private val title = itemView.findViewById<TextView>(R.id.tvControlBarItem)!!

        fun span() = span(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Bar.Control) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun activate(view: View, position: Int) {
            when (position) {
                0 -> if (Bar.Cart.list.size == 0) (view as ImageView).setImageResource(R.drawable.ic_explore_active)
                1 -> {}
                2 -> {}
            }
        }
    }

    inner class ControlPanel {

        private val clWorkspace = (context as Workspace).clWorkspaceParent!!

        fun constraint(view: View) {
            TransitionManager.beginDelayedTransition(clWorkspace)
            Constraint.set(view, clWorkspace) {
                if (it) Compilation().activate(view, it)
                else Compilation().activate(view, it)
            }
        }

        inner class ControlPanelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val layout = itemView.findViewById<ConstraintLayout>(R.id.clControlPanelItemParent)!!
            private val image = itemView.findViewById<ImageView>(R.id.ivControlPanelItem)!!
            private val title = itemView.findViewById<TextView>(R.id.tvControlPanelItem)!!
            private val divider = itemView.findViewById<View>(R.id.dvControlPanelItem)!!

            fun span() = span(context, divider, (context as Workspace).windowManager, orientation, list.size) {}

            fun bind(item: Panel.ControlPanel) {
                image.setImageResource(item.image)
                title.text = item.title
            }

            fun mapId(position: Int) {
                when (position) {
                    0 -> {}
                    1 -> layout.id = R.id.clControlPanelLogout
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
                view.setOnClickListener {
                    when (view.id) {
                        R.id.clControlPanelLogout -> com.corespark.pccompiler.service.Auth.logOut(context) { complete ->
                            if (complete) Intent.launch(context, activity_auth) {}
                            Intent.finish(context)
                        }
                    }
                }
            }
        }
    }

    inner class Compilation {

        val clWorkspace = (context as Workspace).clWorkspaceParent!!
        val rvCompilationBar = clWorkspace.rvCompilationBar!!
        var clCompilationBar00: ConstraintLayout? = null
        var clCompilationBar01: ConstraintLayout? = null
        var clCompilationBar02: ConstraintLayout? = null

        var firstCompilationPresented: View? = null
        var secondCompilationPresented: View? = null

        val clActionEdit = ActionBar().rvActionBar.layoutManager?.getChildAt(2)
            ?.findViewById<ConstraintLayout>(R.id.clActionEdit)!!
        val ivActionEdit = ActionBar().rvActionBar.layoutManager?.getChildAt(2)
            ?.findViewById<ImageView>(R.id.ivActionBarItem)!!

        fun activate(view: View, clickable: Boolean) {
            if (clickable) {
                clCompilationBar00?.isClickable = false
                view.isSelected = !view.isSelected
            } else {
                clCompilationBar00?.isClickable = true
                view.isSelected = !view.isSelected
            }
        }

        inner class CompilationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val parent = itemView.findViewById<ConstraintLayout>(R.id.clCompilationItemParent)!!
            val layout = itemView.findViewById<CardView>(R.id.cvCompilationItem)!!
            private val image = itemView.findViewById<ImageView>(R.id.ivCompilationItem)!!
            private val title = itemView.findViewById<TextView>(R.id.tvCompilationItem)!!

            fun span() = span(context, layout, (context as Workspace).windowManager, orientation, 2) {}

            fun mapId(position: Int) {
                when (position) {
                    0 -> if (clCompilationBar00 == null) {
                        clCompilationBar00 = parent
                        clCompilationBar00?.id = R.id.cvCompilation0
                    }
                    1 -> if (clCompilationBar01 == null) {
                        clCompilationBar01 = parent
                        clCompilationBar01?.id = R.id.cvCompilation1
                    }
                    2 -> if (clCompilationBar02 == null) {
                        clCompilationBar02 = parent
                        clCompilationBar02?.id = R.id.cvCompilation2
                    }
                }
            }

            fun bind(item: Bar.Compilation) {
                image.setImageResource(item.image)
                title.text = item.title
            }

            fun customize() {
                val views = arrayOf(parent, clWorkspace, clWorkspace.clForward, clWorkspace.clBackward)
                for (view in views) onClick(view)
            }

            fun onClick(view: View) {
                when (view) {
                    parent -> view.setOnClickListener {
                        firstCompilationPresented = rvCompilationBar.layoutManager!!.getChildAt(0)
                        secondCompilationPresented = rvCompilationBar.layoutManager!!.getChildAt(1)
                        when (parent) {
                            firstCompilationPresented -> when {
                                !firstCompilationPresented?.isSelected!! -> {
                                    firstCompilationPresented?.setBackgroundColor(Compiler.colors.colorAccent)
                                    secondCompilationPresented?.setBackgroundColor(Compiler.colors.colorCloud)
                                    firstCompilationPresented?.isSelected = true
                                    secondCompilationPresented?.isSelected = false
                                    clActionEdit.isEnabled = true
                                    ivActionEdit.setImageResource(R.drawable.ic_edit_active)
                                }
                                else -> {
                                    firstCompilationPresented?.setBackgroundColor(Compiler.colors.colorCloud)
                                    firstCompilationPresented?.isSelected = false
                                    secondCompilationPresented?.isSelected = false
                                    clActionEdit.isEnabled = false
                                    ivActionEdit.setImageResource(R.drawable.ic_edit_inactive)
                                }
                            }
                            secondCompilationPresented -> when {
                                !secondCompilationPresented?.isSelected!! -> {
                                    firstCompilationPresented?.setBackgroundColor(Compiler.colors.colorCloud)
                                    secondCompilationPresented?.setBackgroundColor(Compiler.colors.colorAccent)
                                    firstCompilationPresented?.isSelected = false
                                    secondCompilationPresented?.isSelected = true
                                    clActionEdit.isEnabled = true
                                    ivActionEdit.setImageResource(R.drawable.ic_edit_active)
                                }
                                else -> {
                                    secondCompilationPresented?.setBackgroundColor(Compiler.colors.colorCloud)
                                    firstCompilationPresented?.isSelected = false
                                    secondCompilationPresented?.isSelected = false
                                    clActionEdit.isEnabled = false
                                    ivActionEdit.setImageResource(R.drawable.ic_edit_inactive)
                                }
                            }
                        }
                    }
                    clWorkspace.clBackward -> view.setOnClickListener {
                        (rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(true)
                        rvCompilationBar.smoothScrollToPosition(0)
                        Handler().postDelayed({
                            (rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(false)
                        }, 1000)
                    }
                    clWorkspace.clForward -> view.setOnClickListener {
                        (rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(true)
                        rvCompilationBar.smoothScrollToPosition(2)
                        Handler().postDelayed({
                            (rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(false)
                        }, 1000)
                    }
                }
            }
        }
    }

    inner class CartBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clCartBarItemParent)!!
        private val image = itemView.findViewById<ImageView>(R.id.ivCartBarItem)!!
        private val title = itemView.findViewById<TextView>(R.id.tvCartBarItemComponent)!!
        private val price = itemView.findViewById<TextView>(R.id.tvCartBarItemPrice)!!

        fun span() = span(context, layout, (context as Compile).windowManager, orientation, 1) {}

        fun bind(item: Bar.Cart) {
            image.setImageResource(item.image)
            title.text = item.component
            price.text = item.price
        }
    }

    inner class Component {

        val clCompile = (context as Compile).clCompileParent!!
        val rvComponentBar = (context as Compile).rvComponentBar!!
        val rvComponent = (context as Compile).rvComponent!!

        inner class ComponentBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val layout = itemView.findViewById<ConstraintLayout>(R.id.clComponentBarItemParent)!!
            private val image = itemView.findViewById<ImageView>(R.id.ivComponentBarItem)!!

            fun span() = span(context, layout, (context as Compile).windowManager, orientation, list.size/2) {}

            fun bind(item: Bar.Component) = image.setImageResource(item.image)

            fun customize() {
                rvComponentBar.layoutManager?.getChildAt(0)?.setBackgroundColor(Compiler.colors.colorAccent)
                GlobalFunction.mark(rvComponentBar)
            }

            fun onClick(view: View) {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clCompile)
                    GlobalFunction.focus(rvComponentBar, adapterPosition)
                    val componentList = Compiler.componentsList[adapterPosition]
                    rvComponent.adapter = Recycler(context, componentList, 7, adapterPosition)
                }
            }
        }

        inner class ComponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val parent = itemView.findViewById<ConstraintLayout>(R.id.clComponentItemParent)!!
            val layout = itemView.findViewById<ConstraintLayout>(R.id.clComponentItem)!!
            private val image = itemView.findViewById<ImageView>(R.id.ivComponentItem)!!
            private val title = itemView.findViewById<TextView>(R.id.tvComponentItem)!!
            val more = itemView.findViewById<ImageView>(R.id.ivComponentItemMore)!!
            private val price = itemView.findViewById<TextView>(R.id.tvComponentItemPrice)!!

            fun span() {
                span(context, layout, (context as Compile).windowManager, orientation, 1) {}
                span(context, title, context.windowManager, orientation, 2) {}
            }

            fun bind(item: com.corespark.pccompiler.model.Component) {
                DataBinding.componentImage(image, componentType!!)
                title.text = String.format("%s %s", item.manufaturer, item.component)
                price.text = String.format("$%s", item.price)
                more.setImageResource(R.drawable.ic_more_active)
            }

            fun customize(item: com.corespark.pccompiler.model.Component) {
                Parameter.set(image, 48)
                GlobalFunction.styleAsTable(layout, adapterPosition)
                GlobalFunction.mark(clCompile, item, layout, componentType!!, adapterPosition)
            }

            fun onClick(view: View, item: com.corespark.pccompiler.model.Component) {
                view.setOnClickListener {
                    rvComponent.scrollToPosition(adapterPosition)
                    val oldPositionList = Compiler.oldPositionsList[componentType!!]
                    val oldPosition = GlobalFunction.oldPosition(componentType, oldPositionList, adapterPosition)
                    dialog.Compile().Overview().build(componentType, item, adapterPosition, oldPosition)
                }
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val parent = itemView.findViewById<ConstraintLayout>(R.id.clItemEmptyParent)!!
        val layout = itemView.findViewById<ConstraintLayout>(R.id.clItemEmpty)!!
        private val image = itemView.findViewById<ImageView>(R.id.ivItemEmpty)!!
        private val title = itemView.findViewById<TextView>(R.id.tvItemEmpty)!!

        fun span() = span(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Bar.Empty) {
            image.setImageResource(item.image)
            title.text = item.title
        }
    }
}
