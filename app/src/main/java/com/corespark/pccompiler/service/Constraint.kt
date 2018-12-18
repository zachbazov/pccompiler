package com.corespark.pccompiler.service

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R.id.btnAuthSignIn
import com.corespark.pccompiler.R.id.btnAuthSignUp


/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Constraint {

    val set = ConstraintSet()

    fun set(applyTo: View, parentLayout: ConstraintLayout, subLayout: ConstraintLayout, start: View, end: View) {
        when (applyTo.id) {
            btnAuthSignIn -> {
                set.clone(parentLayout)
                set.connect(start.id, ConstraintSet.TOP, parentLayout.id, ConstraintSet.BOTTOM)
                set.connect(start.id, ConstraintSet.LEFT, parentLayout.id, ConstraintSet.RIGHT)
                set.connect(end.id, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM)
                set.applyTo(parentLayout)

            }
            btnAuthSignUp -> {
                set.clone(parentLayout)
                set.connect(start.id, ConstraintSet.TOP, parentLayout.id, ConstraintSet.BOTTOM)
                set.connect(start.id, ConstraintSet.LEFT, parentLayout.id, ConstraintSet.RIGHT)
                set.connect(end.id, ConstraintSet.TOP, subLayout.id, ConstraintSet.BOTTOM)
                set.applyTo(parentLayout)
            }
        }
        TransitionManager.beginDelayedTransition(parentLayout)
    }
}