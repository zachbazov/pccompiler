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
import com.corespark.pccompiler.adapter.CompilationBar
import com.corespark.pccompiler.model.*
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

        val clicks = arrayOf(clTabWorkspace, clTabCart)
        for (click in clicks) onClick(click)

        val adapters = arrayOf(rvActionBar, rvControlPanel, rvCompilation, rvCart, rvCartBar)
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
                        Constraint.set(clTabWorkspace, clDashboard, clFragCartBarParent, clActionBar)
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
                        Constraint.set(clTabCart, clDashboard, clFragCartBarParent, clActionBar)
                    }
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }
        }
    }

    private fun setAdapter(view: View) {
        when (view.id) {
            rvActionBar.id -> {
                ActionBar.addActions(this)
                rvActionBar.adapter = com.corespark.pccompiler.adapter.ActionBar(
                    this, this, ActionBar.actionList)
            }
            rvControlPanel.id -> {
                ControlPanel.addControls(this)
                rvControlPanel.adapter = com.corespark.pccompiler.adapter.ControlPanel(
                    this, this, ControlPanel.controlList)
            }
            rvCompilation.id -> {
                Compilation.add()
                compilationBar = CompilationBar(this, Compilation.list)
                rvCompilation.adapter = compilationBar
            }
            rvCart.id -> {
                rvCart.adapter = CompilationBar(this, Compilation.list)
            }
            rvCartBar.id -> {
                CartBar.addActions(this)
                rvCartBar.adapter = com.corespark.pccompiler.adapter.CartBar(
                    this, this, CartBar.actionList)
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
