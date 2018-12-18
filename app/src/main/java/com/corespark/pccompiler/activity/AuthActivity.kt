package com.corespark.pccompiler.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.content.LocalBroadcastManager
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.AuthService
import com.corespark.pccompiler.service.WindowService
import com.corespark.pccompiler.utility.BROADCAST_USER_UPDATE
import com.corespark.pccompiler.utility.Intent
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_workspace.*


class AuthActivity : AppCompatActivity() {

    private lateinit var mIntents: Intent

    private val mSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        WindowService.measureValues(windowManager, WindowService.dm)

        mIntents = Intent(this)

        if (Compiler.sharedPreferences.isLoggedIn && Compiler.sharedPreferences.username != "") {
            println(Compiler.sharedPreferences.username)
            println(Compiler.sharedPreferences.isLoggedIn)
            startActivity(mIntents.ACTIVITY_WORKSPACE)
        }

        ivAuthLogo.setImageResource(R.drawable.img_logo)
        ivAuthLogoTitle.setImageResource(R.drawable.img_logo_title)

        btnAuthSignIn.text = resources.getText(R.string.auth_sign_in)
        btnAuthSignUp.text = resources.getText(R.string.auth_sign_up)

        onClick(btnAuthSignIn)
        onClick(btnAuthSignUp)
        onClick(btnAuthDialogSignIn)
        onClick(btnAuthDialogSignUp)
    }

    private fun onClick(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                view.setOnClickListener {
                    setConstraints(it)
                    setValue(it)
                }
            }
            btnAuthSignUp.id -> {
                view.setOnClickListener {
                    setConstraints(it)
                    setValue(it)
                }
            }
            btnAuthDialogSignIn.id -> {
                view.setOnClickListener {
                    val username = etAuthDialogUsernameSignIn.text.toString()
                    val password = etAuthDialogPasswordSignIn.text.toString()
                    AuthService.signIn(clAuthParent, username, password) {
                        if (it) {
                            val userUpdate = android.content.Intent(BROADCAST_USER_UPDATE)
                            LocalBroadcastManager.getInstance(this).sendBroadcast(userUpdate)
                            startActivity(mIntents.ACTIVITY_WORKSPACE)
                        } else {
                            clearInputs(view)
                        }
                    }
                }
            }
            btnAuthDialogSignUp.id -> {
                view.setOnClickListener {
                    val username = etAuthDialogUsernameSignUp.text.toString()
                    val email = etAuthDialogEmailSignUp.text.toString()
                    val password = etAuthDialogPasswordSignUp.text.toString()
                    AuthService.signUp(clAuthParent, username, email, password) {
                        if (it) {
                            clearInputs(view)
                            setConstraints(btnAuthSignIn)
                            setValue(btnAuthSignIn)
                        } else {
                            clearInputs(view)
                        }
                    }
                }
            }
        }
    }

    private fun setValue(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                etAuthDialogUsernameSignIn.hint = getString(R.string.auth_hint_username)
                etAuthDialogPasswordSignIn.hint = getString(R.string.auth_hint_password)
                btnAuthDialogSignIn.text = getString(R.string.auth_sign_in)
            }
            btnAuthSignUp.id -> {
                etAuthDialogUsernameSignUp.hint = getString(R.string.auth_hint_username)
                etAuthDialogEmailSignUp.hint = getString(R.string.auth_hint_email)
                etAuthDialogPasswordSignUp.hint = getString(R.string.auth_hint_password)
                btnAuthDialogSignUp.text = getString(R.string.auth_sign_up)
            }
        }
    }

    private fun setConstraints(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                mSet.clone(clAuthParent)
                mSet.connect(clAuthDialogSignUp.id, ConstraintSet.TOP, clAuthParent.id, ConstraintSet.BOTTOM)
                mSet.connect(clAuthDialogSignUp.id, ConstraintSet.LEFT, clAuthParent.id, ConstraintSet.RIGHT)
                mSet.connect(clAuthDialogSignIn.id, ConstraintSet.TOP, clAuthDialog.id, ConstraintSet.BOTTOM)
                mSet.applyTo(clAuthParent)

            }
            btnAuthSignUp.id -> {
                mSet.clone(clAuthParent)
                mSet.connect(clAuthDialogSignIn.id, ConstraintSet.TOP, clAuthParent.id, ConstraintSet.BOTTOM)
                mSet.connect(clAuthDialogSignIn.id, ConstraintSet.LEFT, clAuthParent.id, ConstraintSet.RIGHT)
                mSet.connect(clAuthDialogSignUp.id, ConstraintSet.TOP, clAuthDialog.id, ConstraintSet.BOTTOM)
                mSet.applyTo(clAuthParent)
            }
        }
        TransitionManager.beginDelayedTransition(clAuthParent)
    }

    private fun clearInputs(view: View) {
        when (view.id) {
            btnAuthDialogSignIn.id -> {
                etAuthDialogUsernameSignIn.text.clear()
                etAuthDialogPasswordSignIn.text.clear()
            }
            btnAuthDialogSignUp.id -> {
                etAuthDialogUsernameSignUp.text.clear()
                etAuthDialogEmailSignUp.text.clear()
                etAuthDialogPasswordSignUp.text.clear()
            }
        }
    }


}
