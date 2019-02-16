package com.corespark.pccompiler.activity

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.transition.TransitionManager
import android.view.View
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application.Companion.query
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

        btnAuthSignIn.isEnabled = true
        btnAuthSignUp.isEnabled = true

        val clicks = arrayOf(btnAuthSignIn, btnAuthSignUp, tvAuthSignUp, tvAuthSignIn)
        clicks.forEach { click -> onClick(click) }
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
                clearInput(view)
                if (complete) {
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    cvFragAuthSignIn.removeView(clFragAuthSignIn)
                    Task(this, layoutInflater.inflate(R.layout.dialog_progress, cvFragAuthSignIn, false)
                        ,0).execute()
                    view.isEnabled = false
                }
                else Snackbar.make(view, getString(R.string.auth_sign_in_failure), Snackbar.LENGTH_SHORT).show()
            }
        }
        else -> view.setOnClickListener {
            User.username = etAuthSignUpUsername.text.toString()
            User.email = etAuthSignUpEmail.text.toString()
            User.password = etAuthSignUpPassword.text.toString()
            Auth.signUp(User.username, User.email, User.password) { complete ->
                Input.hideKeyboard(this)
                clearInput(view)
                if (complete) {
                    TransitionManager.beginDelayedTransition(clAuthParent)
                    cvFragAuthSignUp.removeView(clFragAuthSignUp)
                    Task(this, layoutInflater.inflate(R.layout.dialog_progress, cvFragAuthSignUp, false)
                        ,1).execute()
                    view.isEnabled = false
                }
                else Snackbar.make(view, getString(R.string.auth_sign_up_failure), Snackbar.LENGTH_LONG).show()
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

    class Task internal constructor(
        activity: com.corespark.pccompiler.activity.Auth, view: View,
        param: Int,
        private val weakAuth: WeakReference<com.corespark.pccompiler.activity.Auth> = WeakReference(activity),
        private val weakLayout: WeakReference<View> = WeakReference(view),
        private val weakParam: WeakReference<Int> = WeakReference(param)
    ) : AsyncTask<Void, Int, Void>() {

        override fun onPreExecute() {
            when (weakParam.get()) {
                0 -> weakAuth.get()!!.cvFragAuthSignIn?.addView(weakLayout.get())
                else -> weakAuth.get()!!.cvFragAuthSignUp?.addView(weakLayout.get())
            }
            width(weakAuth.get()!!, weakLayout.get()!!, weakAuth.get()!!.windowManager, orientation, 2) {}
            height(weakAuth.get()!!, weakLayout.get()!!, weakAuth.get()!!.windowManager, orientation, 5) {}
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val tvProgress = weakLayout.get()!!.findViewById<TextView>(R.id.tvDialogProgress)
            val tvDot = weakLayout.get()!!.findViewById<TextView>(R.id.tvDialogProgressDot)
            tvProgress!!.text = weakAuth.get()!!.getString(R.string.text_loading)
            tvDot!!.text = when(values.iterator().next()) {
                0 -> weakAuth.get()!!.getString(R.string.text_dot)
                1 -> weakAuth.get()!!.getString(R.string.text_dot_double)
                else -> weakAuth.get()!!.getString(R.string.text_dot_triple)
            }
        }

        override fun doInBackground(vararg params: Void?): Void? {
            for (i in 0..2) {
                publishProgress(i)
                try { Thread.sleep(1000) } catch (e: InterruptedException) {}
            }
            return null
        }

        override fun onPostExecute(result: Void?) = when (weakParam.get()) {
            0 -> {
                Intent.launch(weakAuth.get()!!, R.layout.activity_workspace) { if (it) query.fetchCompilations() }
                Intent.finish(weakAuth.get()!!)
            }
            else -> {
                TransitionManager.beginDelayedTransition(weakAuth.get()!!.clAuthParent)
                Snackbar.make(weakAuth.get()!!.cvFragAuthSignUp, weakAuth.get()!!.getString(R.string.auth_sign_up_success),
                    Snackbar.LENGTH_LONG).show()
                Constraint.set(weakAuth.get()!!.tvAuthSignIn, weakAuth.get()!!.clAuthParent) {}
                weakAuth.get()!!.cvFragAuthSignUp.removeView(weakLayout.get())
                weakAuth.get()!!.cvFragAuthSignUp.addView(weakAuth.get()!!.clFragAuthSignUp)
                weakAuth.get()!!.btnAuthSignUp.isEnabled = true
            }
        }
    }
}