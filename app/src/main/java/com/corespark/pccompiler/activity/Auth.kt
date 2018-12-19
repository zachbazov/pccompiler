package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.Constraint
import com.corespark.pccompiler.service.Window
import com.corespark.pccompiler.service.Intent
import com.corespark.pccompiler.utility.ACTIVITY_WORKSPACE
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

        activateView()
    }

    private fun customizeView() {
        ivAuthLogo.setImageResource(R.mipmap.ic_pccompiler)
        ivAuthLogoTitle.setImageResource(R.drawable.ic_pccompiler_title)

        btnSignIn.text = resources.getText(R.string.auth_sign_in)
        btnSignUp.text = resources.getText(R.string.auth_sign_up)
    }

    private fun activateView() {
        onClick(btnSignIn)
        onClick(btnSignUp)
        onClick(btnAuthDialogSignIn)
        onClick(btnAuthDialogSignUp)
    }

    private fun onClick(view: View) {
        when (view.id) {
            btnSignIn.id -> {
                view.setOnClickListener {
                    if (Compiler.preferences.isLoggedIn) {
                        startActivity(Intent.launch(this, ACTIVITY_WORKSPACE))
                        finish()
                    } else {
                        Constraint.set(view, clAuthParent, clAuthDialog, clAuthDialogSignUp, clAuthDialogSignIn)
                        setValue(it)
                    }
                }
            }
            btnSignUp.id -> {
                view.setOnClickListener {
                    Constraint.set(view, clAuthParent, clAuthDialog, clAuthDialogSignIn, clAuthDialogSignUp)
                    setValue(it)
                }
            }
            btnAuthDialogSignIn.id -> {
                view.setOnClickListener {
                    val username = etAuthDialogUsernameSignIn.text.toString()
                    val password = etAuthDialogPasswordSignIn.text.toString()
                    Auth.signIn(clAuthParent, username, password) { complete ->
                        if (complete) {
                            startActivity(Intent.launch(this, ACTIVITY_WORKSPACE))
                            finish()
                        } else {
                            clearInput(view)
                        }
                    }
                }
            }
            btnAuthDialogSignUp.id -> {
                view.setOnClickListener {
                    val username = etAuthDialogUsernameSignUp.text.toString()
                    val email = etAuthDialogEmailSignUp.text.toString()
                    val password = etAuthDialogPasswordSignUp.text.toString()
                    Auth.signUp(clAuthParent, username, email, password) { complete ->
                        if (complete) {
                            clearInput(view)
                            Constraint.set(btnSignIn, clAuthParent, clAuthDialog, clAuthDialogSignUp, clAuthDialogSignIn)
                            setValue(btnSignIn)
                        } else {
                            clearInput(view)
                        }
                    }
                }
            }
        }
    }

    private fun setValue(view: View) {
        when (view.id) {
            btnSignIn.id -> {
                etAuthDialogUsernameSignIn.hint = getString(R.string.auth_hint_username)
                etAuthDialogPasswordSignIn.hint = getString(R.string.auth_hint_password)
                btnAuthDialogSignIn.text = getString(R.string.auth_sign_in)
            }
            btnSignUp.id -> {
                etAuthDialogUsernameSignUp.hint = getString(R.string.auth_hint_username)
                etAuthDialogEmailSignUp.hint = getString(R.string.auth_hint_email)
                etAuthDialogPasswordSignUp.hint = getString(R.string.auth_hint_password)
                btnAuthDialogSignUp.text = getString(R.string.auth_sign_up)
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
