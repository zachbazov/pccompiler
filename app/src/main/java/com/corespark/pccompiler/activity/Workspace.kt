package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.adapter.LayoutManager
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.model.*
import com.corespark.pccompiler.service.View.orientation
import com.corespark.pccompiler.service.View.span
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

        Broadcast.emit(this, onChannelAuth)

        customize()
    }

    override fun onDestroy() {
        Broadcast.abort(this, onChannelAuth)
        super.onDestroy()
    }

    private fun customize() {
        span(this, ivTracker, windowManager, orientation, 2) {}

        clBackward.setBackgroundColor(Compiler.colors.colorTransparentGray)
        clForward.setBackgroundColor(Compiler.colors.colorTransparentGray)
        ivBackward.setImageDrawable(Compiler.attributes.homeIndicator(this))
        ivForward.setImageDrawable(Compiler.attributes.homeIndicator(this))

        val adapters = arrayOf(rvTabBar, rvActionBar, rvControlBar, rvCompilationBar, rvCartBar, rvControlPanel)
        for (adapter in adapters) setAdapter(adapter)
    }

    private fun setAdapter(view: View) {
        when (view.id) {
            rvTabBar.id -> {
                Bar.Tab.add()
                rvTabBar.adapter = Recycler(this, Bar.Tab.list, 0, null)
            }
            rvActionBar.id -> {
                Bar.Action.add(this)
                rvActionBar.adapter = Recycler(this, Bar.Action.list, 1, null)
            }
            rvControlBar.id -> {
                Bar.Control.add(this)
                rvControlBar.adapter = Recycler(this, Bar.Control.list, 2, null)
            }
            rvControlPanel.id -> {
                Panel.ControlPanel.add(this)
                rvControlPanel.adapter = Recycler(this, Panel.ControlPanel.list, 3, null)
            }
            rvCompilationBar.id -> {
                Handler().postDelayed({
                    if (Bar.Compilation.list.isNotEmpty()) {
                        rvCompilationBar.setHasFixedSize(true)
                        rvCompilationBar.layoutManager = LayoutManager(
                            this, LinearLayoutManager.HORIZONTAL, false)
                        rvCompilationBar.adapter = Recycler(this, Bar.Compilation.list, 4, null)
                    }
                    else {
                        Bar.Compilation.addEmpty(this)
                        rvCompilationBar.adapter = Recycler(this, Bar.Compilation.empty, 8, null)
                    }
                }, 3000)
            }
            else -> {
                Bar.Cart.addEmpty(this)
                if (Bar.Cart.list.size > 0)
                    rvCartBar.adapter = Recycler(this, Bar.Cart.list, 5, null)
                else rvCartBar.adapter = Recycler(this, Bar.Cart.empty, 8, null)
            }
        }
    }

    private val onChannelAuth = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: android.content.Intent?) {
            if (Compiler.preferences.isAuthenticated) {
                User.username = Compiler.preferences.username
                User.password = Compiler.preferences.password
            }
        }
    }
}
