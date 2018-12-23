package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.service.Auth
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

    //TODO action bar activation | compilation empty mode | rvCart modification.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)

        Broadcast.emit(this, onChannelAuth)

        Window.determineTabSize(this, ivTracker, windowManager, Window.orientation) {}

        customizeLayout()
    }

    override fun onDestroy() {
        Broadcast.abort(this, onChannelAuth)
        super.onDestroy()
    }

    private fun customizeLayout() {
        Constraint.set(clTabWorkspace, clWorkspaceParent, clFragWorkspaceParent)

        val values = arrayOf(ivTabWorkspace, ivTabCart, ivUser, ivRemove, ivEdit, ivAdd, ivMenu, ivSettings, ivLogout,
            tvRemove, tvEdit, tvAdd, tvMenu, tvSettings, tvLogout)
        for (value in values) setValue(value)

        val params = arrayOf(ivTabWorkspace, ivTabCart, ivUser, ivRemove, ivEdit, ivAdd, ivMenu, ivSettings, ivLogout)
        for (param in params) {
            when (param) {
                ivTabWorkspace -> { Parameter.set(param, 72) }
                ivTabCart -> { Parameter.set(param, 48) }
                ivUser -> { Parameter.set(param, 96) }
                else -> { Parameter.set(param, 64) }
            }
        }

        val clicks = arrayOf(clTabWorkspace, clTabCart, clUser, ivLogout)
        for (click in clicks) onClick(click)

        val adapters = arrayOf(rvCompilation, rvCart)
        for (adapter in adapters) setAdapter(adapter)
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

    private fun onClick(view: View) {
        when (view.id) {
            clTabWorkspace.id -> {
                try {
                    view.setOnClickListener {
                        TransitionManager.beginDelayedTransition(clWorkspaceParent)
                        Parameter.set(ivTabWorkspace, 72)
                        Parameter.set(ivTabCart, 48)
                        Constraint.set(clTabWorkspace, clTabParent, ivTracker)
                        Constraint.set(clTabWorkspace, clWorkspaceParent, clFragWorkspaceParent)
                    }
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }
            clTabCart.id -> {
                try {
                    view.setOnClickListener {
                        TransitionManager.beginDelayedTransition(clWorkspaceParent)
                        Parameter.set(ivTabCart, 72)
                        Parameter.set(ivTabWorkspace, 48)
                        Constraint.set(clTabCart, clTabParent, ivTracker)
                        Constraint.set(clTabCart, clWorkspaceParent, clFragCartParent)
                    }
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }
            clUser.id -> {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clDashboard)
                    Constraint.set(view, clDashboard) { complete ->
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
                    Auth.logOut(this, tvUser) { complete ->
                        if (complete) {
                            startActivity(Intent.launch(this, R.layout.activity_auth))
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun setAdapter(view: View) {
        when (view.id) {
            rvCompilation.id -> {
                Compilation.add()
                rvCompilation.adapter = CompilationBar(this, Compilation.list)
            }
            rvCart.id -> {
                Compilation.add()
                rvCart.adapter = CompilationBar(this, Compilation.list)
            }
        }
    }

    private val onChannelAuth = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: android.content.Intent?) {
            if (Compiler.preferences.isLoggedIn) {
                tvUser.text = Compiler.preferences.username
            }
        }
    }
}
