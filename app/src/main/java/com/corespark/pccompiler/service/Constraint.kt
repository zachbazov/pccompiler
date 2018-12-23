package com.corespark.pccompiler.service

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
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
                        Constraint.set.clear(clFragCartParent, ConstraintSet.END)
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

    fun set(applyTo: View, parentLayout: ConstraintLayout, complete: (Boolean) -> Unit) {
        when (applyTo.id) {
            clUser -> {
                if (!applyTo.isSelected) {
                    Constraint.set.clone(parentLayout)
                    Constraint.set.clear(clUser, ConstraintSet.END)
                    Constraint.set.clear(clActionBar, ConstraintSet.START)
                    Constraint.set.clear(clActionBar, ConstraintSet.END)
                    Constraint.set.clear(clControlPanel, ConstraintSet.START)
                    Constraint.set.connect(clUser, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 32)
                    Constraint.set.connect(clActionBar, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
                    Constraint.set.connect(clControlPanel, ConstraintSet.START, clUser, ConstraintSet.END, 32)
                    Constraint.set.applyTo(parentLayout)
                    complete(true)
                } else {
                    Constraint.set.clone(parentLayout)
                    Constraint.set.clear(clUser, ConstraintSet.START)
                    Constraint.set.clear(clActionBar, ConstraintSet.START)
                    Constraint.set.clear(clActionBar, ConstraintSet.END)
                    Constraint.set.clear(clControlPanel, ConstraintSet.START)
                    Constraint.set.clear(clControlPanel, ConstraintSet.END)
                    Constraint.set.connect(clUser, ConstraintSet.END, parentLayout.id, ConstraintSet.END, 32)
                    Constraint.set.connect(clActionBar, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                    Constraint.set.connect(clActionBar, ConstraintSet.END, clUser, ConstraintSet.START)
                    Constraint.set.connect(clControlPanel, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
                    Constraint.set.applyTo(parentLayout)
                    complete(false)
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
}