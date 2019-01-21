package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
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

        customize()
    }

    private fun customize() {
        ivAuthLogo.setImageResource(R.mipmap.ic_pccompiler)
        ivAuthLogoTitle.setImageResource(R.drawable.ic_pccompiler_title)

        etAuthSignInUsername.hint = getString(R.string.auth_hint_username)
        etAuthSignInPassword.hint = getString(R.string.auth_hint_password)
        btnAuthSignIn.text = getString(R.string.auth_sign_in)
        tvAuthSignUp.text = getString(R.string.auth_sign_up_ref)

        etAuthSignUpUsername.hint = getString(R.string.auth_hint_username)
        etAuthSignUpEmail.hint = getString(R.string.auth_hint_email)
        etAuthSignUpPassword.hint = getString(R.string.auth_hint_password)
        btnAuthSignUp.text = getString(R.string.auth_sign_up)
        tvAuthSignIn.text = getString(R.string.auth_sign_in_ref)

        val clicks = arrayOf(btnAuthSignIn, btnAuthSignUp, tvAuthSignUp, tvAuthSignIn)
        for (click in clicks) onClick(click)
    }

    private fun onClick(view: View) {
        when (view.id) {
            tvAuthSignIn.id -> {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    Constraint.set(view, clAuthParent) {}
                }
            }
            tvAuthSignUp.id -> {
                view.setOnClickListener {
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    Constraint.set(view, clAuthParent) {}
                }
            }
            btnAuthSignIn.id -> {
                view.setOnClickListener {
                    User.username = etAuthSignInUsername.text.toString()
                    User.password = etAuthSignInPassword.text.toString()
                    Auth.signIn(User.username, User.password) { complete ->
                        if (complete) {
                            Intent.launch(this, R.layout.activity_workspace) {}
                            Intent.finish(this)
                        } else {
                            Snackbar.make(view, getString(R.string.auth_incorrect_credentials), Snackbar.LENGTH_SHORT).show()
                            clearInput(view)
                            Window.hideKeyboard(this)
                        }
                    }
                }
            }
            btnAuthSignUp.id -> {
                view.setOnClickListener {
                    User.username = etAuthSignUpUsername.text.toString()
                    User.email = etAuthSignUpEmail.text.toString()
                    User.password = etAuthSignUpPassword.text.toString()
                    Auth.signUp(User.username, User.email, User.password) { complete ->
                        if (complete) {
                            Snackbar.make(view, getString(R.string.auth_sign_up_success), Snackbar.LENGTH_LONG).show()
                            clearInput(view)
                            Window.hideKeyboard(this)
                            TransitionManager.beginDelayedTransition(clAuthParent)
                            Constraint.set(tvAuthSignIn, clAuthParent) {}
                        } else {
                            Snackbar.make(view, getString(R.string.auth_sign_up_failure), Snackbar.LENGTH_LONG).show()
                            clearInput(view)
                            Window.hideKeyboard(this)
                        }
                    }
                }
            }
        }
    }

    private fun clearInput(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                etAuthSignInUsername.text.clear()
                etAuthSignInPassword.text.clear()
            }
            btnAuthSignUp.id -> {
                etAuthSignUpUsername.text.clear()
                etAuthSignUpEmail.text.clear()
                etAuthSignUpPassword.text.clear()
            }
        }
    }
}
