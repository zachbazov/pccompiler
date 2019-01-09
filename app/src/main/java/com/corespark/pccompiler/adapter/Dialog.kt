package com.corespark.pccompiler.adapter

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
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
import com.corespark.pccompiler.service.Intent


/**
 * @author Zachy Bazov.
 * @since 08/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Dialog(val context: Context, val type: Int) {

    inner class Workspace {

        val clWorkspace = (context as com.corespark.pccompiler.activity.Workspace).clWorkspace

        inner class Compilation {

            val bgTransparent = ConstraintLayout(context)
            val layout = LayoutInflater.from(context).inflate(R.layout.dialog_compile, clWorkspace, false)
            val clDialog = layout.findViewById<ConstraintLayout>(R.id.clDialogCompile)
            val ivDialog = layout.findViewById<ImageView>(R.id.ivDialogCompile)
            val tvDialog = layout.findViewById<TextView>(R.id.tvDialogCompile)
            val etDialog = layout.findViewById<EditText>(R.id.etDialogCompile)
            val btnDialog = layout.findViewById<Button>(R.id.btnDialogCompile)

            fun create(view: View) {
                if (!view.isSelected) {
                    view.isSelected = true
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

            fun customize() {
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

            fun listener(view: View) {
                when (view.id) {
                    ivDialog.id -> {
                        ivDialog.setOnClickListener {
                            TransitionManager.beginDelayedTransition(clWorkspace)
                            clWorkspace.removeView(layout)
                            clWorkspace.removeView(bgTransparent)
                            view.isSelected = false
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
                            (context as Activity).startActivity(Intent.launch(context, R.layout.activity_compile))
                            context.finish()
                        }
                    }
                }
            }
        }
    }
}