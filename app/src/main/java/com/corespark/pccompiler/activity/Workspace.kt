package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.transition.TransitionManager.beginDelayedTransition
import android.view.View
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.adapter.LayoutManager
import com.corespark.pccompiler.adapter.Recycler
import com.corespark.pccompiler.app.Application.Companion.attributes
import com.corespark.pccompiler.app.Application.Companion.isSignedMessagePresented
import com.corespark.pccompiler.app.Application.Companion.preferences
import com.corespark.pccompiler.model.*
import com.corespark.pccompiler.model.Compilation.isCompilationFetched
import com.corespark.pccompiler.model.User.password
import com.corespark.pccompiler.model.User.username
import com.corespark.pccompiler.service.Broadcast.abort
import com.corespark.pccompiler.service.Broadcast.emit
import com.corespark.pccompiler.service.Layout.fetchLayout
import com.corespark.pccompiler.service.Parameter.decreaseHeightBy
import com.corespark.pccompiler.service.Parameter.margin
import com.corespark.pccompiler.service.View.orientation
import com.corespark.pccompiler.service.View.widthSpan
import com.corespark.pccompiler.service.View.heightSpan
import com.corespark.pccompiler.service.View.density
import com.corespark.pccompiler.service.View.spannedHeightPx
import kotlinx.android.synthetic.main.activity_workspace.*
import java.lang.Thread.sleep
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

        emit(this, onChannelAuth)

        customize()

//        if (!isCompilationFetched) Task(this, fetchLayout(this, 10)!!).execute()
//        else {
//            clBackward.visibility = View.VISIBLE
//            clForward.visibility = View.VISIBLE
//            rvCompilationBar.setHasFixedSize(true)
//            rvCompilationBar.layoutManager = LayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//            rvCompilationBar.adapter = Recycler(this, Bar.Compilation.list, 4, null)
//        }
    }

    override fun onDestroy() {
        abort(this, onChannelAuth)
        super.onDestroy()
    }

    private fun customize() {
        widthSpan(this, ivTracker, windowManager, orientation, 2) {}
        heightSpan(this, clFragWorkspace, windowManager, orientation, 5) {}

        margin(cvFragActionBar, density, 3, 1)

        clBackward.visibility = View.GONE
        clForward.visibility = View.GONE
        decreaseHeightBy(clBackward, spannedHeightPx, 8)
        decreaseHeightBy(clForward, spannedHeightPx, 8)
        clBackward.setBackgroundColor(attributes.colorTransparentGray)
        clForward.setBackgroundColor(attributes.colorTransparentGray)
        ivBackward.setImageDrawable(attributes.arrowIndicator(this))
        ivForward.setImageDrawable(attributes.arrowIndicator(this))

        val adapters = arrayOf(rvTabBar, rvActionBar, rvControlBar, rvControlPanel, rvCartBar)
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
            if (Bar.Cart.list.isNotEmpty()) rvCartBar.adapter = Recycler(this, Bar.Cart.list, 5, null)
            else rvCartBar.adapter = Recycler(this, Bar.Cart.empty, 8, null)
        }
    }

    private val onChannelAuth = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: android.content.Intent?) {
            if (preferences.isAuthenticated) {
                username = preferences.username
                password = preferences.password
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
                Snackbar.make(weakWorkspace.get()!!.clWorkspaceParent, "${weakWorkspace.get()!!.getString(R.string.auth_sign_in_success)} ${preferences.username}",
                    Snackbar.LENGTH_LONG).show()
                isSignedMessagePresented = true
            }
            weakWorkspace.get()!!.clFragWorkspace.addView(weakLayout.get())
            widthSpan(weakWorkspace.get()!!, weakLayout.get()!!, weakWorkspace.get()!!.windowManager, orientation, 1) {}
            heightSpan(weakWorkspace.get()!!, weakLayout.get()!!, weakWorkspace.get()!!.windowManager, orientation, 5) {}
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val tvProgress = weakLayout.get()!!.findViewById<TextView>(R.id.tvDialogProgress)
            val tvDot = weakLayout.get()!!.findViewById<TextView>(R.id.tvDialogProgressDot)
            tvProgress!!.text = weakWorkspace.get()!!.getString(R.string.text_loading)
            tvDot!!.text = when(values.iterator().next()) {
                0 -> weakWorkspace.get()!!.getString(R.string.text_dot)
                1,2 -> weakWorkspace.get()!!.getString(R.string.text_dot_double)
                else -> weakWorkspace.get()!!.getString(R.string.text_dot_triple)
            }
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0..3) {
                publishProgress(i)
                try { sleep(1000) } catch (e: InterruptedException) {}
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            beginDelayedTransition(weakWorkspace.get()!!.clFragWorkspace)
            weakWorkspace.get()!!.clFragWorkspace.removeView(weakLayout.get())
            if (Bar.Compilation.list.isNotEmpty()) {
                weakWorkspace.get()!!.rvCompilationBar!!.setHasFixedSize(true)
                weakWorkspace.get()!!.rvCompilationBar!!.layoutManager = LayoutManager(
                    weakWorkspace.get()!!, LinearLayoutManager.HORIZONTAL, false)
                weakWorkspace.get()!!.rvCompilationBar!!.adapter = Recycler(
                    weakWorkspace.get()!!, Bar.Compilation.list, 4, null)
                weakWorkspace.get()!!.clBackward.visibility = View.VISIBLE
                weakWorkspace.get()!!.clForward.visibility = View.VISIBLE
                isCompilationFetched = true
            } else {
                Bar.Compilation.addEmpty(weakWorkspace.get()!!)
                weakWorkspace.get()!!.rvCompilationBar!!.adapter = Recycler(
                    weakWorkspace.get()!!, Bar.Compilation.empty, 8, null)
                weakWorkspace.get()!!.clBackward.visibility = View.GONE
                weakWorkspace.get()!!.clForward.visibility = View.GONE
                isCompilationFetched = false
            }
        }
    }
}