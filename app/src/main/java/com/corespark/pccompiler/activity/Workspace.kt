package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.utility.ACTIVITY_AUTH
import kotlinx.android.synthetic.main.activity_workspace.*

/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Workspace : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)

        Broadcast.emit(this, channelSignIn)

        Window.determineTabSize(this, ivTracker, windowManager, Window.orientation) {}

        customizeView()

        activateView()

        clUser.setOnClickListener {
            if (!it.isSelected) {
                Constraint.set.clone(clDashboard)
                Constraint.set.clear(clUser.id, ConstraintSet.END)
                Constraint.set.connect(clUser.id, ConstraintSet.START, clDashboard.id, ConstraintSet.START)
                Constraint.set.applyTo(clDashboard)
                ivUser.setImageResource(R.drawable.ic_profile_active)
                it.isSelected = !it.isSelected
            } else {
                Constraint.set.clone(clDashboard)
                Constraint.set.clear(clUser.id, ConstraintSet.START)
                Constraint.set.connect(clUser.id, ConstraintSet.END, clDashboard.id, ConstraintSet.END, 32)
                Constraint.set.applyTo(clDashboard)
                ivUser.setImageResource(R.drawable.ic_profile_inactive)
                it.isSelected = !it.isSelected
            }

            Handler().postDelayed({
                if (!ivLogout.isSelected) {
                    Constraint.set.clone(clDashboard)
                    Constraint.set.clear(ivSettings.id, ConstraintSet.START)
                    Constraint.set.clear(ivLogout.id, ConstraintSet.START)
                    Constraint.set.clear(ivRemove.id, ConstraintSet.END)
                    Constraint.set.connect(ivSettings.id, ConstraintSet.START, clUser.id, ConstraintSet.END, 32)
                    Constraint.set.connect(ivLogout.id, ConstraintSet.START, ivSettings.id, ConstraintSet.END, 64)
                    Constraint.set.connect(ivRemove.id, ConstraintSet.END, clDashboard.id, ConstraintSet.START, 32)
                    Constraint.set.applyTo(clDashboard)
                    ivLogout.isSelected = !ivLogout.isSelected
                } else {
                    Constraint.set.clone(clDashboard)
                    Constraint.set.clear(ivSettings.id, ConstraintSet.START)
                    Constraint.set.clear(ivLogout.id, ConstraintSet.START)
                    Constraint.set.clear(ivRemove.id, ConstraintSet.END)
                    Constraint.set.connect(ivRemove.id, ConstraintSet.END, clUser.id, ConstraintSet.START, 48)
                    Constraint.set.connect(ivSettings.id, ConstraintSet.START, clDashboard.id, ConstraintSet.END)
                    Constraint.set.connect(ivLogout.id, ConstraintSet.START, clDashboard.id, ConstraintSet.END)
                    Constraint.set.applyTo(clDashboard)
                    ivLogout.isSelected = !ivLogout.isSelected
                }
                TransitionManager.beginDelayedTransition(clDashboard)
            }, 300)

            TransitionManager.beginDelayedTransition(clDashboard)
        }
    }

    override fun onDestroy() {
        Broadcast.abort(this, channelSignIn)
        super.onDestroy()
    }

    private fun customizeView() {
        ivTabWorkspace.setImageResource(R.drawable.ic_workspace_active)
        ivTabTrolley.setImageResource(R.drawable.ic_cart_active)

        Parameter.set(ivTabWorkspace, 72)

        ivUser.setImageResource(R.drawable.ic_profile_inactive)
        ivSettings.setImageResource(R.drawable.ic_settings_inactive)
        ivLogout.setImageResource(R.drawable.ic_logout_inactive)
        ivRemove.setImageResource(R.drawable.ic_remove_inactive)
        ivEdit.setImageResource(R.drawable.ic_edit_inactive)
        ivAdd.setImageResource(R.drawable.ic_add_inactive)
        ivMenu.setImageResource(R.drawable.ic_menu_inactive)

        tvRemove.text = "REMOVE"
        tvEdit.text = "EDIT"
        tvAdd.text = "ADD"
        tvMenu.text = "MENU"

        Parameter.set(ivUser, 94)
        Parameter.set(ivSettings, 64)
        Parameter.set(ivLogout, 64)
        Parameter.set(ivRemove, 64)
        Parameter.set(ivEdit, 64)
        Parameter.set(ivAdd, 64)
        Parameter.set(ivMenu, 64)
    }

    private fun activateView() {
        onClick(clTabWorkspace)
        onClick(clTabTrolley)
        onClick(ivLogout)
    }

    private fun onClick(view: View) {
        when (view.id) {
            clTabWorkspace.id -> {
                view.setOnClickListener {
                    Constraint.set(clTabWorkspace, clTabParent, ivTracker)
                    Parameter.set(ivTabWorkspace, 72)
                    Parameter.set(ivTabTrolley, 48)
                }
            }
            clTabTrolley.id -> {
                view.setOnClickListener {
                    Constraint.set(clTabTrolley, clTabParent, ivTracker)
                    Parameter.set(ivTabTrolley, 80)
                    Parameter.set(ivTabWorkspace, 44)
                }
            }
            ivLogout.id -> {
                view.setOnClickListener {
                    Auth.logOut(tvUser) { complete ->
                        if (complete) {
                            startActivity(Intent.launch(this, ACTIVITY_AUTH))
                        }
                    }
                }
            }
        }
    }

    private val channelSignIn = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: android.content.Intent?) {
            if (Compiler.preferences.isLoggedIn) {
                tvUser.text = Compiler.preferences.username
            }
        }
    }
}
