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

    class Auth {

        fun constraint(applyTo: View, parentLayout: ConstraintLayout) {
            when (applyTo.id) {
                tvAuthSignIn -> {
                    set.clone(parentLayout)
                    set.clear(cvFragAuthSignUp, ConstraintSet.START)
                    set.clear(cvFragAuthSignUp, ConstraintSet.END)
                    set.clear(cvFragAuthSignIn, ConstraintSet.END)
                    set.connect(cvFragAuthSignUp, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                    set.connect(cvFragAuthSignIn, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                    set.connect(cvFragAuthSignIn, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                    set.applyTo(parentLayout)
                }
                tvAuthSignUp -> {
                    set.clone(parentLayout)
                    set.clear(cvFragAuthSignIn, ConstraintSet.START)
                    set.clear(cvFragAuthSignIn, ConstraintSet.END)
                    set.clear(cvFragAuthSignUp, ConstraintSet.END)
                    set.connect(cvFragAuthSignIn, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                    set.connect(cvFragAuthSignUp, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                    set.connect(cvFragAuthSignUp, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                    set.applyTo(parentLayout)
                }
            }
        }
    }

    class Workspace {

        fun constraint(applyTo: View, parentLayout: ConstraintLayout, view: View) {
            when (applyTo.id) {
                clTabWorkspace -> when (view.id) {
                    ivTracker -> {
                        set.clone(parentLayout)
                        set.clear(ivTracker, ConstraintSet.END)
                        set.connect(ivTracker, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        set.applyTo(parentLayout)
                    }
                    clTabWorkspace -> {
                        set.clone(parentLayout)
                        set.clear(clFragActionBar, ConstraintSet.START)
                        set.clear(clFragControlBar, ConstraintSet.START)
                        set.clear(clFragControlBar, ConstraintSet.END)
                        set.connect(clFragActionBar, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        set.connect(clFragActionBar, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                        set.connect(clFragControlBar, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                        set.applyTo(parentLayout)
                    }
                    clFragWorkspace -> {
                        set.clone(parentLayout)
                        set.clear(clFragWorkspace, ConstraintSet.END)
                        set.clear(clFragCart, ConstraintSet.START)
                        set.clear(clFragCart, ConstraintSet.END)
                        set.clear(clFragCart, ConstraintSet.BOTTOM)
                        set.connect(clFragWorkspace, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        set.connect(clFragCart, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        set.applyTo(parentLayout)
                    }
                    tvWorkspaceTitle -> {
                        set.clone(parentLayout)
                        set.clear(tvWorkspaceTitle, ConstraintSet.END)
                        set.clear(tvCartTitle, ConstraintSet.START)
                        set.connect(tvWorkspaceTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 32)
                        set.connect(tvCartTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        set.applyTo(parentLayout)
                    }
                }
                clTabCart -> when (view.id) {
                    ivTracker -> {
                        set.clone(parentLayout)
                        set.clear(ivTracker, ConstraintSet.START)
                        set.connect(ivTracker, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                        set.applyTo(parentLayout)
                    }
                    clTabCart -> {
                        set.clone(parentLayout)
                        set.clear(clFragActionBar, ConstraintSet.START)
                        set.clear(clFragActionBar, ConstraintSet.END)
                        set.clear(clFragControlBar, ConstraintSet.END)
                        set.connect(clFragActionBar, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        set.connect(clFragControlBar, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        set.connect(clFragControlBar, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                        set.applyTo(parentLayout)
                    }
                    clFragCart -> {
                        set.clone(parentLayout)
                        set.clear(clFragWorkspace, ConstraintSet.START)
                        set.clear(clFragWorkspace, ConstraintSet.END)
                        set.clear(clFragCart, ConstraintSet.START)
                        set.connect(clFragWorkspace, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                        set.connect(clFragCart, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        set.connect(clFragCart, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                        set.connect(clFragCart, ConstraintSet.BOTTOM, parentLayout.id, ConstraintSet.BOTTOM)
                        set.applyTo(parentLayout)
                    }
                    tvCartTitle -> {
                        set.clone(parentLayout)
                        set.clear(tvCartTitle, ConstraintSet.END)
                        set.clear(tvWorkspaceTitle, ConstraintSet.END)
                        set.connect(tvCartTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 32)
                        set.connect(tvWorkspaceTitle, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        set.applyTo(parentLayout)
                    }
                }
            }
        }

        fun constraint(applyTo: View, parentLayout: ConstraintLayout, complete: (Boolean) -> Unit) {
            when (applyTo.id) {
                clActionUser -> if (!applyTo.isSelected) {
                    set.clone(parentLayout)
                    set.clear(clFragControlPanel, ConstraintSet.END)
                    set.connect(clFragControlPanel, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                    set.connect(clFragControlPanel, ConstraintSet.TOP, clFragActionBar, ConstraintSet.BOTTOM)
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

    class Dialog {

        fun constraint(applyTo: View, parentLayout: ConstraintLayout) {
            when (applyTo.id) {
                bgTransparent -> {
                    set.clone(parentLayout)
                    set.connect(bgTransparent, ConstraintSet.TOP, clFragActionBar, ConstraintSet.TOP)
                    set.connect(bgTransparent, ConstraintSet.BOTTOM, clWorkspaceParent, ConstraintSet.BOTTOM)
                    set.connect(bgTransparent, ConstraintSet.START, clWorkspaceParent, ConstraintSet.START)
                    set.connect(bgTransparent, ConstraintSet.END, clWorkspaceParent, ConstraintSet.END)
                    set.applyTo(parentLayout)
                }
                bgTransparentFull -> {
                    set.clone(parentLayout)
                    set.connect(bgTransparentFull, ConstraintSet.TOP, parentLayout.id, ConstraintSet.TOP)
                    set.connect(bgTransparentFull, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                    set.connect(bgTransparentFull, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                    set.connect(bgTransparentFull, ConstraintSet.BOTTOM, parentLayout.id, ConstraintSet.BOTTOM)
                    set.applyTo(parentLayout)
                }
                layout -> {
                    set.clone(parentLayout)
                    set.connect(applyTo.id, ConstraintSet.TOP, parentLayout.id, ConstraintSet.TOP)
                    set.connect(applyTo.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                    set.connect(applyTo.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                    set.connect(applyTo.id, ConstraintSet.BOTTOM, parentLayout.id, ConstraintSet.BOTTOM)
                    set.applyTo(parentLayout)
                }
                etPrelim -> if (!applyTo.isSelected) {
                    set.clone(parentLayout)
                    set.connect(etPrelim, ConstraintSet.BOTTOM, clPrelim, ConstraintSet.BOTTOM, 32)
                    set.applyTo(parentLayout)
                } else {
                    set.clone(parentLayout)
                    set.clear(etPrelim, ConstraintSet.BOTTOM)
                    set.applyTo(parentLayout)
                }
            }
        }
    }
}