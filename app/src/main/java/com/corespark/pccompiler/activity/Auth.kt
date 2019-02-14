package com.corespark.pccompiler.activity

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application
import com.corespark.pccompiler.model.User
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.View.height
import com.corespark.pccompiler.service.View.measure
import com.corespark.pccompiler.service.View.metrics
import com.corespark.pccompiler.service.View.orientation
import com.corespark.pccompiler.service.View.width
import kotlinx.android.synthetic.main.activity_auth.*
import java.lang.ref.WeakReference


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

        measure(windowManager, metrics)

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

    private fun onClick(view: View) = when (view) {
        tvAuthSignIn -> view.setOnClickListener {
            TransitionManager.beginDelayedTransition(clAuthParent)
            Constraint.set(view, clAuthParent) {}
        }
        tvAuthSignUp -> view.setOnClickListener {
            TransitionManager.beginDelayedTransition(clAuthParent)
            Constraint.set(view, clAuthParent) {}
        }
        btnAuthSignIn -> view.setOnClickListener {
            User.username = etAuthSignInUsername.text.trim().toString()
            User.password = etAuthSignInPassword.text.trim().toString()
            Auth.signIn(User.username, User.password) { complete ->
                Input.hideKeyboard(this)
                if (complete)
                    Task(this, layoutInflater.inflate(R.layout.dialog_progress, clAuthParent, false)).execute()
                else {
                    Snackbar.make(view, getString(R.string.auth_incorrect_credentials), Snackbar.LENGTH_SHORT).show()
                    clearInput(view)
                }
            }
        }
        else -> view.setOnClickListener {
            User.username = etAuthSignUpUsername.text.toString()
            User.email = etAuthSignUpEmail.text.toString()
            User.password = etAuthSignUpPassword.text.toString()
            Auth.signUp(User.username, User.email, User.password) { complete ->
                if (complete) {
                    Snackbar.make(view, getString(R.string.auth_sign_up_success), Snackbar.LENGTH_LONG).show()
                    clearInput(view)
                    Input.hideKeyboard(this)
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    Constraint.set(tvAuthSignIn, clAuthParent) {}
                } else {
                    Snackbar.make(view, getString(R.string.auth_sign_up_failure), Snackbar.LENGTH_LONG).show()
                    clearInput(view)
                    Input.hideKeyboard(this)
                }
            }
        }
    }

    private fun clearInput(view: View) = when (view) {
        btnAuthSignIn -> {
            etAuthSignInUsername.text.clear()
            etAuthSignInPassword.text.clear()
        }
        else -> {
            etAuthSignUpUsername.text.clear()
            etAuthSignUpEmail.text.clear()
            etAuthSignUpPassword.text.clear()
        }
    }

    class Task internal constructor(activity: com.corespark.pccompiler.activity.Auth, view: View)
        : AsyncTask<Void, Int, Void>() {

        private val weakAuth: WeakReference<com.corespark.pccompiler.activity.Auth> = WeakReference(activity)
        private val weakLayout: WeakReference<View> = WeakReference(view)

        override fun onPreExecute() {
            weakAuth.get()?.clAuthParent?.addView(weakLayout.get())
            width(weakAuth.get()!!, weakLayout.get()!!, weakAuth.get()!!.windowManager, orientation, 1) {}
            height(weakAuth.get()!!, weakLayout.get()!!, weakAuth.get()!!.windowManager, orientation, 5) {}
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val tv = weakLayout.get()?.findViewById<TextView>(R.id.tvDialogProgress)
            tv!!.text = when(values.iterator().next()) {
                0 -> "Loading."
                1 -> "Loading.."
                2 -> "Loading..."
                else -> "Loading...."
            }
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0..3) {
                publishProgress(i)
                try { Thread.sleep(1000) }
                catch (e: InterruptedException) {}
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            val context = weakAuth.get()!!
            Intent.launch(context, R.layout.activity_workspace) {}
            Intent.finish(context)
            Application.query.fetchCompilations()
        }
    }
}
