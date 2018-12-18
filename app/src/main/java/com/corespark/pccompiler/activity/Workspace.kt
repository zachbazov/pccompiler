package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.Animation
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.Broadcast
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.service.Window
import com.corespark.pccompiler.utility.ACTIVITY_AUTH
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

        Broadcast.emit(this, channelSignIn)

        Window.determineTabSize(this, ivWorkspaceTrackTracker, windowManager, Window.orientation) {}

        customizeView()

        activateView()
    }

    override fun onDestroy() {
        Broadcast.abort(this, channelSignIn)
        super.onDestroy()
    }

    private fun customizeView() {
        ivWorkspaceTrackWorkspace.setImageResource(R.drawable.img_workspace)
        ivWorkspaceTrackTrolley.setImageResource(R.drawable.img_trolley)
    }

    private fun activateView() {
        onClick(clWorkspaceTrackWorkspace)
        onClick(clWorkspaceTrackTrolley)
        onClick(btnWorkspaceLogout)
    }

    private fun onClick(view: View) {
        when (view.id) {
            clWorkspaceTrackWorkspace.id -> {
                view.setOnClickListener {
                    Animation.animate(this, R.anim.translate_from_x, ivWorkspaceTrackTracker)
                }
            }
            clWorkspaceTrackTrolley.id -> {
                view.setOnClickListener {
                    Animation.animate(this, R.anim.translate_to_x, ivWorkspaceTrackTracker)
                }
            }
            btnWorkspaceLogout.id -> {
                view.setOnClickListener {
                    Auth.logOut(tvWorkspaceUser) {
                        if (it) {
                            startActivity(Intent.launch(this, ACTIVITY_AUTH))
                        }
                    }
                }
            }
        }
    }

    private val channelSignIn = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: android.content.Intent?) {
            if (Compiler.preferences.isLoggedIn) {
                tvWorkspaceUser.text = Compiler.preferences.username
            }
        }
    }
}
