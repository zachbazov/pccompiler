package com.corespark.pccompiler.service

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import com.corespark.pccompiler.R
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
                        Constraint.set.connect(tvWorkspaceTitle, ConstraintSet.START, clFragTitle, ConstraintSet.START, 32)
                        Constraint.set.connect(tvCartTitle, ConstraintSet.START, clFragTitle, ConstraintSet.END)
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
                        Constraint.set.connect(tvCartTitle, ConstraintSet.START, clFragTitle, ConstraintSet.START, 32)
                        Constraint.set.connect(tvWorkspaceTitle, ConstraintSet.START, clFragTitle, ConstraintSet.END)
                        Constraint.set.applyTo(parentLayout)
                    }
                }
            }
        }
    }

    fun set(applyTo: View, parentLayout: ConstraintLayout, subLayout: ConstraintLayout, view: View) {
        when (applyTo.id) {
            clTabWorkspace -> {
                when (view.id) {
                    clFragActionBar -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(view.id, ConstraintSet.START)
                        Constraint.set.clear(subLayout.id, ConstraintSet.START)
                        Constraint.set.clear(subLayout.id, ConstraintSet.END)
                        Constraint.set.connect(view.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        Constraint.set.connect(view.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                        Constraint.set.connect(subLayout.id, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                        Constraint.set.applyTo(parentLayout)
                    }
                }
            }
            clTabCart -> {
                when (view.id) {
                    clFragActionBar -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(view.id, ConstraintSet.START)
                        Constraint.set.clear(view.id, ConstraintSet.END)
                        Constraint.set.clear(subLayout.id, ConstraintSet.END)
                        Constraint.set.connect(view.id, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                        Constraint.set.connect(subLayout.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        Constraint.set.connect(subLayout.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                        Constraint.set.applyTo(parentLayout)
                    }
                }
            }
        }
    }

    fun set(applyTo: View, parentLayout: ConstraintLayout, subLayout: ConstraintLayout, complete: (Boolean) -> Unit) {
        when (applyTo.id) {
            btnSignIn -> {
                set.clone(parentLayout)
                set.connect(clAuthDialogSignUp, ConstraintSet.TOP, parentLayout.id, ConstraintSet.BOTTOM)
                set.connect(clAuthDialogSignUp, ConstraintSet.LEFT, parentLayout.id, ConstraintSet.RIGHT)
                set.connect(clAuthDialogSignIn, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM)
                set.applyTo(parentLayout)
                complete(true)
            }
            btnSignUp -> {
                set.clone(parentLayout)
                set.connect(clAuthDialogSignIn, ConstraintSet.TOP, parentLayout.id, ConstraintSet.BOTTOM)
                set.connect(clAuthDialogSignIn, ConstraintSet.LEFT, parentLayout.id, ConstraintSet.RIGHT)
                set.connect(clAuthDialogSignUp, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM)
                set.applyTo(parentLayout)
                complete(true)
            }
        }
    }

    fun set(applyTo: View, parentLayout: ConstraintLayout, subLayout: ConstraintLayout, view: View, complete: (Boolean) -> Unit) {
        when (applyTo.id) {
            R.id.clActionUser -> {
                if (!applyTo.isSelected) {
                    set.clone(parentLayout)
                    set.clear(view.id, ConstraintSet.END)
                    set.connect(view.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 8)
                    set.connect(view.id, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM)
                    set.applyTo(parentLayout)
                    complete(true)
                } else {
                    set.clone(parentLayout)
                    set.clear(view.id, ConstraintSet.START)
                    set.connect(view.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.START)
                    set.applyTo(parentLayout)
                    complete(false)
                }
            }

        }
    }
}