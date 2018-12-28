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
                    clFragWorkspaceParent -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(clFragWorkspaceParent, ConstraintSet.END)
                        Constraint.set.clear(clFragCartParent, ConstraintSet.START)
                        Constraint.set.connect(clFragWorkspaceParent, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                        Constraint.set.connect(clFragCartParent, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
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
                    clFragCartParent -> {
                        Constraint.set.clone(parentLayout)
                        Constraint.set.clear(clFragWorkspaceParent, ConstraintSet.START)
                        Constraint.set.clear(clFragWorkspaceParent, ConstraintSet.END)
                        Constraint.set.clear(clFragCartParent, ConstraintSet.START)
                        Constraint.set.connect(clFragWorkspaceParent, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                        Constraint.set.connect(clFragCartParent, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
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
                    clActionBar -> {
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
                    clActionBar -> {
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
            R.id.actionBarLayout0 -> {
                if (!applyTo.isSelected) {
                    set.clone(parentLayout)
                    set.clear(view.id, ConstraintSet.END)
                    set.connect(view.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 8)
                    set.connect(view.id, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM, 8)
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