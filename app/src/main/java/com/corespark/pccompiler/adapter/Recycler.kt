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
import com.corespark.pccompiler.model.*
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.service.View.orientation
import com.corespark.pccompiler.service.View.width
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_workspace.*
import kotlinx.android.synthetic.main.activity_workspace.view.*
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application.Companion.attributes
import com.corespark.pccompiler.utility.Array


/**
 * @author Zachy Bazov.
 * @since 29/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Recycler(
    private val context: Context,
    private val list: MutableList<Any>,
    private val recyclerType: Int,
    private val componentType: Int?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            9 -> holder = Compilation().CompilationViewHolder(infalter.inflate(item_compilation_bar, container, false))
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
            9 -> {
                holder as Compilation.CompilationViewHolder
                holder.span()
                holder.bind(item as Bar.Compilation)
            }
            else -> {
                holder as EmptyViewHolder
                holder.span()
                holder.bind(item as Bar.Empty)
            }
        }
    }

    inner class TabBar(
        val clWorkspace: ConstraintLayout = (context as Workspace).clWorkspaceParent,
        var clTabWorkspace: ConstraintLayout? = null,
        var clTabCart: ConstraintLayout? = null
    ) {

        inner class TabBarViewHolder(
            itemView: View,
            val layout: ConstraintLayout = itemView.findViewById(R.id.clTabBarItem),
            private val image: ImageView = itemView.findViewById(R.id.ivTabBarItem),
            private val divider: View = itemView.findViewById(R.id.dvTabBarItem)
        ) : RecyclerView.ViewHolder(itemView) {

            fun span() = width(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

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
                with(clWorkspace) {
                    tvWorkspaceTitle.text = context.getString(R.string.text_my_compilations)
                    tvCartTitle.text = context.getString(R.string.text_my_cart)
                }
                Parameter.set(image, 64)
                when (position) {
                    0 -> image.setImageResource(R.drawable.ic_workspace_active)
                    1 -> {
                        image.setImageResource(R.drawable.ic_cart_active)
                        layout.removeView(divider)
//                        if (com.corespark.pccompiler.model.Compilation.isOnGoing) {
//                            Constraint.set(clTabCart!!, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
//                            Constraint.set(clTabCart!!, clWorkspace, clWorkspace.clFragCart)
//                            Constraint.set(clTabCart!!, clWorkspace) {}
//                            Constraint.set(clTabCart!!, clWorkspace.clFragTitle, clWorkspace.tvCartTitle)
//                        }
                    }
                }
            }

            fun onClick(view: View) {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clWorkspace)
                    when (view.id) {
                        clTabWorkspace?.id -> with(Constraint) {
                            set(clTabWorkspace!!, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                            set(clTabWorkspace!!, clWorkspace, clWorkspace.clFragWorkspace)
                            set(clTabWorkspace!!, clWorkspace) {}
                            set(clTabWorkspace!!, clWorkspace.clFragTitle, clWorkspace.tvWorkspaceTitle)
                        }
                        clTabCart?.id -> with(Constraint) {
                            set(clTabCart!!, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                            set(clTabCart!!, clWorkspace, clWorkspace.clFragCart)
                            set(clTabCart!!, clWorkspace) {}
                            set(clTabCart!!, clWorkspace.clFragTitle, clWorkspace.tvCartTitle)
                        }
                    }
                }
            }
        }
    }

    inner class ActionBar(val rvActionBar: RecyclerView = (context as Workspace).rvActionBar) {

        inner class ActionBarViewHolder(
            itemView: View,
            val layout: ConstraintLayout = itemView.findViewById(R.id.clActionBarItemParent),
            val image: ImageView = itemView.findViewById(R.id.ivActionBarItem),
            private val title: TextView = itemView.findViewById(R.id.tvActionBarItem),
            private val divider: View = itemView.findViewById(R.id.dvActionBarItem)
        ) : RecyclerView.ViewHolder(itemView) {

            fun span() = width(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

            fun bind(item: Bar.Action) {
                image.setImageResource(item.image)
                title.text = item.title
            }

            fun mapId(position: Int) = when (position) {
                0 -> {
                    layout.id = R.id.clActionUser
                    image.id = R.id.ivActionUser
                    title.id = R.id.tvActionUser
                }
                1 -> {
                    layout.id = R.id.clActionCompile
                    image.id = R.id.ivActionCompile
                }
                else -> layout.id = R.id.clActionEdit
            }

            fun customize(position: Int) = when (position) {
                0 -> title.text = User.username
                1 -> {}
                else -> layout.removeView(divider)
            }

            fun activate(view: View, position: Int) = when (position) {
                0 -> if (!view.isSelected) {
                    (view as ImageView).setImageResource(R.drawable.ic_profile_inactive)
                    view.isSelected = !view.isSelected
                } else {
                    (view as ImageView).setImageResource(R.drawable.ic_profile_active)
                    view.isSelected = !view.isSelected
                }
                1 -> (view as ImageView).setImageResource(R.drawable.ic_compile_active)
                else -> layout.isEnabled = false
            }

            fun onClick(view: View) = when (view.id) {
                R.id.clActionUser -> {
                    view.setOnClickListener {
                        ControlPanel().constraint(view)
                        activate(image, 0)
                    }
                }
                R.id.clActionCompile -> view.setOnClickListener {
                    Dialog(context).Workspace().Prelim().build()
                }
                else -> view.setOnClickListener {}
            }
        }
    }

    inner class ControlBarViewHolder(
        itemView: View,
        val layout: ConstraintLayout = itemView.findViewById(R.id.clControlBarItemParent),
        val image: ImageView = itemView.findViewById(R.id.ivControlBarItem),
        private val title: TextView = itemView.findViewById(R.id.tvControlBarItem)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = width(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Bar.Control) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun activate(view: View, position: Int) = when (position) {
            0 -> {
                if (Bar.Cart.list.size == 0) (view as ImageView).setImageResource(R.drawable.ic_explore_active)
                else {}
            }
            1 -> {}
            else -> {}
        }
    }

    inner class ControlPanel(private val clWorkspace: ConstraintLayout = (context as Workspace).clWorkspaceParent) {

        fun constraint(view: View) {
            TransitionManager.beginDelayedTransition(clWorkspace)
            Constraint.set(view, clWorkspace) { if (it) Compilation().activate(view, it) else Compilation().activate(view, it) }
        }

        inner class ControlPanelViewHolder(
            itemView: View,
            val layout: ConstraintLayout = itemView.findViewById(R.id.clControlPanelItemParent),
            private val image: ImageView = itemView.findViewById(R.id.ivControlPanelItem),
            private val title: TextView = itemView.findViewById(R.id.tvControlPanelItem),
            private val divider: View = itemView.findViewById(R.id.dvControlPanelItem)
        ) : RecyclerView.ViewHolder(itemView) {

            fun span() = width(context, divider, (context as Workspace).windowManager, orientation, list.size) {}

            fun bind(item: Panel.ControlPanel) {
                image.setImageResource(item.image)
                title.text = item.title
            }

            fun mapId(position: Int) = when (position) {
                0 -> {}
                else -> layout.id = R.id.clControlPanelLogout
            }

            fun customize(position: Int) = when (position) {
                0 -> {}
                else -> {
                    divider.visibility = View.INVISIBLE
                    image.setImageResource(R.drawable.ic_logout_active)
                }
            }

            fun onClick(view: View) = when (view.id) {
                R.id.clControlPanelLogout -> view.setOnClickListener {
                    com.corespark.pccompiler.service.Auth.logOut(context) { complete ->
                        if (complete) Intent.launch(context, activity_auth) {}
                        Intent.finish(context)
                    }
                }
                else -> {}
            }
        }
    }

    inner class Compilation(
        val clWorkspace: ConstraintLayout = (context as Workspace).clWorkspaceParent,
        val rvCompilationBar: RecyclerView = clWorkspace.rvCompilationBar,
        var clCompilationBar00: ConstraintLayout? = null,
        var clCompilationBar01: ConstraintLayout? = null,
        var clCompilationBar02: ConstraintLayout? = null,
        var firstCompilationPresented: View? = null,
        var secondCompilationPresented: View? = null,
        val clActionEdit: ConstraintLayout = ActionBar().rvActionBar.layoutManager?.getChildAt(2)
            ?.findViewById(R.id.clActionEdit)!!,
        val ivActionEdit: ImageView = ActionBar().rvActionBar.layoutManager?.getChildAt(2)
            ?.findViewById(R.id.ivActionBarItem)!!
    ) {

        fun activate(view: View, clickable: Boolean) = when(clickable) {
            false -> {
                clCompilationBar00?.isClickable = false
                view.isSelected = !view.isSelected
            }
            else -> {
                clCompilationBar00?.isClickable = true
                view.isSelected = !view.isSelected
            }
        }

        inner class CompilationViewHolder(
            itemView: View,
            private val parent: ConstraintLayout = itemView.findViewById(R.id.clCompilationItemParent),
            private val layout: CardView = itemView.findViewById(R.id.cvCompilationItem),
            private val image: ImageView = itemView.findViewById(R.id.ivCompilationItem),
            private val title: TextView = itemView.findViewById(R.id.tvCompilationItem)
        ) : RecyclerView.ViewHolder(itemView) {

            fun span() = width(context, layout, (context as Workspace).windowManager, orientation, 2) {}

            fun mapId(position: Int) = when (position) {
                0 -> {
                    if (clCompilationBar00 == null) {
                        clCompilationBar00 = parent
                        clCompilationBar00?.id = R.id.cvCompilation0
                    } else {}
                }
                1 -> {
                    if (clCompilationBar01 == null) {
                        clCompilationBar01 = parent
                        clCompilationBar01?.id = R.id.cvCompilation1
                    } else {}
                }
                else -> {
                    if (clCompilationBar02 == null) {
                        clCompilationBar02 = parent
                        clCompilationBar02?.id = R.id.cvCompilation2
                    } else {}
                }
            }

            fun bind(item: Bar.Compilation) {
                image.setImageResource(item.image)
                title.text = item.title
            }

            fun customize() {
                val views = arrayOf(parent, clWorkspace, clWorkspace.clForward, clWorkspace.clBackward)
                views.forEach { view -> onClick(view) }
            }

            private fun onClick(view: View) = when (view) {
                parent -> view.setOnClickListener {
                    firstCompilationPresented = rvCompilationBar.layoutManager!!.getChildAt(0)
                    secondCompilationPresented = rvCompilationBar.layoutManager!!.getChildAt(1)
                    when (parent) {
                        firstCompilationPresented -> when {
                            !firstCompilationPresented?.isSelected!! -> {
                                firstCompilationPresented?.setBackgroundColor(attributes.colorAccent)
                                secondCompilationPresented?.setBackgroundColor(attributes.colorCloud)
                                firstCompilationPresented?.isSelected = true
                                secondCompilationPresented?.isSelected = false
                                clActionEdit.isEnabled = true
                                ivActionEdit.setImageResource(R.drawable.ic_edit_active)
                            }
                            else -> {
                                firstCompilationPresented?.setBackgroundColor(attributes.colorCloud)
                                firstCompilationPresented?.isSelected = false
                                secondCompilationPresented?.isSelected = false
                                clActionEdit.isEnabled = false
                                ivActionEdit.setImageResource(R.drawable.ic_edit_inactive)
                            }
                        }
                        secondCompilationPresented -> when {
                            !secondCompilationPresented?.isSelected!! -> {
                                firstCompilationPresented?.setBackgroundColor(attributes.colorCloud)
                                secondCompilationPresented?.setBackgroundColor(attributes.colorAccent)
                                firstCompilationPresented?.isSelected = false
                                secondCompilationPresented?.isSelected = true
                                clActionEdit.isEnabled = true
                                ivActionEdit.setImageResource(R.drawable.ic_edit_active)
                            }
                            else -> {
                                secondCompilationPresented?.setBackgroundColor(attributes.colorCloud)
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
                else -> view.setOnClickListener {
                    (rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(true)
                    rvCompilationBar.smoothScrollToPosition(2)
                    Handler().postDelayed({
                        (rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(false)
                    }, 1000)
                }
            }
        }
    }

    inner class CartBarViewHolder(
        itemView: View,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clCartBarItemParent),
        private val image: ImageView = itemView.findViewById(R.id.ivCartBarItem),
        private val title: TextView = itemView.findViewById(R.id.tvCartBarItemComponent),
        private val price: TextView = itemView.findViewById(R.id.tvCartBarItemPrice)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = width(context, layout, (context as Compile).windowManager, orientation, 1) {}

        fun bind(item: Bar.Cart) {
            image.setImageResource(item.image)
            title.text = item.component
            price.text = item.price
        }
    }

    inner class Component(
        val clCompile: ConstraintLayout = (context as Compile).clCompileParent,
        val rvComponentBar: RecyclerView = (context as Compile).rvComponentBar,
        val rvComponent: RecyclerView = (context as Compile).rvComponent
    ) {

        inner class ComponentBarViewHolder(
            itemView: View,
            val layout: ConstraintLayout = itemView.findViewById(R.id.clComponentBarItemParent),
            private val image: ImageView = itemView.findViewById(R.id.ivComponentBarItem)
        ) : RecyclerView.ViewHolder(itemView) {

            fun span() = width(context, layout, (context as Compile).windowManager, orientation, list.size / 2) {}

            fun bind(item: Bar.Component) = image.setImageResource(item.image)

            fun customize() {
                rvComponentBar.layoutManager?.getChildAt(0)?.setBackgroundColor(attributes.colorAccent)
                Factory.mark(rvComponentBar)
            }

            fun onClick(view: View) = view.setOnClickListener {
                TransitionManager.beginDelayedTransition(clCompile)
                Factory.focus(rvComponentBar, adapterPosition)
                val componentList = Array.componentsArray[adapterPosition]
                rvComponent.adapter = Recycler(context, componentList, 7, adapterPosition)
            }
        }

        inner class ComponentViewHolder(
            itemView: View,
            private val layout: ConstraintLayout = itemView.findViewById(R.id.clComponentItem),
            private val image: ImageView = itemView.findViewById(R.id.ivComponentItem),
            private val title: TextView = itemView.findViewById(R.id.tvComponentItem),
            val more: ImageView = itemView.findViewById(R.id.ivComponentItemMore),
            private val price: TextView = itemView.findViewById(R.id.tvComponentItemPrice)
        ) : RecyclerView.ViewHolder(itemView) {

            fun span() {
                width(context, layout, (context as Compile).windowManager, orientation, 1) {}
                width(context, title, context.windowManager, orientation, 2) {}
            }

            fun bind(item: com.corespark.pccompiler.model.Component) {
                Bind.componentImage(image, componentType!!)
                title.text = String.format("%s %s", item.manufaturer, item.component)
                price.text = String.format("$%s", item.price)
                more.setImageResource(R.drawable.ic_more_active)
            }

            fun customize(item: com.corespark.pccompiler.model.Component) {
                Parameter.set(image, 48)
                Factory.styleAsTable(layout, adapterPosition)
                Factory.mark(clCompile, item, layout, componentType!!, adapterPosition)
            }

            fun onClick(view: View, item: com.corespark.pccompiler.model.Component) = view.setOnClickListener {
                rvComponent.scrollToPosition(adapterPosition)
                val oldPositionList = Array.oldPositionsArray[componentType!!]
                val oldPosition = Factory.oldPosition(componentType, oldPositionList, adapterPosition)
                Dialog(context).Compile().Overview().build(componentType, item, adapterPosition, oldPosition)
            }
        }
    }

    inner class EmptyViewHolder(
        itemView: View,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clItemEmpty),
        private val image: ImageView = itemView.findViewById(R.id.ivItemEmpty),
        private val title: TextView = itemView.findViewById(R.id.tvItemEmpty)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = width(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Bar.Empty) {
            image.setImageResource(item.image)
            title.text = item.title
        }
    }
}
