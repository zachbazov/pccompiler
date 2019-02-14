package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.adapter.LayoutManager
import com.corespark.pccompiler.app.Application
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.adapter.Recycler
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

        clBackward.setBackgroundColor(Application.attributes.colorTransparentGray)
        clForward.setBackgroundColor(Application.attributes.colorTransparentGray)
        ivBackward.setImageDrawable(Application.attributes.arrowIndicator(this))
        ivForward.setImageDrawable(Application.attributes.arrowIndicator(this))

        val adapters = arrayOf(rvTabBar, rvActionBar, rvControlBar, rvCartBar, rvControlPanel)
        for (adapter in adapters) setAdapter(adapter)
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
            if (Application.preferences.isAuthenticated) {
                User.username = Application.preferences.username
                User.password = Application.preferences.password
            }
        }
    }

    class Task internal constructor(activity: Workspace, view: View) : AsyncTask<Void, Int, Void>() {

        private val weakWorkspace: WeakReference<Workspace> = WeakReference(activity)
        private val weakLayout: WeakReference<View> = WeakReference(view)

        override fun onPreExecute() {
            weakWorkspace.get()?.clFragWorkspace?.addView(weakLayout.get())
            width(weakWorkspace.get()!!, weakLayout.get()!!, weakWorkspace.get()!!.windowManager, orientation, 1) {}
            height(weakWorkspace.get()!!, weakLayout.get()!!, weakWorkspace.get()!!.windowManager, orientation, 5) {}
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val tv = weakLayout.get()?.findViewById<TextView>(R.id.tvDialogProgress)
            tv!!.text = when(values.iterator().next()) {
                0 -> "Loading."
                1 -> "Loading.."
                2 -> "Loading..."
                else -> "Loading...."
            }
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0..3) {
                publishProgress(i)
                try { Thread.sleep(1000) }
                catch (e: InterruptedException) {}
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            val context = weakWorkspace.get()!!
            TransitionManager.beginDelayedTransition(weakWorkspace.get()!!.clFragWorkspace)
            weakWorkspace.get()?.clFragWorkspace?.removeView(weakLayout.get())
            if (Bar.Compilation.list.isNotEmpty()) {
                weakWorkspace.get()?.rvCompilationBar?.setHasFixedSize(true)
                weakWorkspace.get()?.rvCompilationBar?.layoutManager = LayoutManager(
                    context, LinearLayoutManager.HORIZONTAL, false)
                weakWorkspace.get()?.rvCompilationBar?.adapter =
                    Recycler(context, Bar.Compilation.list, 4, null)
            } else {
                Bar.Compilation.addEmpty(context)
                weakWorkspace.get()?.rvCompilationBar?.adapter =
                    Recycler(context, Bar.Compilation.empty, 8, null)
            }
        }
    }
}
