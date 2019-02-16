package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.adapter.LayoutManager
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.app.Application.Companion.attributes
import com.corespark.pccompiler.app.Application.Companion.isSignedMessagePresented
import com.corespark.pccompiler.app.Application.Companion.preferences
import com.corespark.pccompiler.model.*
import com.corespark.pccompiler.service.View.orientation
import com.corespark.pccompiler.service.View.width
import com.corespark.pccompiler.service.View.height
import kotlinx.android.synthetic.main.activity_workspace.*
import java.lang.ref.WeakReference


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

        Task(this, layoutInflater.inflate(R.layout.dialog_progress, clFragWorkspace, false)).execute()
    }

    override fun onDestroy() {
        Broadcast.abort(this, onChannelAuth)
        super.onDestroy()
    }

    private fun customize() {
        width(this, ivTracker, windowManager, orientation, 2) {}
        height(this, clFragWorkspace, windowManager, orientation, 5) {}

        clBackward.setBackgroundColor(attributes.colorTransparentGray)
        clForward.setBackgroundColor(attributes.colorTransparentGray)
        ivBackward.setImageDrawable(attributes.arrowIndicator(this))
        ivForward.setImageDrawable(attributes.arrowIndicator(this))

        val adapters = arrayOf(rvTabBar, rvActionBar, rvControlBar, rvCartBar, rvControlPanel)
        adapters.forEach { adapter -> setAdapter(adapter) }
    }

    private fun setAdapter(view: View) = when (view) {
        rvTabBar -> {
            Bar.Tab.add()
            rvTabBar.adapter = Recycler(this, Bar.Tab.list, 0, null)
        }
        rvActionBar -> {
            Bar.Action.add(this)
            rvActionBar.adapter = Recycler(this, Bar.Action.list, 1, null)
        }
        rvControlBar -> {
            Bar.Control.add(this)
            rvControlBar.adapter = Recycler(this, Bar.Control.list, 2, null)
        }
        rvControlPanel -> {
            Panel.ControlPanel.add(this)
            rvControlPanel.adapter = Recycler(this, Panel.ControlPanel.list, 3, null)
        }
        else -> {
            Bar.Cart.addEmpty(this)
            if (Bar.Cart.list.size > 0) rvCartBar.adapter = Recycler(this, Bar.Cart.list, 5, null)
            else rvCartBar.adapter = Recycler(this, Bar.Cart.empty, 8, null)
        }
    }

    private val onChannelAuth = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: android.content.Intent?) {
            if (preferences.isAuthenticated) {
                User.username = preferences.username
                User.password = preferences.password
            }
        }
    }

    class Task internal constructor(
        activity: Workspace,
        view: View,
        private val weakWorkspace: WeakReference<Workspace> = WeakReference(activity),
        private val weakLayout: WeakReference<View> = WeakReference(view)
    ) : AsyncTask<Void, Int, Void>() {

        override fun onPreExecute() {
            if (!isSignedMessagePresented) {
                Snackbar.make(
                    weakWorkspace.get()!!.clWorkspaceParent,
                    "${weakWorkspace.get()!!.getString(R.string.auth_sign_in_success)} ${preferences.username}",
                    Snackbar.LENGTH_LONG)
                    .show()
                isSignedMessagePresented = true
            }
            weakWorkspace.get()!!.clFragWorkspace.addView(weakLayout.get())
            width(weakWorkspace.get()!!, weakLayout.get()!!, weakWorkspace.get()!!.windowManager, orientation, 1) {}
            height(weakWorkspace.get()!!, weakLayout.get()!!, weakWorkspace.get()!!.windowManager, orientation, 5) {}
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val tvProgress = weakLayout.get()!!.findViewById<TextView>(R.id.tvDialogProgress)
            val tvDot = weakLayout.get()!!.findViewById<TextView>(R.id.tvDialogProgressDot)
            tvProgress!!.text = weakWorkspace.get()!!.getString(R.string.text_loading)
            tvDot!!.text = when(values.iterator().next()) {
                0 -> weakWorkspace.get()!!.getString(R.string.text_dot)
                1 -> weakWorkspace.get()!!.getString(R.string.text_dot_double)
                else -> weakWorkspace.get()!!.getString(R.string.text_dot_triple)
            }
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0..2) {
                publishProgress(i)
                try { Thread.sleep(1000) } catch (e: InterruptedException) {}
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            TransitionManager.beginDelayedTransition(weakWorkspace.get()!!.clFragWorkspace)
            weakWorkspace.get()!!.clFragWorkspace.removeView(weakLayout.get())
            if (Bar.Compilation.list.isNotEmpty()) {
                weakWorkspace.get()!!.rvCompilationBar!!.setHasFixedSize(true)
                weakWorkspace.get()!!.rvCompilationBar!!.layoutManager = LayoutManager(
                    weakWorkspace.get()!!, LinearLayoutManager.HORIZONTAL, false)
                weakWorkspace.get()?.rvCompilationBar?.adapter = Recycler(
                    weakWorkspace.get()!!, Bar.Compilation.list, 4, null)
            } else {
                Bar.Compilation.addEmpty(weakWorkspace.get()!!)
                weakWorkspace.get()?.rvCompilationBar?.adapter = Recycler(
                    weakWorkspace.get()!!, Bar.Compilation.empty, 8, null)
            }
        }
    }
}