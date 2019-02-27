package com.corespark.pccompiler.activity

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.transition.TransitionManager.beginDelayedTransition
import android.view.View
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.R.layout.activity_workspace
import com.corespark.pccompiler.app.Application.Companion.query
import com.corespark.pccompiler.model.User.email
import com.corespark.pccompiler.model.User.password
import com.corespark.pccompiler.model.User.username
import com.corespark.pccompiler.service.*
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.service.Auth.auth
import com.corespark.pccompiler.service.Auth.signIn
import com.corespark.pccompiler.service.Input.hideKeyboard
import com.corespark.pccompiler.service.Intent.finish
import com.corespark.pccompiler.service.Intent.launch
import com.corespark.pccompiler.service.Layout.fetchLayout
import com.corespark.pccompiler.service.Parameter.layoutParams
import com.corespark.pccompiler.service.View.density
import com.corespark.pccompiler.service.View.heightSpan
import com.corespark.pccompiler.service.View.measure
import com.corespark.pccompiler.service.View.metrics
import com.corespark.pccompiler.service.View.orientation
import com.corespark.pccompiler.service.View.widthSpan
import kotlinx.android.synthetic.main.activity_auth.*
import java.lang.Thread.sleep
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

        auth(this) { if (it) finish() }

        customize()
    }

    private fun customize() {
        ivAuthLogo.setImageResource(R.drawable.ic_logo)

        layoutParams(ivAuthLogo, density, 288)

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

    private fun onClick(view: View) = view.setOnClickListener {
        when (it) {
            tvAuthSignIn -> {
                beginDelayedTransition(clAuthParent)
                Constraint.Auth().constraint(it, clAuthParent)
            }
            tvAuthSignUp -> {
                beginDelayedTransition(clAuthParent)
                Constraint.Auth().constraint(it, clAuthParent)
            }
            btnAuthSignIn -> {
                username = etAuthSignInUsername.text.trim().toString()
                password = etAuthSignInPassword.text.trim().toString()
                signIn(username, password) { complete ->
                    hideKeyboard(this)
                    clearInput(it)
                    if (complete) {
                        beginDelayedTransition(clAuthParent)
                        cvFragAuthSignIn.removeView(clFragAuthSignIn)
                        it.isEnabled = false
                        Task(this, fetchLayout(this, 9)!!, 0).execute()
                    } else Snackbar.make(it, getString(R.string.auth_sign_in_failure), Snackbar.LENGTH_LONG).show()
                }
            }
            else -> {
                username = etAuthSignUpUsername.text.toString()
                email = etAuthSignUpEmail.text.toString()
                password = etAuthSignUpPassword.text.toString()
                Auth.signUp(username, email, password) { complete ->
                    hideKeyboard(this)
                    clearInput(it)
                    if (complete) {
                        beginDelayedTransition(clAuthParent)
                        cvFragAuthSignUp.removeView(clFragAuthSignUp)
                        it.isEnabled = false
                        Task(this, fetchLayout(this, 9)!!, 1).execute()
                    } else Snackbar.make(it, getString(R.string.auth_sign_up_failure), Snackbar.LENGTH_LONG).show()
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

    class Task internal constructor(
        activity: com.corespark.pccompiler.activity.Auth,
        view: View,
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
            widthSpan(weakAuth.get()!!, weakLayout.get()!!, weakAuth.get()!!.windowManager, orientation, 2) {}
            heightSpan(weakAuth.get()!!, weakLayout.get()!!, weakAuth.get()!!.windowManager, orientation, 5) {}
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
                try { sleep(1000) } catch (e: InterruptedException) {}
            }
            return null
        }

        override fun onPostExecute(result: Void?) = when (weakParam.get()) {
            0 -> {
                launch(weakAuth.get()!!, activity_workspace) { if (it) query.fetchCompilations() }
                finish(weakAuth.get()!!)
            }
            else -> {
                beginDelayedTransition(weakAuth.get()!!.clAuthParent)
                Snackbar.make(weakAuth.get()!!.cvFragAuthSignUp, weakAuth.get()!!.getString(R.string.auth_sign_up_success), Snackbar.LENGTH_LONG).show()
                Constraint.Auth().constraint(weakAuth.get()!!.tvAuthSignIn, weakAuth.get()!!.clAuthParent)
                weakAuth.get()!!.cvFragAuthSignUp.removeView(weakLayout.get())
                weakAuth.get()!!.cvFragAuthSignUp.addView(weakAuth.get()!!.clFragAuthSignUp)
                weakAuth.get()!!.btnAuthSignUp.isEnabled = true
            }
        }
    }
}