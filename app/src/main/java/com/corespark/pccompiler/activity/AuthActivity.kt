package com.corespark.pccompiler.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.TransitionManager
import android.view.View
import com.corespark.pccompiler.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    val set = ConstraintSet()
    lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        ivAuthLogo.setImageResource(R.drawable.img_logo)
        ivAuthLogoTitle.setImageResource(R.drawable.img_logo_title)
        btnSignIn.text = "SIGN IN"
        btnSignUp.text = "SIGN UP"

        var dialogCl : ConstraintLayout
        var boxCl : ConstraintLayout
        var boxClSignIn : ConstraintLayout

        btnSignUp.setOnClickListener {
            constraintLayout = findViewById(R.id.clAuth)
            dialogCl = findViewById(R.id.clAuthDialog)
            boxCl = findViewById(R.id.clAuthBox)
            boxCl.visibility = View.VISIBLE
            set.clone(constraintLayout)
            set.connect(boxCl.id, ConstraintSet.TOP, dialogCl.id, ConstraintSet.BOTTOM)
            set.applyTo(constraintLayout)
            TransitionManager.beginDelayedTransition(constraintLayout)
        }

        btnSignIn.setOnClickListener {
            constraintLayout = findViewById(R.id.clAuth)
            dialogCl = findViewById(R.id.clAuthDialog)
            boxCl = findViewById(R.id.clAuthBox)
            boxClSignIn = findViewById(R.id.clAuthBoxSignIn)
            set.clone(constraintLayout)
            set.clear(boxCl.id)
            set.connect(boxCl.id, ConstraintSet.TOP, dialogCl.id, ConstraintSet.BOTTOM)
            set.connect(boxCl.id, ConstraintSet.LEFT, clAuth.id, ConstraintSet.RIGHT)
            set.connect(boxClSignIn.id, ConstraintSet.TOP, dialogCl.id, ConstraintSet.BOTTOM)
            set.applyTo(constraintLayout)
            TransitionManager.beginDelayedTransition(constraintLayout)
        }
    }
}
