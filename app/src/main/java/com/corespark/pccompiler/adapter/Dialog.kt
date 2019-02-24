package com.corespark.pccompiler.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.transition.TransitionManager
import android.view.LayoutInflater
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
class Dialog(
    private val context: Context,
    private val background: ConstraintLayout = ConstraintLayout(context)
) {

    inner class Workspace(
        private val clWorkspace: ConstraintLayout = (context as com.corespark.pccompiler.activity.Workspace).clWorkspaceParent,
        private val clActionCompile: ConstraintLayout = clWorkspace.rvActionBar.findViewById(R.id.clActionCompile)
    ) {

        inner class Prelim(
            private val layout: View = LayoutInflater.from(context).inflate(R.layout.dialog_prelim, clWorkspace, false),
            private val clPrelim: ConstraintLayout = layout.findViewById(R.id.clPrelim),
            private val ivPrelim: ImageView = layout.findViewById(R.id.ivPrelim),
            private val tvPrelim: TextView = layout.findViewById(R.id.tvPrelim),
            private val etPrelim: EditText = layout.findViewById(R.id.etPrelim),
            private val btnPrelim: Button = layout.findViewById(R.id.btnPrelim)
        ) {

            fun build() {
                if (!clActionCompile.isSelected) {
                    clActionCompile.isSelected = true
                    instantiate()
                    customize()
                }
            }

            private fun instantiate() {
                TransitionManager.beginDelayedTransition(clWorkspace)
                with(clWorkspace) {
                    addView(this@Dialog.background)
                    addView(layout)
                }
            }

            private fun customize() {
                with(background) {
                    id = R.id.bgTransparent
                    layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    layoutParams.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    setBackgroundColor(Application.attributes.colorTransparentBlack)
                }

                ivPrelim.setImageResource(R.drawable.ic_close_transparent_gray_24dp)
                tvPrelim.text = context.getString(R.string.text_compilation)
                etPrelim.hint = context.getString(R.string.text_title)
                btnPrelim.text = context.getString(R.string.text_create)
                btnPrelim.visibility = View.GONE

                with(Constraint) {
                    set(background, clWorkspace, background)
                    set(layout, clWorkspace, layout)
                    set(etPrelim, clPrelim, etPrelim)
                }

                val views = arrayOf(clWorkspace, ivPrelim, etPrelim, btnPrelim)
                views.forEach { view -> onClick(view) }
            }

            private fun onClick(view: View) = when (view) {
                clWorkspace, ivPrelim -> view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clWorkspace)
                    clWorkspace.removeView(layout)
                    clWorkspace.removeView(background)
                    clActionCompile.isSelected = false
                    Input.hideKeyboard(context)
                }
                etPrelim -> (view as EditText).addTextChangedListener(object : TextWatcher {
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
                    Compilation.title = etPrelim.text.toString()
                    Intent.launch(context, R.layout.activity_compile) { complete -> if (complete) createCompilationObject() }
                    Intent.finish(context)
                }
            }

            private fun createCompilationObject() {
                //val compilation = ParseObject.create("Compilation")
                //compilation.put("user", Auth.parseUser)
                //compilation.put("title", Compilation.title)

//                if (Compilation.cpu != null) {
//                    val query = ParseQuery.getQuery<ParseObject>("CPU")
//                    query.whereEqualTo("objectId", Compilation.cpu!!.id)
//                    query.findInBackground { objects, e ->
//                        if (e == null) {
//                            for (obj in objects) {
//                                compilation.put("cpu", obj)
//                                compilation.saveInBackground()
//                            }
//                        }
//                    }
//                }
            }
        }
    }

    inner class Compile(
        private val clCompile: ConstraintLayout = (context as com.corespark.pccompiler.activity.Compile).clCompileParent,
        private val ivMore: ImageView = clCompile.rvComponent.findViewById(R.id.ivComponentItemMore)
    ) {

        inner class Overview(
            private val layout: View = LayoutInflater.from(context).inflate(R.layout.dialog_overview, clCompile, false),
            private val tvOverview: TextView = layout.tvOverview,
            private val ivClose: ImageView = layout.ivOverviewClose,
            private val tvComponentType: TextView = layout.tvOverviewComponentType,
            private val tvComponent: TextView = layout.tvOverviewComponent,
            private val ivComponent: ImageView = layout.ivOverview,
            private val llParameter: LinearLayout = layout.llOverviewParameter,
            private val llDescription: LinearLayout = layout.llOverviewDescription,
            private val tvParameter: TextView = layout.tvOverviewParameter,
            private val tvDescription: TextView = layout.tvOverviewDescription,
            private val tvParamA: TextView = layout.tvOverviewParamA,
            private val tvParamB: TextView = layout.tvOverviewParamB,
            private val tvParamC: TextView = layout.tvOverviewParamC,
            private val tvParamD: TextView = layout.tvOverviewParamD,
            private val tvParamE: TextView = layout.tvOverviewParamE,
            private val tvParamF: TextView = layout.tvOverviewParamF,
            private val tvDescA: TextView = layout.tvOverviewDescA,
            private val tvDescB: TextView = layout.tvOverviewDescB,
            private val tvDescC: TextView = layout.tvOverviewDescC,
            private val tvDescD: TextView = layout.tvOverviewDescD,
            private val tvDescE: TextView = layout.tvOverviewDescE,
            private val tvDescF: TextView = layout.tvOverviewDescF,
            private val tvPrice: TextView = layout.tvOverviewPrice,
            private val btnAdd: Button = layout.btnAdd,
            private val btnRemove: Button = layout.btnRemove,
            private val btnReplace: Button = layout.btnReplace,
            private val params: Array<View> = arrayOf(
                ivComponent, tvComponentType, tvComponent,
                tvParamA, tvParamB, tvParamC, tvParamD, tvParamE, tvParamF,
                tvDescA, tvDescB, tvDescC, tvDescD, tvDescE, tvDescF,
                tvPrice, llParameter, llDescription, tvParameter, tvDescription),
            private val views: Array<Button> = arrayOf(btnAdd, btnRemove, btnReplace)
        ) {

            fun build(componentType: Int, item: Component, rowPosition: Int, oldPosition: Int) {
                if (!ivMore.isSelected) {
                    ivMore.isSelected = true
                    instantiate()
                    customize(componentType, item, rowPosition, oldPosition)
                }
            }

            private fun instantiate() {
                TransitionManager.beginDelayedTransition(clCompile)
                with(clCompile) {
                    addView(this@Dialog.background)
                    addView(layout)
                }
            }

            private fun customize(componentType: Int, item: Component, rowPosition: Int, oldPosition: Int) {
                with(background) {
                    id = R.id.bgTransparent
                    layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    layoutParams.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                    setBackgroundColor(Application.attributes.colorTransparentBlack)
                }

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
                Bind.componentInfo(context, item, componentType, params)
                Factory.utilize(item, componentType, views)

                val listeners = arrayOf(background, ivClose, layout, btnAdd, btnRemove, btnReplace)
                listeners.forEach { listener -> onClick(listener, componentType, item, rowPosition, oldPosition) }
            }

            private fun onClick(
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
                    Factory.mark(clCompile, btnAdd, componentType, rowPosition)
                    Compilation.assignComponent(componentType, item)
                    btnAdd.isEnabled = false
                    btnRemove.isEnabled = true
                }
                btnRemove -> view.setOnClickListener {
                    btnAdd.isEnabled = true
                    btnRemove.isEnabled = false
                    Compilation.deassignComponent(componentType, item)
                    Factory.mark(clCompile, btnRemove, componentType, rowPosition)
                }
                else -> view.setOnClickListener {
                    Compilation.replaceComponent(componentType, item)
                    Factory.replace(clCompile, layout, componentType, rowPosition, oldPosition, views)
                }
            }
        }
    }
}