package com.corespark.pccompiler.adapter

import android.content.Context
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager.beginDelayedTransition
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
import com.corespark.pccompiler.service.View.widthSpan
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_workspace.*
import kotlinx.android.synthetic.main.activity_workspace.view.*
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application.Companion.attributes
import com.corespark.pccompiler.model.Compilation.clearAll
import com.corespark.pccompiler.model.Compilation.isCompiling
import com.corespark.pccompiler.service.Auth.logOut
import com.corespark.pccompiler.service.Bind.componentImage
import com.corespark.pccompiler.service.Factory.focus
import com.corespark.pccompiler.service.Factory.mark
import com.corespark.pccompiler.service.Factory.style
import com.corespark.pccompiler.service.Intent.finish
import com.corespark.pccompiler.service.Intent.launch
import com.corespark.pccompiler.service.Layout.fetchLayout
import com.corespark.pccompiler.service.Parameter.layoutParams
import com.corespark.pccompiler.service.View.density
import com.corespark.pccompiler.service.View.heightSpan
import com.corespark.pccompiler.utility.Array.Companion.componentsArray
import com.corespark.pccompiler.utility.Array.Companion.oldPositionsArray
import kotlinx.android.synthetic.main.activity_compile.view.*


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
    private val state: Int,
    private val type: Int?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = list.size

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        when (state) {
            0 -> holder = TabBarViewHolder(fetchLayout(context, state)!!)
            1 -> holder = ActionBarViewHolder(fetchLayout(context, state)!!)
            2 -> holder = ControlBarViewHolder(fetchLayout(context, state)!!)
            3 -> holder = ControlPanelViewHolder(fetchLayout(context, state)!!)
            4 -> holder = CompilationViewHolder(fetchLayout(context, state)!!)
            5 -> holder = CartBarViewHolder(fetchLayout(context, state)!!)
            6 -> holder = ComponentBarViewHolder(fetchLayout(context, state)!!)
            7 -> holder = ComponentViewHolder(fetchLayout(context, state)!!)
            else -> holder = EmptyViewHolder(fetchLayout(context, state)!!)
        }
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (state) {
            0 -> {
                holder as TabBarViewHolder
                holder.span()
                holder.mapId()
                holder.bind(item as Bar.Tab)
                holder.customize()
                holder.onClick()
            }
            1 -> {
                holder as ActionBarViewHolder
                holder.span()
                holder.bind(item as Bar.Action)
                holder.mapId()
                holder.customize()
                holder.activate()
                holder.onClick()
            }
            2 -> {
                holder as ControlBarViewHolder
                holder.span()
                holder.bind(item as Bar.Control)
                holder.activate()
                holder.onClick()
            }
            3 -> {
                holder as ControlPanelViewHolder
                holder.span()
                holder.bind(item as Panel.ControlPanel)
                holder.mapId()
                holder.customize()
                holder.onClick()
            }
            4 -> {
                holder as CompilationViewHolder
                holder.span()
                holder.mapId()
                holder.bind(item as Bar.Compilation)
                holder.customize()
            }
            5 -> {
                holder as CartBarViewHolder
                holder.span()
                holder.bind(item as Component)
                holder.customize()
                holder.onClick()
            }
            6 -> {
                holder as ComponentBarViewHolder
                holder.bind(item as Bar.Component)
                holder.customize()
                holder.onClick()
            }
            7 -> {
                holder as ComponentViewHolder
                holder.span()
                holder.bind(item as Component)
                holder.customize(item)
                holder.onClick(item)
            }
            else -> {
                holder as EmptyViewHolder
                holder.span()
                holder.bind(item as Bar.Empty)
            }
        }
    }

    private inner class TabBarViewHolder(
        itemView: View,
        private val clWorkspace: ConstraintLayout = (context as Workspace).clWorkspaceParent,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clTabBarItem),
        private val image: ImageView = itemView.findViewById(R.id.ivTabBarItem),
        private val divider: View = itemView.findViewById(R.id.dvTabBarItem)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = widthSpan(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun mapId() = when (adapterPosition) {
            0 -> layout.id = R.id.clTabWorkspace
            else -> layout.id = R.id.clTabCart
        }

        fun bind(item: Bar.Tab) = image.setImageResource(item.image)

        fun customize() {
            with(clWorkspace) {
                tvWorkspaceTitle.text = context.getString(R.string.text_my_compilations)
                tvCartTitle.text = context.getString(R.string.text_my_cart)
            }

            layoutParams(image, density, 32)

            when (adapterPosition) {
                0 -> attributes.setTheme(context, R.style.Vector, R.drawable.ic_workspace, image)
                1 -> {
                    attributes.setTheme(context, R.style.Vector, R.drawable.ic_cart, image)
                    layout.removeView(divider)
                    if (isCompiling)
                        with(Constraint.Workspace()) {
                            constraint(layout, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                            constraint(layout, clWorkspace, layout)
                            constraint(layout, clWorkspace, clWorkspace.clFragCart)
                            constraint(layout, clWorkspace.clFragTitle, clWorkspace.tvCartTitle)
                        }
                }
            }
        }

        fun onClick() = layout.setOnClickListener {
            beginDelayedTransition(clWorkspace)
            when (adapterPosition) {
                0 -> with(Constraint.Workspace()) {
                    constraint(layout, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                    constraint(layout, clWorkspace, layout)
                    constraint(layout, clWorkspace, clWorkspace.clFragWorkspace)
                    constraint(layout, clWorkspace.clFragTitle, clWorkspace.tvWorkspaceTitle)
                }
                1 -> with(Constraint.Workspace()) {
                    constraint(layout, clWorkspace.clFragTabBar, clWorkspace.ivTracker)
                    constraint(layout, clWorkspace, layout)
                    constraint(layout, clWorkspace, clWorkspace.clFragCart)
                    constraint(layout, clWorkspace.clFragTitle, clWorkspace.tvCartTitle)
                }
            }
        }
    }

    private inner class ActionBarViewHolder(
        itemView: View,
        val rvActionBar: RecyclerView = (context as Workspace).rvActionBar,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clActionBarItemParent),
        private val image: ImageView = itemView.findViewById(R.id.ivActionBarItem),
        private val title: TextView = itemView.findViewById(R.id.tvActionBarItem),
        private val divider: View = itemView.findViewById(R.id.dvActionBarItem)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = widthSpan(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Bar.Action) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun mapId() = when (adapterPosition) {
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

        fun customize() = when (adapterPosition) {
            0 -> title.text = User.username
            1 -> {}
            else -> layout.removeView(divider)
        }

        fun activate() = when (adapterPosition) {
            0 -> if (!layout.isSelected) attributes.setTheme(context, R.style.App, R.drawable.ic_user, image)
                 else attributes.setTheme(context, R.style.Vector, R.drawable.ic_user, image)
            1 -> attributes.setTheme(context, R.style.Vector, R.drawable.ic_compile, image)
            else -> layout.isEnabled = false
        }

        fun onClick() = layout.setOnClickListener {
            when (layout.id) {
                R.id.clActionUser -> {
                    ControlPanelViewHolder(fetchLayout(context, 3)!!).constraint(layout)
                    activate()
                }
                R.id.clActionCompile -> Dialog(context).Workspace().Prelim().build()
            }
        }
    }

    private inner class ControlBarViewHolder(
        itemView: View,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clControlBarItemParent),
        private val image: ImageView = itemView.findViewById(R.id.ivControlBarItem),
        private val title: TextView = itemView.findViewById(R.id.tvControlBarItem)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = widthSpan(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Bar.Control) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun activate() = when (adapterPosition) {
            0 -> attributes.setTheme(context, R.style.Vector, R.drawable.ic_explore, image)
            1 -> if (Bar.Cart.list.isNotEmpty()) attributes.setTheme(context, R.style.Vector, R.drawable.ic_clear, image)
                 else attributes.setTheme(context, R.style.App, R.drawable.ic_clear, image)
            else -> if (Bar.Cart.list.isNotEmpty()) attributes.setTheme(context, R.style.Vector, R.drawable.ic_checkout, image)
                    else attributes.setTheme(context, R.style.App, R.drawable.ic_checkout, image)
        }

        fun onClick() = layout.setOnClickListener {
            when (adapterPosition) {
                0 -> {
                    launch(context, activity_compile) {}
                    finish(context)
                }
                1 -> CartBarViewHolder(fetchLayout(context, 5)!!).clear()
            }
        }
    }

    private inner class ControlPanelViewHolder(
        itemView: View,
        private val clWorkspace: ConstraintLayout = (context as Workspace).clWorkspaceParent,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clControlPanelItemParent),
        private val image: ImageView = itemView.findViewById(R.id.ivControlPanelItem),
        private val title: TextView = itemView.findViewById(R.id.tvControlPanelItem),
        private val divider: View = itemView.findViewById(R.id.dvControlPanelItem)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = widthSpan(context, divider, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Panel.ControlPanel) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun mapId() = when (adapterPosition) {
            0 -> {}
            else -> layout.id = R.id.clControlPanelLogout
        }

        fun customize() = when (adapterPosition) {
            0 -> attributes.setTheme(context, R.style.Vector, R.drawable.ic_settings, image)
            else -> {
                attributes.setTheme(context, R.style.VectorHighlight, R.drawable.ic_logout, image)
                divider.visibility = View.INVISIBLE
            }
        }

        fun constraint(view: View) {
            beginDelayedTransition(clWorkspace)
            Constraint.Workspace().constraint(view, clWorkspace) {
                if (it) CompilationViewHolder(fetchLayout(context, 4)!!).activate(view, it)
                else CompilationViewHolder(fetchLayout(context, 4)!!).activate(view, it)
            }
        }

        fun onClick() = layout.setOnClickListener {
            when (adapterPosition) {
                1 -> logOut(context) { complete ->
                    if (complete) launch(context, activity_auth) {}
                    finish(context)
                }
            }
        }
    }

    private inner class CompilationViewHolder(
        itemView: View,
        private val clWorkspace: ConstraintLayout = (context as Workspace).clWorkspaceParent,
        private var compilation00: View? = null,
        private var compilation01: View? = null,
        private val clActionEdit: ConstraintLayout = ActionBarViewHolder(fetchLayout(context, 1)!!)
            .rvActionBar.layoutManager?.getChildAt(2)?.findViewById(R.id.clActionEdit)!!,
        private val ivActionEdit: ImageView = ActionBarViewHolder(fetchLayout(context, 1)!!)
            .rvActionBar.layoutManager?.getChildAt(2)?.findViewById(R.id.ivActionBarItem)!!,
        private val parent: ConstraintLayout = itemView.findViewById(R.id.clCompilationItemParent),
        private val layout: CardView = itemView.findViewById(R.id.cvCompilationItem),
        private val image: ImageView = itemView.findViewById(R.id.ivCompilationItem),
        private val title: TextView = itemView.findViewById(R.id.tvCompilationItem)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() {
            widthSpan(context, layout, (context as Workspace).windowManager, orientation, 2) {}
            heightSpan(context, layout, context.windowManager, orientation, 5) {}
        }

        fun mapId() = when (adapterPosition) {
            0 -> parent.id = R.id.cvCompilation0
            1 -> parent.id = R.id.cvCompilation1
            else -> parent.id = R.id.cvCompilation2
        }

        fun bind(item: Bar.Compilation) {
            image.setImageResource(item.image)
            title.text = item.title
        }

        fun customize() {
            layoutParams(image, density, 40)

            val views = arrayOf(parent, clWorkspace.clForward, clWorkspace.clBackward)
            views.forEach { view -> onClick(view) }
        }

        fun activate(view: View, clickable: Boolean) = if (!clickable) {
            parent.isClickable = true
            view.isSelected = !view.isSelected
        } else {
            parent.isClickable = false
            view.isSelected = !view.isSelected
        }

        private fun onClick(view: View) = when (view) {
            parent -> view.setOnClickListener {
                compilation00 = clWorkspace.rvCompilationBar.layoutManager!!.getChildAt(0)
                compilation01 = clWorkspace.rvCompilationBar.layoutManager!!.getChildAt(1)
                    when (parent) {
                    compilation00 -> if (!compilation00?.isSelected!!) {
                        compilation00?.setBackgroundColor(attributes.colorAccent)
                        compilation01?.setBackgroundColor(attributes.colorCloud)
                        compilation00?.isSelected = true
                        compilation01?.isSelected = false
                        clActionEdit.isEnabled = true
                    } else {
                        compilation00?.setBackgroundColor(attributes.colorCloud)
                        compilation00?.isSelected = false
                        compilation01?.isSelected = false
                        clActionEdit.isEnabled = false
                    }
                    compilation01 -> if (!compilation01?.isSelected!!) {
                        compilation00?.setBackgroundColor(attributes.colorCloud)
                        compilation01?.setBackgroundColor(attributes.colorAccent)
                        compilation00?.isSelected = false
                        compilation01?.isSelected = true
                        clActionEdit.isEnabled = true
                    } else {
                        compilation01?.setBackgroundColor(attributes.colorCloud)
                        compilation00?.isSelected = false
                        compilation01?.isSelected = false
                        clActionEdit.isEnabled = false
                    }
                }
                if (!parent.isSelected) attributes.setTheme(context, R.style.App, R.drawable.ic_edit, ivActionEdit)
                else attributes.setTheme(context, R.style.Vector, R.drawable.ic_edit, ivActionEdit)
            }
            clWorkspace.clBackward -> view.setOnClickListener {
                (clWorkspace.rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(true)
                clWorkspace.rvCompilationBar.smoothScrollToPosition(0)
                Handler().postDelayed({
                    (clWorkspace.rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(false)
                }, 1000)
            }
            else -> view.setOnClickListener {
                (clWorkspace.rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(true)
                clWorkspace.rvCompilationBar.smoothScrollToPosition(0 + 2)
                Handler().postDelayed({
                    (clWorkspace.rvCompilationBar.layoutManager as LayoutManager).setScrollEnabled(false)
                }, 1000)
            }
        }
    }

    private inner class CartBarViewHolder(
        itemView: View,
        private val clWorkspace: ConstraintLayout = (context as Workspace).clWorkspaceParent,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clCartBarItemParent),
        private val image: ImageView = itemView.findViewById(R.id.ivCartBarItem),
        private val title: TextView = itemView.findViewById(R.id.tvCartBarItem),
        private val more: ImageView = itemView.findViewById(R.id.ivCartBarItemMore),
        private val price: TextView = itemView.findViewById(R.id.tvCartBarItemPrice)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() {
            widthSpan(context, layout, (context as Workspace).windowManager, orientation, 1) {}
            widthSpan(context, title, context.windowManager, orientation, 2) {}
        }

        fun bind(item: Component) {
            image.setImageResource(item.image!!)
            title.text = String.format("%s %s", item.manufaturer, item.component)
            price.text = item.price
            more.setImageResource(R.drawable.ic_more)
        }

        fun customize() {
            layoutParams(image, density, 24)
            style(layout, adapterPosition)
            attributes.setTheme(context, R.style.Vector, R.drawable.ic_more, more)
        }

        fun onClick() = more.setOnClickListener {
            Dialog(context).Workspace().Option().build()
        }

        fun clear() {
            beginDelayedTransition(clWorkspace)
            clearAll()
            clWorkspace.rvCartBar.adapter?.notifyDataSetChanged()
            attributes.setTheme(context, R.style.App, R.drawable.ic_clear,
                clWorkspace.rvControlBar.getChildAt(1).findViewById(R.id.ivControlBarItem))
            attributes.setTheme(context, R.style.App, R.drawable.ic_checkout,
                clWorkspace.rvControlBar.getChildAt(2).findViewById(R.id.ivControlBarItem))
            clWorkspace.rvCartBar.adapter = Recycler(context, Bar.Cart.empty, 8, null)
        }
    }

    private inner class ComponentBarViewHolder(
        itemView: View,
        private val clCompile: ConstraintLayout = (context as Compile).clCompileParent,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clComponentBarItemParent),
        private val card: CardView = itemView.findViewById(R.id.cvComponentBarItem),
        private val image: ImageView = itemView.findViewById(R.id.ivComponentBarItem)
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Bar.Component) = image.setImageResource(item.image)

        fun customize() {
            when (adapterPosition) {
                0 -> layout.setBackgroundColor(attributes.colorAccent)
            }
            layoutParams(layout, density, 64)
            layoutParams(image, density, 40)
            mark(card, adapterPosition)
        }

        fun onClick() = layout.setOnClickListener {
            beginDelayedTransition(clCompile)
            focus(clCompile.rvComponentBar, adapterPosition)
            val componentList = componentsArray[adapterPosition]
            clCompile.rvComponent.adapter = Recycler(context, componentList, 7, adapterPosition)
        }
    }

    private inner class ComponentViewHolder(
        itemView: View,
        private val clCompile: ConstraintLayout = (context as Compile).clCompileParent,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clComponentItem),
        private val image: ImageView = itemView.findViewById(R.id.ivComponentItem),
        private val title: TextView = itemView.findViewById(R.id.tvComponentItem),
        private val more: ImageView = itemView.findViewById(R.id.ivComponentItemMore),
        private val price: TextView = itemView.findViewById(R.id.tvComponentItemPrice)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() {
            widthSpan(context, layout, (context as Compile).windowManager, orientation, 1) {}
            widthSpan(context, title, context.windowManager, orientation, 2) {}
        }

        fun bind(item: Component) {
            componentImage(image, type!!)
            title.text = String.format("%s %s", item.manufaturer, item.component)
            price.text = String.format("$%s", item.price)
            more.setImageResource(R.drawable.ic_more)
        }

        fun customize(item: Component) {
            layoutParams(image, density, 24)
            style(layout, adapterPosition)
            mark(clCompile, item, layout, type!!, adapterPosition)
            attributes.setTheme(context, R.style.Vector, R.drawable.ic_more, more)
        }

        fun onClick(item: Component) = more.setOnClickListener {
            clCompile.rvComponent.scrollToPosition(adapterPosition)
            val oldPositionList = oldPositionsArray[type!!]
            val oldPosition = Factory.position(type, oldPositionList, adapterPosition)
            Dialog(context).Compile().Overview().build(type, item, adapterPosition, oldPosition)
        }
    }

    private inner class EmptyViewHolder(
        itemView: View,
        private val layout: ConstraintLayout = itemView.findViewById(R.id.clItemEmpty),
        private val image: ImageView = itemView.findViewById(R.id.ivItemEmpty),
        private val title: TextView = itemView.findViewById(R.id.tvItemEmpty)
    ) : RecyclerView.ViewHolder(itemView) {

        fun span() = widthSpan(context, layout, (context as Workspace).windowManager, orientation, list.size) {}

        fun bind(item: Bar.Empty) {
            image.setImageResource(item.image)
            title.text = item.title
        }
    }
}