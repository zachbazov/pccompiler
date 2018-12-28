package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.adapter.CompilationBar
import com.corespark.pccompiler.model.ActionBar
import com.corespark.pccompiler.model.Compilation
import com.corespark.pccompiler.model.User
import kotlinx.android.synthetic.main.activity_workspace.*


/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Workspace : AppCompatActivity() {

    //TODO action bar activation | compilation empty mode | rvCart modification

    lateinit var actionBar: com.corespark.pccompiler.adapter.ActionBar
    lateinit var compilationBar: CompilationBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)

        Broadcast.emit(this, onChannelAuth)

        Window.determineSpan(this, ivTracker, windowManager, Window.orientation, 2) {}

        customize()
    }

    override fun onDestroy() {
        Broadcast.abort(this, onChannelAuth)
        super.onDestroy()
    }

    private fun customize() {
        Constraint.set(clTabWorkspace, clWorkspaceParent, clFragWorkspaceParent)

        val values = arrayOf(ivTabWorkspace, ivTabCart)
        for (value in values) setValue(value)

        val params = arrayOf(ivTabWorkspace, ivTabCart)
        for (param in params)
            when (param) {
                ivTabWorkspace -> { Parameter.set(param, 72) }
                ivTabCart -> { Parameter.set(param, 48) }
                else -> { Parameter.set(param, 64) }
            }

        val clicks = arrayOf(clTabWorkspace, clTabCart, ivLogout)
        for (click in clicks) onClick(click)

        val adapters = arrayOf(rvActionBar, rvCompilation, rvCart)
        for (adapter in adapters) setAdapter(adapter)
    }

    private fun setValue(view: View) {
        when (view.id) {
            ivTabWorkspace.id -> { ivTabWorkspace.setImageResource(R.drawable.ic_workspace_active) }
            ivTabCart.id -> { ivTabCart.setImageResource(R.drawable.ic_cart_active) }
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

                        Constraint.set.clone(clDashboard)
                        Constraint.set.clear(clActionBar.id, ConstraintSet.START)
                        Constraint.set.connect(clActionBar.id, ConstraintSet.START, clDashboard.id, ConstraintSet.START)
                        Constraint.set.connect(clActionBar.id, ConstraintSet.END, clDashboard.id, ConstraintSet.END)
                        Constraint.set.applyTo(clDashboard)

                        Constraint.set.clone(clDashboard)
                        Constraint.set.clear(clCartBar.id, ConstraintSet.START)
                        Constraint.set.clear(clCartBar.id, ConstraintSet.END)
                        Constraint.set.connect(clCartBar.id, ConstraintSet.END, clDashboard.id, ConstraintSet.START)
                        Constraint.set.applyTo(clDashboard)
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

                        Constraint.set.clone(clDashboard)
                        Constraint.set.clear(clActionBar.id, ConstraintSet.START)
                        Constraint.set.clear(clActionBar.id, ConstraintSet.END)
                        Constraint.set.connect(clActionBar.id, ConstraintSet.START, clDashboard.id, ConstraintSet.END)
                        Constraint.set.applyTo(clDashboard)

                        Constraint.set.clone(clDashboard)
                        Constraint.set.clear(clCartBar.id, ConstraintSet.END)
                        Constraint.set.connect(clCartBar.id, ConstraintSet.START, clDashboard.id, ConstraintSet.START)
                        Constraint.set.connect(clCartBar.id, ConstraintSet.END, clDashboard.id, ConstraintSet.END)
                        Constraint.set.applyTo(clDashboard)
                    }
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }
            ivLogout.id -> {
                view.setOnClickListener {
                    com.corespark.pccompiler.service.Auth.logOut(this) { complete ->
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
            rvActionBar.id -> {
                ActionBar.add(this)
                actionBar = com.corespark.pccompiler.adapter.ActionBar(this, this, ActionBar.list)
                rvActionBar.adapter = actionBar

            }
            rvCompilation.id -> {
                Compilation.add()
                compilationBar = CompilationBar(this, Compilation.list)
                rvCompilation.adapter = compilationBar
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
                User.username = Compiler.preferences.username
            }
        }
    }
}
