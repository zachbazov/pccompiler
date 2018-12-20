package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.utility.*
import com.corespark.pccompiler.adapter.CompilationBar
import com.corespark.pccompiler.model.Compilation
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

        setAdapter(rvCompilation)
    }

    override fun onDestroy() {
        Broadcast.abort(this, channelSignIn)
        super.onDestroy()
    }

    private fun customizeView() {
        setValue(ivTabWorkspace)
        setValue(ivTabCart)
        setValue(ivUser)
        setValue(ivRemove)
        setValue(ivEdit)
        setValue(ivAdd)
        setValue(ivMenu)
        setValue(ivSettings)
        setValue(ivLogout)
        setValue(tvRemove)
        setValue(tvEdit)
        setValue(tvAdd)
        setValue(tvMenu)
        setValue(tvSettings)
        setValue(tvLogout)

        Parameter.set(ivTabWorkspace, VALUE_72)
        Parameter.set(ivTabCart, VALUE_48)

        Parameter.set(ivUser, VALUE_96)
        Parameter.set(ivSettings, VALUE_64)
        Parameter.set(ivLogout, VALUE_64)
        Parameter.set(ivRemove, VALUE_64)
        Parameter.set(ivEdit, VALUE_64)
        Parameter.set(ivAdd, VALUE_64)
        Parameter.set(ivMenu, VALUE_64)

        Constraint.set.clone(clDashboard)
        Constraint.set.clear(clActionBar.id, ConstraintSet.START)
        Constraint.set.clear(clActionBar.id, ConstraintSet.END)
        Constraint.set.connect(clActionBar.id, ConstraintSet.START, clDashboard.id, ConstraintSet.START)
        Constraint.set.connect(clActionBar.id, ConstraintSet.END, clUser.id, ConstraintSet.START)
        Constraint.set.applyTo(clDashboard)
    }

    private fun activateView() {
        onClick(clTabWorkspace)
        onClick(clTabCart)
        onClick(clUser)
        onClick(ivLogout)
    }

    private fun onClick(view: View) {
        when (view.id) {
            clTabWorkspace.id -> {
                view.setOnClickListener {
                    Constraint.set(clTabWorkspace, clTabParent, ivTracker)
                    Parameter.set(ivTabWorkspace, VALUE_72)
                    Parameter.set(ivTabCart, VALUE_48)
                }
            }
            clTabCart.id -> {
                view.setOnClickListener {
                    Constraint.set(clTabCart, clTabParent, ivTracker)
                    Parameter.set(ivTabCart, VALUE_72)
                    Parameter.set(ivTabWorkspace, VALUE_48)
                }
            }
            clUser.id -> {
                view.setOnClickListener {
                    Constraint.set(view, clDashboard, clUser, clActionBar, clControlPanel) { complete ->
                        if (complete) {
                            ivUser.setImageResource(R.drawable.ic_profile_active)
                            view.isSelected = !view.isSelected
                        } else {
                            ivUser.setImageResource(R.drawable.ic_profile_inactive)
                            view.isSelected = !view.isSelected
                        }
                    }
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

    private fun setValue(view: View) {
        when (view.id) {
            ivTabWorkspace.id -> { ivTabWorkspace.setImageResource(R.drawable.ic_workspace_active) }
            ivTabCart.id -> { ivTabCart.setImageResource(R.drawable.ic_cart_active) }
            ivUser.id -> { ivUser.setImageResource(R.drawable.ic_profile_inactive) }
            ivRemove.id -> { ivRemove.setImageResource(R.drawable.ic_remove_inactive) }
            ivEdit.id -> { ivEdit.setImageResource(R.drawable.ic_edit_inactive) }
            ivAdd.id -> { ivAdd.setImageResource(R.drawable.ic_add_inactive) }
            ivMenu.id -> { ivMenu.setImageResource(R.drawable.ic_menu_inactive) }
            ivSettings.id -> { ivSettings.setImageResource(R.drawable.ic_settings_inactive) }
            ivLogout.id -> { ivLogout.setImageResource(R.drawable.ic_logout_inactive) }
            tvRemove.id -> { tvRemove.text = getString(R.string.text_remove) }
            tvEdit.id -> { tvEdit.text = getString(R.string.text_edit) }
            tvAdd.id -> { tvAdd.text = getString(R.string.text_add) }
            tvMenu.id -> { tvMenu.text = getString(R.string.text_menu) }
            tvSettings.id -> { tvSettings.text = getString(R.string.text_settings) }
            tvLogout.id -> { tvLogout.text = getString(R.string.text_logout) }
        }
    }

    private fun setAdapter(view: View) {
        when (view.id) {
            rvCompilation.id -> {
                Compilation.add()
                rvCompilation.adapter = CompilationBar(this, Compilation.list)
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
