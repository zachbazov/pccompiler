package com.corespark.pccompiler.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
import com.corespark.pccompiler.service.AuthService
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    val mSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        ivAuthLogo.setImageResource(R.drawable.img_logo)
        ivAuthLogoTitle.setImageResource(R.drawable.img_logo_title)

        btnAuthSignIn.text = resources.getText(R.string.auth_sign_in)
        btnAuthSignUp.text = resources.getText(R.string.auth_sign_up)

        onClick(btnAuthSignIn)
        onClick(btnAuthSignUp)
    }

    fun onClick(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                view.setOnClickListener {
                    mSet.clone(clAuthParent)
                    mSet.connect(clAuthDialogSignUp.id, ConstraintSet.TOP, clAuthParent.id, ConstraintSet.BOTTOM)
                    mSet.connect(clAuthDialogSignUp.id, ConstraintSet.LEFT, clAuthParent.id, ConstraintSet.RIGHT)
                    mSet.connect(clAuthDialogSignIn.id, ConstraintSet.TOP, clAuthDialog.id, ConstraintSet.BOTTOM)
                    mSet.applyTo(clAuthParent)
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    setValue(it)
                }
            }
            btnAuthSignUp.id -> {
                view.setOnClickListener {
                    mSet.clone(clAuthParent)
                    mSet.connect(clAuthDialogSignIn.id, ConstraintSet.TOP, clAuthParent.id, ConstraintSet.BOTTOM)
                    mSet.connect(clAuthDialogSignIn.id, ConstraintSet.LEFT, clAuthParent.id, ConstraintSet.RIGHT)
                    mSet.connect(clAuthDialogSignUp.id, ConstraintSet.TOP, clAuthDialog.id, ConstraintSet.BOTTOM)
                    mSet.applyTo(clAuthParent)
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    setValue(it)
                }
            }
            btnAuthDialogSignIn.id -> {
                val username = etAuthDialogUsernameSignUp.text.toString()
                val password = etAuthDialogPasswordSignUp.text.toString()

                AuthService.signIn(username, password) {
                    if (it) {
                        val intent = Intent(this, WorkspaceActivity::class.java)
                        startActivity(intent)
                        println(it)
                    }
                }
            }
        }
    }

    fun setValue(view: View) {
        when (view.id) {
            btnAuthSignIn.id -> {
                etAuthDialogEmailSignIn.hint = getString(R.string.auth_hint_email)
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
}
