package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.User
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.Constraint
import com.corespark.pccompiler.service.Window
import com.corespark.pccompiler.service.Intent
import kotlinx.android.synthetic.main.activity_auth.*


/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Auth : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Window.measure(windowManager, Window.metrics)

        Auth.auth(this) { if (it) finish() }

        customizeView()
    }

    private fun customizeView() {
        val values = arrayOf(ivAuthLogo, ivAuthLogoTitle, btnSignIn, btnSignUp)
        for (value in values) setValue(value)

        val clicks = arrayOf(btnSignIn, btnSignUp, btnAuthDialogSignIn, btnAuthDialogSignUp)
        for (click in clicks) onClick(click)
    }

    private fun setValue(view: View) {
        when (view.id) {
            ivAuthLogo.id -> {
                ivAuthLogo.setImageResource(R.mipmap.ic_pccompiler)
            }
            ivAuthLogoTitle.id -> {
                ivAuthLogoTitle.setImageResource(R.drawable.ic_pccompiler_title)
            }
            btnSignIn.id -> {
                btnSignIn.text = getString(R.string.auth_sign_in)
                etAuthDialogUsernameSignIn.hint = getString(R.string.auth_hint_username)
                etAuthDialogPasswordSignIn.hint = getString(R.string.auth_hint_password)
                btnAuthDialogSignIn.text = getString(R.string.auth_sign_in)
            }
            btnSignUp.id -> {
                btnSignUp.text = getString(R.string.auth_sign_up)
                etAuthDialogUsernameSignUp.hint = getString(R.string.auth_hint_username)
                etAuthDialogEmailSignUp.hint = getString(R.string.auth_hint_email)
                etAuthDialogPasswordSignUp.hint = getString(R.string.auth_hint_password)
                btnAuthDialogSignUp.text = getString(R.string.auth_sign_up)
            }
        }
    }

    private fun onClick(view: View) {
        when (view.id) {
            btnSignIn.id -> {
                view.setOnClickListener {
                    if (Compiler.preferences.isLoggedIn) {
                        startActivity(Intent.launch(this, R.layout.activity_auth))
                        finish()
                    } else {
                        TransitionManager.beginDelayedTransition(clAuthParent)
                        Constraint.set(view, clAuthParent, clAuthDialog) {}
                    }
                }
            }
            btnSignUp.id -> {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    Constraint.set(view, clAuthParent, clAuthDialog) {}
                }
            }
            btnAuthDialogSignIn.id -> {
                view.setOnClickListener {
                    User.username = etAuthDialogUsernameSignIn.text.toString()
                    User.password = etAuthDialogPasswordSignIn.text.toString()
                    Auth.signIn(User.username, User.password) { complete ->
                        if (complete) {
                            startActivity(Intent.launch(this, R.layout.activity_workspace))
                            finish()
                        } else {
                            Snackbar.make(view, getString(R.string.auth_incorrect_credentials), Snackbar.LENGTH_SHORT).show()
                            clearInput(view)
                        }
                    }
                }
            }
            btnAuthDialogSignUp.id -> {
                view.setOnClickListener {
                    User.username = etAuthDialogUsernameSignUp.text.toString()
                    User.email = etAuthDialogEmailSignUp.text.toString()
                    User.password = etAuthDialogPasswordSignUp.text.toString()
                    Auth.signUp(User.username, User.email, User.password) { complete ->
                        if (complete) {
                            Snackbar.make(view, getString(R.string.auth_sign_up_success), Snackbar.LENGTH_LONG).show()
                            clearInput(view)
                            TransitionManager.beginDelayedTransition(clAuthParent)
                            Constraint.set(btnSignIn, clAuthParent, clAuthDialog) {}
                        } else {
                            Snackbar.make(view, getString(R.string.auth_sign_up_failure), Snackbar.LENGTH_LONG).show()
                            clearInput(view)
                        }
                    }
                }
            }
        }
    }

    private fun clearInput(view: View) {
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
