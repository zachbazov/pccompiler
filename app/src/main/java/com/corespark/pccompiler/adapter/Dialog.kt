package com.corespark.pccompiler.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import com.corespark.pccompiler.model.Compilation
import com.corespark.pccompiler.model.Component
import com.corespark.pccompiler.service.*
import kotlinx.android.synthetic.main.activity_workspace.*
import kotlinx.android.synthetic.main.activity_workspace.view.*
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_compile.view.*
import kotlinx.android.synthetic.main.dialog_overview.view.*


/**
 * @author Zachy Bazov.
 * @since 08/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Dialog(val context: Context) {

    val background = ConstraintLayout(context)

    inner class Workspace {

        val clWorkspace = (context as com.corespark.pccompiler.activity.Workspace).clWorkspaceParent!!
        val clActionCompile = clWorkspace.rvActionBar.findViewById<ConstraintLayout>(R.id.clActionCompile)!!

        inner class Prelim {

            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_prelim, clWorkspace, false)!!
            val clPrelim = layout.findViewById<ConstraintLayout>(R.id.clPrelim)!!
            private val ivPrelim = layout.findViewById<ImageView>(R.id.ivPrelim)!!
            private val tvPrelim = layout.findViewById<TextView>(R.id.tvPrelim)!!
            val etPrelim = layout.findViewById<EditText>(R.id.etPrelim)!!
            val btnPrelim = layout.findViewById<Button>(R.id.btnPrelim)!!

            fun build() {
                if (!clActionCompile.isSelected) {
                    clActionCompile.isSelected = true
                    instantiate()
                    customize()
                }
            }

            private fun instantiate() {
                TransitionManager.beginDelayedTransition(clWorkspace)
                clWorkspace.addView(background)
                clWorkspace.addView(layout)
            }

            private fun customize() {
                background.id = R.id.bgTransparent
                background.layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                background.layoutParams.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                background.setBackgroundColor(Compiler.colors.colorTransparentBlack)

                ivPrelim.setImageResource(R.drawable.ic_close_transparent_gray_24dp)
                tvPrelim.text = context.getString(R.string.text_compilation)
                etPrelim.hint = context.getString(R.string.text_title)
                btnPrelim.text = context.getString(R.string.text_create)
                btnPrelim.visibility = View.GONE

                Constraint.set(background, clWorkspace, background)
                Constraint.set(layout, clWorkspace, layout)
                Constraint.set(etPrelim, clPrelim, etPrelim)

                val views = arrayOf(clWorkspace, ivPrelim, etPrelim, btnPrelim)
                for (view in views) listener(view)
            }

            private fun listener(view: View) = when (view.id) {
                clWorkspace.id, ivPrelim.id -> view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clWorkspace)
                    clWorkspace.removeView(layout)
                    clWorkspace.removeView(background)
                    clActionCompile.isSelected = false
                    Input.hideKeyboard(context)
                }
                etPrelim.id -> (view as EditText).addTextChangedListener(object : TextWatcher {
                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                        if (!etPrelim.isSelected) {
                            etPrelim.isSelected = true
                            TransitionManager.beginDelayedTransition(layout as ViewGroup?)
                            btnPrelim.visibility = View.VISIBLE
                            Constraint.set(etPrelim, clPrelim, etPrelim)
                        }
                        if (count == 0) {
                            etPrelim.isSelected = false
                            TransitionManager.beginDelayedTransition(layout as ViewGroup?)
                            btnPrelim.visibility = View.GONE
                            Constraint.set(etPrelim, clPrelim, etPrelim)
                        }
                    }
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                    override fun afterTextChanged(s: Editable) {}
                })
                else -> view.setOnClickListener {
                    it.isEnabled = false
                    val compilationTitle = etPrelim.text.toString()
                    Intent.launch(context, R.layout.activity_compile) { Compilation.title = compilationTitle }
                    Intent.finish(context)
                }
            }
        }
    }

    inner class Compile {

        val clCompile = (context as com.corespark.pccompiler.activity.Compile).clCompileParent!!
        val ivMore = clCompile.rvComponent.findViewById<ImageView>(R.id.ivComponentItemMore)!!

        inner class Overview {

            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_overview, clCompile, false)!!

            private val tvOverview = layout.tvOverview!!
            private val ivClose = layout.ivOverviewClose!!
            private val tvComponentType = layout.tvOverviewComponentType!!
            private val tvComponent = layout.tvOverviewComponent!!
            private val ivComponent = layout.ivOverview!!
            private val llParameter = layout.llOverviewParameter!!
            private val llDescription = layout.llOverviewDescription!!
            private val tvParameter = layout.tvOverviewParameter!!
            private val tvDescription = layout.tvOverviewDescription!!
            private val tvParamA = layout.tvOverviewParamA!!
            private val tvParamB = layout.tvOverviewParamB!!
            private val tvParamC = layout.tvOverviewParamC!!
            private val tvParamD = layout.tvOverviewParamD!!
            private val tvParamE = layout.tvOverviewParamE!!
            private val tvParamF = layout.tvOverviewParamF!!
            private val tvDescA = layout.tvOverviewDescA!!
            private val tvDescB = layout.tvOverviewDescB!!
            private val tvDescC = layout.tvOverviewDescC!!
            private val tvDescD = layout.tvOverviewDescD!!
            private val tvDescE = layout.tvOverviewDescE!!
            private val tvDescF = layout.tvOverviewDescF!!
            private val tvPrice = layout.tvOverviewPrice!!
            private val btnAdd = layout.btnAdd!!
            private val btnRemove = layout.btnRemove!!
            private val btnReplace = layout.btnReplace!!

            private val params = arrayOf(
                ivComponent, tvComponentType, tvComponent,
                tvParamA, tvParamB, tvParamC, tvParamD, tvParamE, tvParamF,
                tvDescA, tvDescB, tvDescC, tvDescD, tvDescE, tvDescF,
                tvPrice, llParameter, llDescription, tvParameter, tvDescription)

            private val views = arrayOf(btnAdd, btnRemove, btnReplace)

            fun build(componentType: Int, item: Component, rowPosition: Int, oldPosition: Int) {
                if (!ivMore.isSelected) {
                    ivMore.isSelected = true
                    instantiate()
                    customize(componentType, item, rowPosition, oldPosition)
                }
            }

            private fun instantiate() {
                TransitionManager.beginDelayedTransition(clCompile)
                clCompile.addView(background)
                clCompile.addView(layout)
            }

            private fun customize(componentType: Int, item: Component, rowPosition: Int, oldPosition: Int) {
                background.id = R.id.bgTransparent
                background.layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                background.layoutParams.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                background.setBackgroundColor(Compiler.colors.colorTransparentBlack)

                tvOverview.text = context.getString(R.string.dialog_overview)
                ivClose.setImageResource(R.drawable.ic_close_transparent_gray_24dp)
                tvParameter.text = context.getString(R.string.dialog_parameter)
                tvDescription.text = context.getString(R.string.dialog_description)
                btnAdd.text = context.getString(R.string.dialog_add)
                btnRemove.text = context.getString(R.string.dialog_remove)
                btnReplace.text = context.getString(R.string.dialog_replace)
                btnRemove.isEnabled = false
                btnReplace.visibility = View.GONE

                Constraint.set(layout, clCompile) {}
                DataBinding.componentInfo(context, item, componentType, params)
                Algorithm.enableSelection(item, componentType, views)

                val listeners = arrayOf(background, ivClose, layout, btnAdd, btnRemove, btnReplace)
                for (listener in listeners) listener(listener, componentType, item, rowPosition, oldPosition)
            }

            private fun listener(
                view: View, componentType: Int, item: Component?, rowPosition: Int, oldPosition: Int
            ) = when (view) {
                background -> view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clCompile)
                    clCompile.removeView(layout)
                    clCompile.removeView(background)
                    ivMore.isSelected = false
                }
                ivClose -> view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clCompile)
                    clCompile.removeView(layout)
                    clCompile.removeView(background)
                    ivMore.isSelected = false
                }
                layout -> layout.setOnClickListener {}
                btnAdd -> view.setOnClickListener {
                    Algorithm.mark(clCompile, btnAdd, componentType, rowPosition)
                    Compilation.assignComponent(componentType, item)
                    btnAdd.isEnabled = false
                    btnRemove.isEnabled = true
                }
                btnRemove -> view.setOnClickListener {
                    btnAdd.isEnabled = true
                    btnRemove.isEnabled = false
                    Compilation.deassignComponent(componentType)
                    Algorithm.mark(clCompile, btnRemove, componentType, rowPosition)
                }
                else -> view.setOnClickListener {
                    Compilation.assignComponent(componentType, item)
                    Algorithm.replacePosition(clCompile, layout, componentType, rowPosition, oldPosition, views)
                }
            }
        }
    }
}