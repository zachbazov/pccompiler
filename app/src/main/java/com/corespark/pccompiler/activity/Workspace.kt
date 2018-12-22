package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
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

    //TODO action bar activation | compilation empty mode | attract user settings for parameters | constraintset

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)

        Broadcast.emit(this, onChannelAuth)

        Window.determineTabSize(this, ivTracker, windowManager, Window.orientation) {}

        customizeView()

        activateView()

        setAdapter(rvCompilation)
    }

    override fun onDestroy() {
        Broadcast.abort(this, onChannelAuth)
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

        Parameter.set(ivTabWorkspace, 72)
        Parameter.set(ivTabCart, 48)

        Parameter.set(ivUser, 96)
        Parameter.set(ivSettings, 64)
        Parameter.set(ivLogout, 64)
        Parameter.set(ivRemove, 64)
        Parameter.set(ivEdit, 64)
        Parameter.set(ivAdd, 64)
        Parameter.set(ivMenu, 64)

        Constraint.set(clActionBar, clDashboard, clUser)
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
                try {
                    view.setOnClickListener {
                        Constraint.set(clTabWorkspace, clTabParent, ivTracker)
                        Parameter.set(ivTabWorkspace, 72)
                        Parameter.set(ivTabCart, 48)
                        Constraint.set(clTabWorkspace, clFragParent, clWorkspaceParent) {
                            if (it) {
                                TransitionManager.beginDelayedTransition(clWorkspaceParent)
                            }
                        }
                        clFragCart.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                        Constraint.set.clone(clFragCartParent)
                        Constraint.set.clear(clFragCart.id, ConstraintSet.TOP)
                        Constraint.set.clear(clFragCart.id, ConstraintSet.BOTTOM)
                        Constraint.set.connect(clFragCart.id, ConstraintSet.TOP, clFragCartParent.id, ConstraintSet.BOTTOM)
                        Constraint.set.applyTo(clFragCartParent)
                    }
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }
            clTabCart.id -> {
                try {
                    view.setOnClickListener {
                        Constraint.set(clTabCart, clTabParent, ivTracker)
                        Parameter.set(ivTabCart, 72)
                        Parameter.set(ivTabWorkspace, 48)
                        Constraint.set(clTabCart, clFragParent, clWorkspaceParent) {
                            if (it) {
                                TransitionManager.beginDelayedTransition(clWorkspaceParent)
                            }
                        }
                        clFragCart.layoutParams.height = ConstraintLayout.LayoutParams.MATCH_PARENT
                        Constraint.set.clone(clFragCartParent)
                        Constraint.set.clear(clFragCart.id, ConstraintSet.TOP)
                        Constraint.set.connect(clFragCart.id, ConstraintSet.TOP, clFragCartParent.id, ConstraintSet.TOP)
                        Constraint.set.connect(clFragCart.id, ConstraintSet.BOTTOM, clFragCartParent.id, ConstraintSet.BOTTOM)
                        Constraint.set.applyTo(clFragCartParent)
                    }
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
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
            //vpViewer.id -> {
            //    vpViewer.adapter = Viewer(this)
            //}
            rvCompilation.id -> {
                Compilation.add()
                rvCompilation.adapter = CompilationBar(this, Compilation.list)
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
