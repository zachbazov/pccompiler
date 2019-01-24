package com.corespark.pccompiler.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.Constraint
import com.corespark.pccompiler.service.Window
import kotlinx.android.synthetic.main.activity_workspace.*
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import com.corespark.pccompiler.model.Component
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.service.Intent.intent
import com.corespark.pccompiler.utility.KEY_COMPILATION_TITLE
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_compile.view.*
import kotlinx.android.synthetic.main.activity_workspace.view.*
import kotlinx.android.synthetic.main.dialog_compile.view.*


/**
 * @author Zachy Bazov.
 * @since 08/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Dialog(val context: Context, val item: Component?) {

    val bgTransparent = ConstraintLayout(context)

    inner class Workspace {

        val clWorkspace = (context as com.corespark.pccompiler.activity.Workspace).clWorkspaceParent!!
        val clActionCompile = clWorkspace.rvActionBar.findViewById<ConstraintLayout>(R.id.clActionCompile)!!

        inner class Compilation {

            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_workspace, clWorkspace, false)!!
            val clDialog = layout.findViewById<ConstraintLayout>(R.id.clDialogWorkspace)!!
            private val ivDialog = layout.findViewById<ImageView>(R.id.ivDialogWorkspace)!!
            private val tvDialog = layout.findViewById<TextView>(R.id.tvDialogWorkspace)!!
            val etDialog = layout.findViewById<EditText>(R.id.etDialogWorkspace)!!
            val btnDialog = layout.findViewById<Button>(R.id.btnDialogWorkspace)!!

            fun build() {
                if (!clActionCompile.isSelected) {
                    clActionCompile.isSelected = true
                    instantiate()
                    customize()
                    constraint()
                }
            }

            fun instantiate() {
                TransitionManager.beginDelayedTransition(clWorkspace)
                clWorkspace.addView(bgTransparent)
                clWorkspace.addView(layout)
            }

            private fun customize() {
                bgTransparent.id = R.id.bgTransparent
                bgTransparent.layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                bgTransparent.layoutParams.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                bgTransparent.setBackgroundColor(Compiler.colors.colorTransparentBlack)

                ivDialog.setImageResource(R.drawable.ic_close_transparent_gray_24dp)
                tvDialog.text = context.getString(R.string.text_compilation)
                etDialog.hint = context.getString(R.string.text_title)
                btnDialog.text = context.getString(R.string.text_create)
                btnDialog.visibility = View.GONE

                val views = arrayOf(ivDialog, etDialog, btnDialog)
                for (listener in views) listener(listener)
            }

            fun constraint() {
                Constraint.set(bgTransparent, clWorkspace, bgTransparent)
                Constraint.set(layout, clWorkspace, layout)
                Constraint.set(etDialog, clDialog, etDialog)
            }

            private fun listener(view: View) {
                when (view.id) {
                    ivDialog.id -> {
                        ivDialog.setOnClickListener {
                            TransitionManager.beginDelayedTransition(clWorkspace)
                            clWorkspace.removeView(layout)
                            clWorkspace.removeView(bgTransparent)
                            clActionCompile.isSelected = false
                            Window.hideKeyboard(context)
                        }
                    }
                    etDialog.id -> {
                        etDialog.addTextChangedListener(object : TextWatcher {
                            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                if (!etDialog.isSelected) {
                                    etDialog.isSelected = true
                                    TransitionManager.beginDelayedTransition(layout as ViewGroup?)
                                    btnDialog.visibility = View.VISIBLE
                                    Constraint.set(etDialog, clDialog, etDialog)
                                }
                                if (count == 0) {
                                    etDialog.isSelected = false
                                    TransitionManager.beginDelayedTransition(layout as ViewGroup?)
                                    btnDialog.visibility = View.GONE
                                    Constraint.set(etDialog, clDialog, etDialog)
                                }
                            }
                            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                            override fun afterTextChanged(s: Editable) {}
                        })
                    }
                    btnDialog.id -> {
                        btnDialog.setOnClickListener {
                            it.isEnabled = false
                            val compilationTitle = etDialog.text.toString()
                            Intent.launch(context, R.layout.activity_compile) {
                                intent.putExtra(KEY_COMPILATION_TITLE, compilationTitle)
                            }
                            Intent.finish(context)
                        }
                    }
                }
            }
        }
    }

    inner class Compile {

        val clCompile = (context as com.corespark.pccompiler.activity.Compile).clCompileParent!!
        val ivMore = clCompile.rvComponent.findViewById<ImageView>(R.id.ivComponentItemMore)!!

        inner class Overview {

            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_compile, clCompile, false)!!

            val ivClose = layout.ivDialogCompileClose!!
            val tvComponent = layout.tvDialogCompile!!
            val ivComponent = layout.ivDialogCompile!!
            val llParameter = layout.llDialogCompileParameter!!
            val llDescription = layout.llDialogCompileDescription!!
            val tvParamA = layout.tvDialogCompileParamA!!
            val tvParamB = layout.tvDialogCompileParamB!!
            val tvParamC = layout.tvDialogCompileParamC!!
            val tvParamD = layout.tvDialogCompileParamD!!
            val tvParamE = layout.tvDialogCompileParamE!!
            val tvParamF = layout.tvDialogCompileParamF!!
            val tvDescA = layout.tvDialogCompileDescA!!
            val tvDescB = layout.tvDialogCompileDescB!!
            val tvDescC = layout.tvDialogCompileDescC!!
            val tvDescD = layout.tvDialogCompileDescD!!
            val tvDescE = layout.tvDialogCompileDescE!!
            val tvDescF = layout.tvDialogCompileDescF!!
            val tvPrice = layout.tvDialogCompilePrice!!
            val tbAction = layout.tbDialogCompile!!

            fun build(component: Int) {
                if (!ivMore.isSelected) {
                    ivMore.isSelected = true
                    TransitionManager.beginDelayedTransition(clCompile)
                    clCompile.addView(bgTransparent)
                    clCompile.addView(layout)
                    customize(component)
                    constraint()
                    listener()
                }
            }

            private fun customize(component: Int) {
                bgTransparent.id = R.id.bgTransparent
                bgTransparent.layoutParams.width = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                bgTransparent.layoutParams.height = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
                bgTransparent.setBackgroundColor(Compiler.colors.colorTransparentBlack)

                when (component) {
                    0 -> {
                        ivComponent.setImageResource(R.mipmap.ic_cpu)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_core)
                        tvParamB.text = context.getString(R.string.dialog_speed)
                        tvParamC.text = context.getString(R.string.dialog_tdp)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamD)
                        llParameter.removeView(tvParamE)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescD)
                        llDescription.removeView(tvDescE)
                        llDescription.removeView(tvDescF)
                    }
                    1 -> {
                        ivComponent.setImageResource(R.mipmap.ic_optdrive)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_bd)
                        tvParamB.text = context.getString(R.string.dialog_cd)
                        tvParamC.text = context.getString(R.string.dialog_dvd)
                        tvParamD.text = context.getString(R.string.dialog_bd_write)
                        tvParamE.text = context.getString(R.string.dialog_cd_write)
                        tvParamF.text = context.getString(R.string.dialog_dvd_write)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvDescE.text = item.paramE
                        tvDescF.text = item.paramF
                        tvPrice.text = String.format("$%s", item.price)
                    }
                    2 -> {
                        ivComponent.setImageResource(R.mipmap.ic_cooler)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_rpm)
                        tvParamB.text = context.getString(R.string.dialog_noise)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamC)
                        llParameter.removeView(tvParamD)
                        llParameter.removeView(tvParamE)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescC)
                        llDescription.removeView(tvDescD)
                        llDescription.removeView(tvDescE)
                        llDescription.removeView(tvDescF)
                    }
                    3 -> {
                        ivComponent.setImageResource(R.mipmap.ic_graphiccard)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_series)
                        tvParamB.text = context.getString(R.string.dialog_chipset)
                        tvParamC.text = context.getString(R.string.dialog_memory)
                        tvParamD.text = context.getString(R.string.dialog_core_clock)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamE)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescE)
                        llDescription.removeView(tvDescF)
                    }
                    4 -> {
                        ivComponent.setImageResource(R.mipmap.ic_motherboard)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_socket_cpu)
                        tvParamB.text = context.getString(R.string.dialog_form_factor)
                        tvParamC.text = context.getString(R.string.dialog_ram_slots)
                        tvParamD.text = context.getString(R.string.dialog_max_ram)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamE)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescE)
                        llDescription.removeView(tvDescF)
                    }
                    5 -> {
                        ivComponent.setImageResource(R.mipmap.ic_soundcard)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_chipset)
                        tvParamB.text = context.getString(R.string.dialog_bits)
                        tvParamC.text = context.getString(R.string.dialog_snr)
                        tvParamD.text = context.getString(R.string.dialog_channels)
                        tvParamE.text = context.getString(R.string.dialog_rate)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvDescE.text = item.paramE
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescF)
                    }
                    6 -> {
                        ivComponent.setImageResource(R.mipmap.ic_memory)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_type)
                        tvParamB.text = context.getString(R.string.dialog_speed)
                        tvParamC.text = context.getString(R.string.dialog_modules)
                        tvParamD.text = context.getString(R.string.dialog_cas)
                        tvParamE.text = context.getString(R.string.dialog_size)
                        tvParamF.text = context.getString(R.string.dialog_gb_price)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvDescE.text = item.paramE
                        tvDescF.text = String.format("$%s", item.paramF)
                        tvPrice.text = String.format("$%s", item.price)
                    }
                    7 -> {
                        ivComponent.setImageResource(R.mipmap.ic_soundcard)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_series)
                        tvParamB.text = context.getString(R.string.dialog_form)
                        tvParamC.text = context.getString(R.string.dialog_efficiency)
                        tvParamD.text = context.getString(R.string.dialog_modular)
                        tvParamE.text = context.getString(R.string.dialog_watts)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvDescE.text = item.paramE
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescF)
                    }
                    8 -> {
                        ivComponent.setImageResource(R.mipmap.ic_storage)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_series)
                        tvParamB.text = context.getString(R.string.dialog_form)
                        tvParamC.text = context.getString(R.string.dialog_type)
                        tvParamD.text = context.getString(R.string.dialog_cache)
                        tvParamE.text = context.getString(R.string.dialog_capacity)
                        tvParamF.text = context.getString(R.string.dialog_gb_price)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvDescE.text = item.paramE
                        tvDescF.text = String.format("$%s", item.paramF)
                        tvPrice.text = String.format("$%s", item.price)
                    }
                    9 -> {
                        ivComponent.setImageResource(R.mipmap.ic_case)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_type)
                        tvParamB.text = context.getString(R.string.dialog_external)
                        tvParamC.text = context.getString(R.string.dialog_internal)
                        tvParamD.text = context.getString(R.string.dialog_power_supply)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = item.paramC
                        tvDescD.text = item.paramD
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamE)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescE)
                        llDescription.removeView(tvDescF)
                    }
                    10 -> {
                        ivComponent.setImageResource(R.mipmap.ic_extstorage)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvParamA.text = context.getString(R.string.dialog_type)
                        tvParamB.text = context.getString(R.string.dialog_capacity)
                        tvParamC.text = context.getString(R.string.dialog_gb_price)
                        tvDescA.text = item.paramA
                        tvDescB.text = item.paramB
                        tvDescC.text = String.format("$%s", item.paramC)
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamD)
                        llParameter.removeView(tvParamE)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescD)
                        llDescription.removeView(tvDescE)
                        llDescription.removeView(tvDescF)
                    }
                    11 -> {
                        ivComponent.setImageResource(R.mipmap.ic_opsystem)
                        tvComponent.text = String.format("%s %s", item!!.manufaturer, item.component)
                        tvPrice.text = String.format("$%s", item.price)
                        llParameter.removeView(tvParamA)
                        llParameter.removeView(tvParamB)
                        llParameter.removeView(tvParamC)
                        llParameter.removeView(tvParamD)
                        llParameter.removeView(tvParamE)
                        llParameter.removeView(tvParamF)
                        llDescription.removeView(tvDescA)
                        llDescription.removeView(tvDescB)
                        llDescription.removeView(tvDescC)
                        llDescription.removeView(tvDescD)
                        llDescription.removeView(tvDescE)
                        llDescription.removeView(tvDescF)
                    }
                }
            }

            fun constraint() {
                Constraint.set.clone(clCompile)
                Constraint.set.connect(bgTransparent.id, ConstraintSet.TOP, clCompile.id, ConstraintSet.TOP)
                Constraint.set.connect(bgTransparent.id, ConstraintSet.START, clCompile.id, ConstraintSet.START)
                Constraint.set.connect(bgTransparent.id, ConstraintSet.END, clCompile.id, ConstraintSet.END)
                Constraint.set.connect(bgTransparent.id, ConstraintSet.BOTTOM, clCompile.id, ConstraintSet.BOTTOM)
                Constraint.set.connect(layout.id, ConstraintSet.TOP, clCompile.id, ConstraintSet.TOP)
                Constraint.set.connect(layout.id, ConstraintSet.START, clCompile.id, ConstraintSet.START)
                Constraint.set.connect(layout.id, ConstraintSet.END, clCompile.id, ConstraintSet.END)
                Constraint.set.connect(layout.id, ConstraintSet.BOTTOM, clCompile.id, ConstraintSet.BOTTOM)
                Constraint.set.applyTo(clCompile)
            }

            fun listener() {
                bgTransparent.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clCompile)
                    clCompile.removeView(layout)
                    clCompile.removeView(bgTransparent)
                    ivMore.isSelected = false
                }
                ivClose.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clCompile)
                    clCompile.removeView(layout)
                    clCompile.removeView(bgTransparent)
                    ivMore.isSelected = false
                }
                layout.setOnClickListener {

                }
            }
        }
    }
}