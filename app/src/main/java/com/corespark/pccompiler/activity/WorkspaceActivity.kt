package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.view.WindowManager
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.impl.TrackController
import com.corespark.pccompiler.service.AuthService
import com.corespark.pccompiler.service.WindowService
import com.corespark.pccompiler.utility.BROADCAST_USER_UPDATE
import com.corespark.pccompiler.utility.Intent
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_workspace.*


class WorkspaceActivity : AppCompatActivity(), TrackController {

    private lateinit var mIntents: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workspace)

        mIntents = Intent(this)

        //Compiler.sharedPreferences.isLoggedIn = false
        if (Compiler.sharedPreferences.isLoggedIn) {
            tvUser.text = Compiler.sharedPreferences.username
            println(Compiler.sharedPreferences.isLoggedIn)
            println(Compiler.sharedPreferences.username)
        }

        determineTabSize(ivWorkspaceTrackTracker, windowManager, WindowService.orientation) {}

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(onUserSignIn, IntentFilter(BROADCAST_USER_UPDATE))

        ivWorkspaceTrackWorkspace.setImageResource(R.drawable.img_workspace)
        ivWorkspaceTrackTrolley.setImageResource(R.drawable.img_trolley)

        clWorkspaceTrackWorkspace.setOnClickListener {
            println(ivWorkspaceTrackTracker.x)
        }

        clWorkspaceTrackTrolley.setOnClickListener {

        }

        btnWorkspaceLogout.setOnClickListener {
            ParseUser.logOutInBackground {
                if (it == null) {
                    tvUser.text = ""
                    Compiler.sharedPreferences.username = ""
                    Compiler.sharedPreferences.isLoggedIn = false
                    startActivity(mIntents.ACTIVITY_AUTH)
                }
            }
        }
    }

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(onUserSignIn)
        super.onDestroy()
    }

    override fun determineTabSize(view: View, manager: WindowManager, orientation: Int?, complete: (Boolean) -> Unit) {
        WindowService.determineLayoutMode(this)
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WindowService.measureValues(manager, WindowService.dm)
            val params = view.layoutParams
            params.width = WindowService.pxToDp(WindowService.widthPx, WindowService.density)
            complete(true)
        } else {
            WindowService.measureValues(manager, WindowService.dm)
            val params = view.layoutParams
            params.width = WindowService.pxToDp(WindowService.widthPx, WindowService.density)
            complete(false)
        }
    }

    private val onUserSignIn = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: android.content.Intent?) {
            if (Compiler.sharedPreferences.isLoggedIn) {
                tvUser.text = Compiler.sharedPreferences.username
                println(Compiler.sharedPreferences.isLoggedIn)
                println(Compiler.sharedPreferences.username)
            }
        }
    }
}
