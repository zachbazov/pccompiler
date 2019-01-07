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
import com.corespark.pccompiler.adapter.Recycler
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

    //TODO action bar activation | rvCart modification + dragNdrog | fix purity in xml | progressbars

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)

        Broadcast.emit(this, onChannelAuth)

        customize()
    }

    override fun onDestroy() {
        Broadcast.abort(this, onChannelAuth)
        super.onDestroy()
    }

    private fun customize() {
        Window.determineSpan(this, ivTracker, windowManager, Window.orientation, 2) {}

        Constraint.set(clTabWorkspace, clWorkspace, clFragWorkspace)

        val values = arrayOf(ivTabWorkspace, ivTabCart)
        for (value in values) setValue(value)

        val params = arrayOf(ivTabWorkspace, ivTabCart)
        for (param in params)
            when (param) {
                ivTabWorkspace -> { Parameter.set(param, 64) }
                ivTabCart -> { Parameter.set(param, 64) }
            }

        val clicks = arrayOf(clTabWorkspace, clTabCart)
        for (click in clicks) onClick(click)

        val adapters = arrayOf(rvCompilationBar, rvCartBar, rvActionBar, rvControlBar, rvControlPanel)
        for (adapter in adapters) setAdapter(adapter)
    }

    private fun setValue(view: View) {
        when (view.id) {
            ivTabWorkspace.id -> { ivTabWorkspace.setImageResource(R.drawable.ic_workspace_active) }
            ivTabCart.id -> { ivTabCart.setImageResource(R.drawable.ic_cart_inactive) }
        }
    }

    private fun onClick(view: View) {
        when (view.id) {
            clTabWorkspace.id -> {
                try {
                    view.setOnClickListener {
                        TransitionManager.beginDelayedTransition(clWorkspace)
                        //Parameter.set(ivTabWorkspace, 72)
                        //Parameter.set(ivTabCart, 48)
                        Constraint.set(clTabWorkspace, clTabParent, ivTracker)
                        Constraint.set(clTabWorkspace, clWorkspace, clFragWorkspace)
                        Constraint.set(clTabWorkspace, clWorkspace, clFragControlBar, clFragActionBar)
                        ivTabWorkspace.setImageResource(R.drawable.ic_workspace_active)
                        ivTabCart.setImageResource(R.drawable.ic_cart_inactive)
                    }
                } catch (e: IllegalStateException) {
                    println(e.localizedMessage)
                }
            }
            clTabCart.id -> {
                try {
                    view.setOnClickListener {
                        TransitionManager.beginDelayedTransition(clWorkspace)
                        //Parameter.set(ivTabCart, 72)
                        //Parameter.set(ivTabWorkspace, 48)
                        Constraint.set(clTabCart, clTabParent, ivTracker)
                        Constraint.set(clTabCart, clWorkspace, clFragCart)
                        Constraint.set(clTabCart, clWorkspace, clFragControlBar, clFragActionBar)
                        ivTabWorkspace.setImageResource(R.drawable.ic_workspace_inactive)
                        ivTabCart.setImageResource(R.drawable.ic_cart_active)
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
                Bar.Action.add(this)
                rvActionBar.adapter = Recycler(this, Bar.Action.list, 0)
            }
            rvControlBar.id -> {
                Bar.Control.add(this)
                rvControlBar.adapter = Recycler(this, Bar.Control.list, 1)
            }
            rvControlPanel.id -> {
                Panel.ControlPanel.add(this)
                rvControlPanel.adapter = Recycler(this, Panel.ControlPanel.list, 2)
            }
            rvCompilationBar.id -> {
                Bar.CompilationBar.addEmpty(this)
                if (Bar.CompilationBar.list.size > 0) {
                    rvCompilationBar.adapter = Recycler(this, Bar.CompilationBar.list, 3)
                } else {
                    rvCompilationBar.adapter = Recycler(this, Bar.CompilationBar.empty, 6)
                }
            }
            rvCartBar.id -> {
                Bar.CartBar.addEmpty(this)
                if (Bar.CartBar.list.size > 0) {
                    rvCartBar.adapter = Recycler(this, Bar.CartBar.list, 4)
                } else {
                    rvCartBar.adapter = Recycler(this, Bar.CartBar.empty, 6)
                }
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
