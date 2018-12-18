package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.service.Auth
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

    private val set = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Window.measure(windowManager, Window.metrics)

        Auth.auth(this) { if (it) finish() }

        customizeView()

        activateView()
    }

    private fun customizeView() {
        ivAuthLogo.setImageResource(R.drawable.img_logo)
        ivAuthLogoTitle.setImageResource(R.drawable.img_logo_title)

        btnAuthSignIn.text = resources.getText(R.string.auth_sign_in)
        btnAuthSignUp.text = resources.getText(R.string.auth_sign_up)
    }

    private fun activateView() {
        onClick(btnAuthSignIn)
        onClick(btnAuthSignUp)
        onClick(btnAuthDialogSignIn)
        onClick(btnAuthDialogSignUp)
    }

    private fun onClick(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                view.setOnClickListener {
                    if (Compiler.preferences.isLoggedIn) {
                        startActivity(Intent.launch(this, ACTIVITY_WORKSPACE))
                        finish()
                    } else {
                        setConstraint(it)
                        setValue(it)
                    }
                }
            }
            btnAuthSignUp.id -> {
                view.setOnClickListener {
                    setConstraint(it)
                    setValue(it)
                }
            }
            btnAuthDialogSignIn.id -> {
                view.setOnClickListener {
                    val username = etAuthDialogUsernameSignIn.text.toString()
                    val password = etAuthDialogPasswordSignIn.text.toString()
                    Auth.signIn(clAuthParent, username, password) {
                        if (it) {
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
                    Auth.signUp(clAuthParent, username, email, password) {
                        if (it) {
                            clearInput(view)
                            setConstraint(btnAuthSignIn)
                            setValue(btnAuthSignIn)
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

    private fun setConstraint(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                set.clone(clAuthParent)
                set.connect(clAuthDialogSignUp.id, ConstraintSet.TOP, clAuthParent.id, ConstraintSet.BOTTOM)
                set.connect(clAuthDialogSignUp.id, ConstraintSet.LEFT, clAuthParent.id, ConstraintSet.RIGHT)
                set.connect(clAuthDialogSignIn.id, ConstraintSet.TOP, clAuthDialog.id, ConstraintSet.BOTTOM)
                set.applyTo(clAuthParent)

            }
            btnAuthSignUp.id -> {
                set.clone(clAuthParent)
                set.connect(clAuthDialogSignIn.id, ConstraintSet.TOP, clAuthParent.id, ConstraintSet.BOTTOM)
                set.connect(clAuthDialogSignIn.id, ConstraintSet.LEFT, clAuthParent.id, ConstraintSet.RIGHT)
                set.connect(clAuthDialogSignUp.id, ConstraintSet.TOP, clAuthDialog.id, ConstraintSet.BOTTOM)
                set.applyTo(clAuthParent)
            }
        }
        TransitionManager.beginDelayedTransition(clAuthParent)
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
