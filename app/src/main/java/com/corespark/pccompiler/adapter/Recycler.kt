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
import com.corespark.pccompiler.service.*
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_workspace.*
import kotlinx.android.synthetic.main.activity_workspace.view.*


/**
 * @author Zachy Bazov.
 * @since 29/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Recycler(
    val context: Context,
    private val list: MutableList<Any>,
    val type: Int,
    val component: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var dialog: Dialog

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        val infalter = LayoutInflater.from(context)
        when (type) {
            0 -> holder = TabBar().TabBarViewHolder(
                infalter.inflate(R.layout.item_tab_bar, container, false))
            1 -> holder = ActionBarViewHolder(
                infalter.inflate(R.layout.item_action_bar, container, false))
            2 -> holder = ControlBarViewHolder(
                infalter.inflate(R.layout.item_control_bar, container, false))
            3 -> holder = ControlPanel().ControlPanelViewHolder(
                infalter.inflate(R.layout.item_control_panel, container, false))
            4 -> holder = Compilation().CompilationViewHolder(
                infalter.inflate(R.layout.item_compilation_bar, container, false))
            5 -> holder = CartBarViewHolder(
                infalter.inflate(R.layout.item_cart_bar, container, false))
            6 -> holder = Component().ComponentBarViewHolder(
                infalter.inflate(R.layout.item_component_bar, container, false))
            7 -> holder = Component().ComponentViewHolder(
                infalter.inflate(R.layout.item_component, container, false))
            else -> holder = EmptyViewHolder(
                infalter.inflate(R.layout.item_empty, container, false))
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (type) {
            0 -> {
                holder as TabBar.TabBarViewHolder
                holder.span()
                holder.mapId(position)
                holder.bind(item as Bar.Tab)
                holder.customize(position)
                holder.onClick(holder.layout)
            }
            1 -> {
                holder as ActionBarViewHolder
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
                Algorithm.style(holder.layout, position)
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
                holder.onClick(holder.layout, position)
            }
            7 -> {
                holder as Component.ComponentViewHolder
                holder.span()
                holder.bind(item as com.corespark.pccompiler.model.Component)
                Algorithm.style(holder.layout, position)
                holder.onClick(holder.more)
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
        var ivTabWorkspace: ImageView? = null
        var ivTabCart: ImageView? = null

        inner class TabBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val parent = itemView.findViewById<ConstraintLayout>(R.id.clTabBarItemParent)!!
            val layout = itemView.findViewById<ConstraintLayout>(R.id.clTabBarItem)!!
            val image = itemView.findViewById<ImageView>(R.id.ivTabBarItem)!!
            val divider = itemView.findViewById<View>(R.id.dvTabBarItem)!!

            fun span() {
                Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
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
                clWorkspace.tvWorkspaceTitle.text = context.getString(R.string.text_my_compilations)
                clWorkspace.tvCartTitle.text = context.getString(R.string.text_my_cart)
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

    inner class ActionBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clActionBarItemParent)!!
        val image = itemView.findViewById<ImageView>(R.id.ivActionBarItem)!!
        val title = itemView.findViewById<TextView>(R.id.tvActionBarItem)!!
        val divider = itemView.findViewById<View>(R.id.dvActionBarItem)!!

        fun span() {
            Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
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
                        dialog = Dialog(context, null)
                        dialog.Workspace().Compilation().build()
                    }
                }
            }
        }
    }

    inner class ControlBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clControlBarItemParent)!!
        val image = itemView.findViewById<ImageView>(R.id.ivControlBarItem)!!
        val title = itemView.findViewById<TextView>(R.id.tvControlBarItem)!!
        val divider = itemView.findViewById<View>(R.id.dvControlBarItem)!!

        fun span() {
            Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
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
            val image = itemView.findViewById<ImageView>(R.id.ivControlPanelItem)!!
            val title = itemView.findViewById<TextView>(R.id.tvControlPanelItem)!!
            val divider = itemView.findViewById<View>(R.id.dvControlPanelItem)!!

            fun span() {
                Window.determineSpan(context, divider, (context as Workspace).windowManager, Window.orientation, list.size) {}
            }

            fun bind(item: Panel.ControlPanel) {
                image.setImageResource(item.image)
                title.text = item.title
            }

            fun mapId(position: Int) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        layout.id = R.id.clControlPanelLogout
                    }
                }
            }

            fun customize(position: Int) {
                when (position) {
                    0 -> {

                    }
                    1 -> {
                        divider.visibility = View.INVISIBLE
                        image.setImageResource(R.drawable.ic_logout_active)
                    }
                }
            }

            fun onClick(view: View) {
                view.setOnClickListener {
                    when (view.id) {
                        R.id.clControlPanelLogout -> {
                            com.corespark.pccompiler.service.Auth.logOut(context) { complete ->
                                if (complete) {
                                    Intent.launch(context, R.layout.activity_auth) {}
                                    Intent.finish(context)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    inner class Compilation {

        var clCompilationBar00: ConstraintLayout? = null
        var clCompilationBar01: ConstraintLayout? = null

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

            val layout = itemView.findViewById<ConstraintLayout>(R.id.clCompilationItemParent)!!
            val image = itemView.findViewById<ImageView>(R.id.ivCompilationItem)!!
            val title = itemView.findViewById<TextView>(R.id.tvCompilationItem)!!

            fun span() {
                Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, 1) {}
            }

            fun mapId(position: Int) {
                when (position) {
                    0 -> {
                        if (clCompilationBar00 == null) {
                            clCompilationBar00 = layout
                            clCompilationBar00?.id = R.id.cvCompilation0
                        }
                    }
                    1 -> {
                        if (clCompilationBar01 == null) {
                            clCompilationBar01 = layout
                            clCompilationBar01?.id = R.id.cvCompilation1
                        }
                    }
                }
            }

            fun bind(item: Bar.Compilation) {
                image.setImageResource(item.image)
                title.text = item.title
            }
        }
    }

    inner class CartBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val layout = itemView.findViewById<ConstraintLayout>(R.id.clCartBarItemParent)!!
        val image = itemView.findViewById<ImageView>(R.id.ivCartBarItem)!!
        val title = itemView.findViewById<TextView>(R.id.tvCartBarItemComponent)!!
        private val price = itemView.findViewById<TextView>(R.id.tvCartBarItemPrice)!!

        fun span() {
            Window.determineSpan(context, layout, (context as Compile).windowManager, Window.orientation, 1) {}
        }

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
            val image = itemView.findViewById<ImageView>(R.id.ivComponentBarItem)!!

            fun span() {
                Window.determineSpan(
                    context, layout, (context as Compile).windowManager, Window.orientation, list.size / 2) {}
            }

            fun bind(item: Bar.Component) {
                image.setImageResource(item.image)
                rvComponentBar.layoutManager?.getChildAt(0)?.setBackgroundColor(Compiler.colors.colorAccent)
            }

            fun onClick(view: View, position: Int) {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clCompile)
                    Algorithm.focus(rvComponentBar, position)
                    val list = Compiler.componentsList[position]
                    rvComponent.adapter = Recycler(context, list, 7, position)
                }
            }
        }

        inner class ComponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val parent = itemView.findViewById<ConstraintLayout>(R.id.clComponentItemParent)!!
            val layout = itemView.findViewById<ConstraintLayout>(R.id.clComponentItem)!!
            val image = itemView.findViewById<ImageView>(R.id.ivComponentItem)!!
            val title = itemView.findViewById<TextView>(R.id.tvComponentItem)!!
            val more = itemView.findViewById<ImageView>(R.id.ivComponentItemMore)!!
            private val price = itemView.findViewById<TextView>(R.id.tvComponentItemPrice)!!

            fun span() {
                Window.determineSpan(context, layout, (context as Compile).windowManager, Window.orientation, 1) {}
                Window.determineSpan(context, title, context.windowManager, Window.orientation, 2) {}
            }

            fun bind(item: com.corespark.pccompiler.model.Component) {
                Parameter.set(image, 48)
                more.setImageResource(R.drawable.ic_more_active)
                when (component) {
                    0 -> {
                        image.setImageResource(R.mipmap.ic_cpu)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    1 -> {
                        image.setImageResource(R.mipmap.ic_optdrive)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    2 -> {
                        image.setImageResource(R.mipmap.ic_cooler)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    3 -> {
                        image.setImageResource(R.mipmap.ic_graphiccard)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    4 -> {
                        image.setImageResource(R.mipmap.ic_motherboard)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    5 -> {
                        image.setImageResource(R.mipmap.ic_soundcard)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    6 -> {
                        image.setImageResource(R.mipmap.ic_memory)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    7 -> {
                        image.setImageResource(R.mipmap.ic_powersupply)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    8 -> {
                        image.setImageResource(R.mipmap.ic_storage)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    9 -> {
                        image.setImageResource(R.mipmap.ic_case)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    10 -> {
                        image.setImageResource(R.mipmap.ic_extstorage)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                    11 -> {
                        image.setImageResource(R.mipmap.ic_opsystem)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                }
            }

            fun onClick(view: View) {
                view.setOnClickListener {
                    val item = list[adapterPosition]
                    dialog = Dialog(context, item as com.corespark.pccompiler.model.Component?)
                    dialog.Compile().Overview().build(component)
                }
            }
        }
    }

    inner class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val parent = itemView.findViewById<ConstraintLayout>(R.id.clItemEmptyParent)!!
        val layout = itemView.findViewById<ConstraintLayout>(R.id.clItemEmpty)!!
        val image = itemView.findViewById<ImageView>(R.id.ivItemEmpty)!!
        val title = itemView.findViewById<TextView>(R.id.tvItemEmpty)!!

        fun span() {
            Window.determineSpan(context, layout, (context as Workspace).windowManager, Window.orientation, list.size) {}
        }

        fun bind(item: Bar.Empty) {
            image.setImageResource(item.image)
            title.text = item.title
        }
    }
}
