package com.corespark.pccompiler.service

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import com.corespark.pccompiler.R.id.*


/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Constraint {

    val set = ConstraintSet()

    fun set(applyTo: View, parentLayout: ConstraintLayout, complete: (Boolean) -> Unit) {
        when (applyTo.id) {
            tvAuthSignIn -> {
                Constraint.set.clone(parentLayout)
                Constraint.set.clear(cvFragAuthSignUp, ConstraintSet.START)
                Constraint.set.clear(cvFragAuthSignUp, ConstraintSet.END)
                Constraint.set.clear(cvFragAuthSignIn, ConstraintSet.END)
                Constraint.set.connect(cvFragAuthSignUp, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                Constraint.set.connect(cvFragAuthSignIn, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                Constraint.set.connect(cvFragAuthSignIn, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                Constraint.set.applyTo(parentLayout)
            }
            tvAuthSignUp -> {
                Constraint.set.clone(parentLayout)
                Constraint.set.clear(cvFragAuthSignIn, ConstraintSet.START)
                Constraint.set.clear(cvFragAuthSignIn, ConstraintSet.END)
                Constraint.set.clear(cvFragAuthSignUp, ConstraintSet.END)
                Constraint.set.connect(cvFragAuthSignIn, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                Constraint.set.connect(cvFragAuthSignUp, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                Constraint.set.connect(cvFragAuthSignUp, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                Constraint.set.applyTo(parentLayout)
            }
            clTabWorkspace -> {
                Constraint.set.clone(parentLayout)
                Constraint.set.clear(clFragActionBar, ConstraintSet.START)
                Constraint.set.clear(clFragControlBar, ConstraintSet.START)
                Constraint.set.clear(clFragControlBar, ConstraintSet.END)
                Constraint.set.connect(clFragActionBar, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                Constraint.set.connect(clFragActionBar, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                Constraint.set.connect(clFragControlBar, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                Constraint.set.applyTo(parentLayout)
            }
            clTabCart -> {
                Constraint.set.clone(parentLayout)
                Constraint.set.clear(clFragActionBar, ConstraintSet.START)
                Constraint.set.clear(clFragActionBar, ConstraintSet.END)
                Constraint.set.clear(clFragControlBar, ConstraintSet.END)
                Constraint.set.connect(clFragActionBar, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                Constraint.set.connect(clFragControlBar, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                Constraint.set.connect(clFragControlBar, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                Constraint.set.applyTo(parentLayout)
            }
            clActionUser -> {
                if (!applyTo.isSelected) {
                    set.clone(parentLayout)
                    set.clear(clFragControlPanel, ConstraintSet.END)
                    set.connect(clFragControlPanel, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 8)
                    set.connect(clFragControlPanel, ConstraintSet.TOP, clFragTitle, ConstraintSet.BOTTOM)
                    set.applyTo(parentLayout)
                    complete(true)
                } else {
                    set.clone(parentLayout)
                    set.clear(clFragControlPanel, ConstraintSet.START)
                    set.connect(clFragControlPanel, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                    set.applyTo(parentLayout)
                    complete(false)
                }
            }
        }
    }

    fun set(applyTo: View, parentLayout: ConstraintLayout, view: View) {
        when (applyTo.id) {
            clTabWorkspace -> {
                when (view.id) {
                    ivTracker -> {
                        set.clone(parentLayout)
                        set.clear(ivTracker, ConstraintSet.END)
                        set.connect(ivTracker, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        set.applyTo(parentLayout)
                    }
                    clFragWorkspace -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(clFragWorkspace, ConstraintSet.END)
                        Constraint.set.clear(clFragCart, ConstraintSet.START)
                        Constraint.set.connect(clFragWorkspace, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        Constraint.set.connect(clFragCart, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        Constraint.set.applyTo(parentLayout)
                    }
                    tvWorkspaceTitle -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(tvWorkspaceTitle, ConstraintSet.END)
                        Constraint.set.clear(tvCartTitle, ConstraintSet.START)
                        Constraint.set.connect(tvWorkspaceTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 32)
                        Constraint.set.connect(tvCartTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        Constraint.set.applyTo(parentLayout)
                    }
                }
            }
            clTabCart -> {
                when (view.id) {
                    ivTracker -> {
                        set.clone(parentLayout)
                        set.clear(ivTracker, ConstraintSet.START)
                        set.connect(ivTracker, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                        set.applyTo(parentLayout)
                    }
                    clFragCart -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(clFragWorkspace, ConstraintSet.START)
                        Constraint.set.clear(clFragWorkspace, ConstraintSet.END)
                        Constraint.set.clear(clFragCart, ConstraintSet.START)
                        Constraint.set.connect(clFragWorkspace, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                        Constraint.set.connect(clFragCart, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        Constraint.set.applyTo(parentLayout)
                    }
                    tvCartTitle -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(tvCartTitle, ConstraintSet.END)
                        Constraint.set.clear(tvWorkspaceTitle, ConstraintSet.END)
                        Constraint.set.connect(tvCartTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 32)
                        Constraint.set.connect(tvWorkspaceTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        Constraint.set.applyTo(parentLayout)
                    }
                }
            }
            bgTransparent -> {
                Constraint.set.clone(parentLayout)
                Constraint.set.connect(bgTransparent, ConstraintSet.TOP, clFragActionBar, ConstraintSet.TOP)
                Constraint.set.connect(bgTransparent, ConstraintSet.BOTTOM, clWorkspace, ConstraintSet.BOTTOM)
                Constraint.set.connect(bgTransparent, ConstraintSet.START, clWorkspace, ConstraintSet.START)
                Constraint.set.connect(bgTransparent, ConstraintSet.END, clWorkspace, ConstraintSet.END)
                Constraint.set.applyTo(parentLayout)
            }
            cvDialogCompile -> {
                Constraint.set.clone(parentLayout)
                Constraint.set.connect(cvDialogCompile, ConstraintSet.TOP, clFragActionBar, ConstraintSet.TOP)
                Constraint.set.connect(cvDialogCompile, ConstraintSet.BOTTOM, clWorkspace, ConstraintSet.BOTTOM)
                Constraint.set.connect(cvDialogCompile, ConstraintSet.START, clWorkspace, ConstraintSet.START)
                Constraint.set.connect(cvDialogCompile, ConstraintSet.END, clWorkspace, ConstraintSet.END)
                Constraint.set.applyTo(parentLayout)
            }
            etDialogCompile -> {
                if (!view.isSelected) {
                    Constraint.set.clone(parentLayout)
                    Constraint.set.connect(etDialogCompile, ConstraintSet.BOTTOM, clDialogCompile, ConstraintSet.BOTTOM, 32)
                    Constraint.set.applyTo(parentLayout)
                } else {
                    Constraint.set.clone(parentLayout)
                    Constraint.set.clear(etDialogCompile, ConstraintSet.BOTTOM)
                    Constraint.set.applyTo(parentLayout)
                }
            }
            clComponent -> {
                if (!applyTo.isSelected) {
                    Constraint.set.clone(parentLayout)
                    Constraint.set.clear(ivComponent, ConstraintSet.BOTTOM)
                    Constraint.set.connect(view.id, ConstraintSet.BOTTOM, parentLayout.id, ConstraintSet.BOTTOM, 16)
                    Constraint.set.applyTo(parentLayout)
                } else {
                    Constraint.set.clone(parentLayout)
                    Constraint.set.clear(view.id, ConstraintSet.BOTTOM)
                    Constraint.set.connect(ivComponent, ConstraintSet.BOTTOM, parentLayout.id, ConstraintSet.BOTTOM, 16)
                    Constraint.set.applyTo(parentLayout)
                }
            }
        }
    }
}