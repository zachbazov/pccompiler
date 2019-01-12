package com.corespark.pccompiler.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
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
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_workspace.*


/**
 * @author Zachy Bazov.
 * @since 29/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Recycler(val context: Context, private val list: MutableList<Any>, val type: Int, val subType: Int)
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
            7 -> {
                holder = Component().ComponentViewHolder(
                    infalter.inflate(R.layout.item_component, container, false))
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
                holder.onClick(holder.layout)
            }
            7 -> {
                val item = list[position]
                holder as Component.ComponentViewHolder
                holder.span()
                holder.bind(item)
                holder.style(position)
                holder.customize()
                holder.expand()
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
                        dialog.Workspace().Compilation().create()
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

        val clCompile = (context as Compile).clCompile!!
        val rvComponent = (context as Compile).rvComponent!!

        inner class ComponentBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val layout = itemView.findViewById<ConstraintLayout>(R.id.clComponent)!!
            val image = itemView.findViewById<ImageView>(R.id.ivComponent)!!

            var clCpu: ConstraintLayout? = null
            var clCooler: ConstraintLayout? = null
            var clMotherboard: ConstraintLayout? = null
            var clMemory: ConstraintLayout? = null
            var clStorage: ConstraintLayout? = null
            var clExtStorage: ConstraintLayout? = null
            var clOptDrive: ConstraintLayout? = null
            var clGraphicCard: ConstraintLayout? = null
            var clSoundCard: ConstraintLayout? = null
            var clPowerSupply: ConstraintLayout? = null
            var clCase: ConstraintLayout? = null
            var clOpSystem: ConstraintLayout? = null

            fun span() {
                Window.determineSpan(
                    context, layout, (context as Compile).windowManager, Window.orientation, list.size/2) {}
            }

            fun mapId(position: Int) {
                when (position) {
                    0 -> {
                        clCpu = layout
                        clCpu?.id = R.id.clComponentBarCpu
                    }
                    2 -> {
                        clCooler = layout
                        clCooler?.id = R.id.clComponentBarCooler
                    }
                    4 -> {
                        clMotherboard = layout
                        clMotherboard?.id = R.id.clComponentBarMotherboard
                    }
                    6 -> {
                        clMemory = layout
                        clMemory?.id = R.id.clComponentBarMemory
                    }
                    8 -> {
                        clStorage = layout
                        clStorage?.id = R.id.clComponentBarStorage
                    }
                    10 -> {
                        clExtStorage = layout
                        clExtStorage?.id = R.id.clComponentBarExtStorage
                    }
                    1 -> {
                        clOptDrive = layout
                        clOptDrive?.id = R.id.clComponentBarOptDrive
                    }
                    3 -> {
                        clGraphicCard = layout
                        clGraphicCard?.id = R.id.clComponentBarGraphicCard
                    }
                    5 -> {
                        clSoundCard = layout
                        clSoundCard?.id = R.id.clComponentBarSoundCard
                    }
                    7 -> {
                        clPowerSupply = layout
                        clPowerSupply?.id = R.id.clComponentBarPowerSupply
                    }
                    9 -> {
                        clCase = layout
                        clCase?.id = R.id.clComponentBarCase
                    }
                    11 -> {
                        clOpSystem = layout
                        clOpSystem?.id = R.id.clComponentBarOpSystem
                    }
                }
            }

            fun bind(item: Bar.Component) {
                image.setImageResource(item.image)
            }

            fun onClick(view: View) {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clCompile)
                    when (view.id) {
                        clCpu?.id -> { rvComponent.adapter = Recycler(context, Compiler.cpuList, 7, 0) }
                        clCooler?.id -> { rvComponent.adapter = Recycler(context, Compiler.coolerList, 7, 1) }
                        clMotherboard?.id -> { rvComponent.adapter = Recycler(context, Compiler.motherboardList, 7, 2) }
                        clMemory?.id -> { rvComponent.adapter = Recycler(context, Compiler.memoryList, 7, 3) }
                        clStorage?.id -> { rvComponent.adapter = Recycler(context, Compiler.storageList, 7, 4) }
                        clExtStorage?.id -> { rvComponent.adapter = Recycler(context, Compiler.extStorageList, 7, 5) }
                        clOptDrive?.id -> { rvComponent.adapter = Recycler(context, Compiler.optDriveList, 7, 6) }
                        clGraphicCard?.id -> { rvComponent.adapter = Recycler(context, Compiler.graphicCardList, 7, 7) }
                        clSoundCard?.id -> { rvComponent.adapter = Recycler(context, Compiler.soundCardList, 7, 8) }
                        clPowerSupply?.id -> { rvComponent.adapter = Recycler(context, Compiler.powerSupplyList, 7, 9) }
                        clCase?.id -> { rvComponent.adapter = Recycler(context, Compiler.caseList, 7, 10) }
                        clOpSystem?.id -> { rvComponent.adapter = Recycler(context, Compiler.opSystemList, 7, 11) }
                    }
                }
            }
        }

        inner class ComponentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val parent = itemView.findViewById<ConstraintLayout>(R.id.clComponentParent)!!
            val layout = itemView.findViewById<ConstraintLayout>(R.id.clComponent)!!
            val image = itemView.findViewById<ImageView>(R.id.ivComponent)!!
            val title = itemView.findViewById<TextView>(R.id.tvComponent)!!
            val paramA = itemView.findViewById<TextView>(R.id.tvComponentParamA)!!
            val paramB = itemView.findViewById<TextView>(R.id.tvComponentParamB)!!
            val paramC = itemView.findViewById<TextView>(R.id.tvComponentParamC)!!
            val paramD = itemView.findViewById<TextView>(R.id.tvComponentParamD)!!
            val paramE = itemView.findViewById<TextView>(R.id.tvComponentParamE)!!
            val paramF = itemView.findViewById<TextView>(R.id.tvComponentParamF)!!
            val price = itemView.findViewById<TextView>(R.id.tvComponentPrice)!!

            fun span() {
                Window.determineSpan(context, layout, (context as Compile).windowManager, Window.orientation, 1) {}
            }

            fun bind(item: Any) {
                when (subType) {
                    0 -> {
                        item as com.corespark.pccompiler.model.Component.CPU
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = String.format("%s Core", item.paramA)
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        price.text = String.format("$%s", item.price)
                    }
                    1 -> {
                        item as com.corespark.pccompiler.model.Component.Cooler
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        price.text = String.format("$%s", item.price)
                    }
                    2 -> {
                        item as com.corespark.pccompiler.model.Component.Motherboard
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = String.format("%s Slot", item.paramC)
                        paramD.text = item.paramD
                        price.text = String.format("$%s", item.price)
                    }
                    3 -> {
                        item as com.corespark.pccompiler.model.Component.Memory
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        paramD.text = item.paramD
                        paramE.text = item.paramE
                        paramF.text = item.paramF
                        price.text = String.format("$%s", item.price)
                    }
                    4 -> {
                        item as com.corespark.pccompiler.model.Component.Storage
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        paramD.text = item.paramD
                        paramE.text = item.paramE
                        paramF.text = item.paramF
                        price.text = String.format("$%s", item.price)
                    }
                    5 -> {
                        item as com.corespark.pccompiler.model.Component.ExternalStorage
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        price.text = String.format("$%s", item.price)
                    }
                    6 -> {
                        item as com.corespark.pccompiler.model.Component.OpticalDrive
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        paramD.text = item.paramD
                        paramE.text = item.paramE
                        paramF.text = item.paramF
                        price.text = String.format("$%s", item.price)
                    }
                    7 -> {
                        item as com.corespark.pccompiler.model.Component.GraphicCard
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        paramD.text = item.paramD
                        price.text = String.format("$%s", item.price)
                    }
                    8 -> {
                        item as com.corespark.pccompiler.model.Component.SoundCard
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        paramD.text = item.paramD
                        paramE.text = item.paramE
                        price.text = String.format("$%s", item.price)
                    }
                    9 -> {
                        item as com.corespark.pccompiler.model.Component.PowerSupply
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        paramD.text = item.paramD
                        paramE.text = item.paramE
                        price.text = String.format("$%s", item.price)
                    }
                    10 -> {
                        item as com.corespark.pccompiler.model.Component.Case
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        paramA.text = item.paramA
                        paramB.text = item.paramB
                        paramC.text = item.paramC
                        paramD.text = item.paramD
                        price.text = String.format("$%s", item.price)
                    }
                    11 -> {
                        item as com.corespark.pccompiler.model.Component.OperatingSystem
                        image.setImageResource(item.image)
                        Parameter.set(image, 48)
                        title.text = String.format("%s %s", item.manufaturer, item.component)
                        price.text = String.format("$%s", item.price)
                    }
                }
            }

            fun style(position: Int) {
                if (position % 2 == 0) layout.setBackgroundColor(Compiler.colors.colorGray)
                else layout.setBackgroundColor(Compiler.colors.colorWhite)
            }

            fun customize() {
                val params = arrayOf(paramA, paramB, paramC, paramD, paramE, paramF)
                for (param in params) param.visibility = View.GONE
            }

            fun expand() {
                when (subType) {
                    0, 1, 5 -> {
                        layout.setOnClickListener {
                            if (!it.isSelected) {
                                TransitionManager.beginDelayedTransition(parent)
                                paramA.visibility = View.VISIBLE
                                paramB.visibility = View.VISIBLE
                                paramC.visibility = View.VISIBLE
                                Constraint.set.clone(layout)
                                Constraint.set.clear(image.id, ConstraintSet.BOTTOM)
                                Constraint.set.connect(paramA.id, ConstraintSet.BOTTOM, layout.id, ConstraintSet.BOTTOM, 16)
                                Constraint.set.applyTo(layout)
                                rvComponent.scrollToPosition(adapterPosition)
                                title.setSingleLine(false)
                                it.isSelected = !it.isSelected
                            } else {
                                TransitionManager.beginDelayedTransition(parent)
                                paramA.visibility = View.GONE
                                paramB.visibility = View.GONE
                                paramC.visibility = View.GONE
                                Constraint.set.clone(layout)
                                Constraint.set.clear(paramA.id, ConstraintSet.BOTTOM)
                                Constraint.set.connect(image.id, ConstraintSet.BOTTOM, layout.id, ConstraintSet.BOTTOM, 16)
                                Constraint.set.applyTo(layout)
                                rvComponent.scrollToPosition(adapterPosition)
                                title.setSingleLine(true)
                                it.isSelected = !it.isSelected
                            }
                        }
                    }
                    2, 3, 4, 6, 7, 8, 9, 10 -> {
                        layout.setOnClickListener {
                            if (!it.isSelected) {
                                TransitionManager.beginDelayedTransition(parent)
                                paramA.visibility = View.VISIBLE
                                paramB.visibility = View.VISIBLE
                                paramC.visibility = View.VISIBLE
                                paramD.visibility = View.VISIBLE
                                Constraint.set.clone(layout)
                                Constraint.set.clear(image.id, ConstraintSet.BOTTOM)
                                Constraint.set.connect(paramD.id, ConstraintSet.BOTTOM, layout.id, ConstraintSet.BOTTOM, 16)
                                Constraint.set.applyTo(layout)
                                rvComponent.scrollToPosition(adapterPosition)
                                title.setSingleLine(false)
                                it.isSelected = !it.isSelected
                            } else {
                                TransitionManager.beginDelayedTransition(parent)
                                paramA.visibility = View.GONE
                                paramB.visibility = View.GONE
                                paramC.visibility = View.GONE
                                paramD.visibility = View.GONE
                                Constraint.set.clone(layout)
                                Constraint.set.clear(paramD.id, ConstraintSet.BOTTOM)
                                Constraint.set.connect(image.id, ConstraintSet.BOTTOM, layout.id, ConstraintSet.BOTTOM, 16)
                                Constraint.set.applyTo(layout)
                                rvComponent.scrollToPosition(adapterPosition)
                                title.setSingleLine(true)
                                it.isSelected = !it.isSelected
                            }
                        }
                    }
                }
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