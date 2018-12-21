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
                set.clone(parentLayout)
                set.clear(view.id, ConstraintSet.END)
                set.connect(view.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
                set.applyTo(parentLayout)
            }
            clTabCart -> {
                set.clone(parentLayout)
                set.clear(view.id, ConstraintSet.START)
                set.connect(view.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END)
                set.applyTo(parentLayout)
            }
            clActionBar -> {
               set.clone(parentLayout)
               set.clear(applyTo.id, ConstraintSet.START)
               set.clear(applyTo.id, ConstraintSet.END)
               set.connect(applyTo.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
               set.connect(applyTo.id, ConstraintSet.END, view.id, ConstraintSet.START)
               set.applyTo(parentLayout)
            }
        }
        TransitionManager.beginDelayedTransition(parentLayout)
    }

    fun set(applyTo: View, parentLayout: ConstraintLayout, subLayout: ConstraintLayout, start: View, end: View) {
        when (applyTo.id) {
            btnSignIn -> {
                set.clone(parentLayout)
                set.connect(start.id, ConstraintSet.TOP, parentLayout.id, ConstraintSet.BOTTOM)
                set.connect(start.id, ConstraintSet.LEFT, parentLayout.id, ConstraintSet.RIGHT)
                set.connect(end.id, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM)
                set.applyTo(parentLayout)
            }
            btnSignUp -> {
                set.clone(parentLayout)
                set.connect(start.id, ConstraintSet.TOP, parentLayout.id, ConstraintSet.BOTTOM)
                set.connect(start.id, ConstraintSet.LEFT, parentLayout.id, ConstraintSet.RIGHT)
                set.connect(end.id, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM)
                set.applyTo(parentLayout)
            }
        }
        TransitionManager.beginDelayedTransition(parentLayout)
    }

    fun set(
        applyTo: View, parentLayout: ConstraintLayout, middlePane: View, actionPane: View, controlPane: View,
        complete: (Boolean) -> Unit
    ) {
        if (!applyTo.isSelected) {
            Constraint.set.clone(parentLayout)
            Constraint.set.clear(middlePane.id, ConstraintSet.END)
            Constraint.set.clear(actionPane.id, ConstraintSet.START)
            Constraint.set.clear(actionPane.id, ConstraintSet.END)
            Constraint.set.clear(controlPane.id, ConstraintSet.START)
            Constraint.set.connect(middlePane.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START, 32)
            Constraint.set.connect(actionPane.id, ConstraintSet.END, parentLayout.id, ConstraintSet.START)
            Constraint.set.connect(controlPane.id, ConstraintSet.START, middlePane.id, ConstraintSet.END, 32)
            Constraint.set.applyTo(parentLayout)
            complete(true)
        } else {
            Constraint.set.clone(parentLayout)
            Constraint.set.clear(middlePane.id, ConstraintSet.START)
            Constraint.set.clear(actionPane.id, ConstraintSet.START)
            Constraint.set.clear(actionPane.id, ConstraintSet.END)
            Constraint.set.clear(controlPane.id, ConstraintSet.START)
            Constraint.set.clear(controlPane.id, ConstraintSet.END)
            Constraint.set.connect(middlePane.id, ConstraintSet.END, parentLayout.id, ConstraintSet.END, 32)
            Constraint.set.connect(actionPane.id, ConstraintSet.START, parentLayout.id, ConstraintSet.START)
            Constraint.set.connect(actionPane.id, ConstraintSet.END, middlePane.id, ConstraintSet.START)
            Constraint.set.connect(controlPane.id, ConstraintSet.START, parentLayout.id, ConstraintSet.END)
            Constraint.set.applyTo(parentLayout)
            complete(false)
        }
        TransitionManager.beginDelayedTransition(parentLayout)
    }
}