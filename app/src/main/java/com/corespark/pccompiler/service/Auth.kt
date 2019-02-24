package com.corespark.pccompiler.service

import android.content.Context
import com.corespark.pccompiler.R
import com.corespark.pccompiler.app.Application.Companion.preferences
import com.corespark.pccompiler.app.Application.Companion.query
import com.corespark.pccompiler.model.User
import com.parse.ParseException
import com.parse.ParseUser


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Auth {

    private var user = User
    var parseUser: ParseUser? = null

    fun signIn(username: String, password: String, complete: (Boolean) -> Unit) {
        ParseUser.logInInBackground(username, password) { parseUser, _ ->
            if (parseUser != null) {
                user.id = parseUser.objectId
                user.username = parseUser.username
                user.email = parseUser.email
                user.password = password
                user.createdAt = parseUser.createdAt
                user.updatedAt = parseUser.updatedAt
                user.isEmailVerified = parseUser.isNew
                user.isAuthenticated = parseUser.isAuthenticated
                this.parseUser = parseUser
                preferences.username = user.username
                preferences.password = user.password
                preferences.isAuthenticated = true
                complete(true)
            } else {
                ParseUser.logOut()
                complete(false)
            }
        }
    }

    fun signUp(username: String, email: String, password: String, complete: (Boolean) -> Unit) {
        val user = ParseUser()
        user.username = username
        user.email = email
        user.setPassword(password)
        user.signUpInBackground {
            if (it == null) complete(true)
            else complete(false)
        }
    }

    private fun verify() = ParseUser.logInInBackground(preferences.username, preferences.password) { parseUser, _ ->
        try {
            if (parseUser != null) {
                this.parseUser = parseUser
                query.fetchCompilations()
            } else ParseUser.logOut()
        } catch (ex: ParseException) {}
    }

    fun auth(context: Context, complete: (Boolean) -> Unit) {
        if (preferences.isAuthenticated) {
            verify()
            Intent.launch(context, R.layout.activity_workspace) {}
            complete(true)
        }
    }

    fun logOut(context: Context, complete: (Boolean) -> Unit) {
        ParseUser.logOutInBackground {
            if (it == null) {
                preferences.username = context.getString(R.string.text_blank)
                preferences.isAuthenticated = false
                Auth.user = User
                complete(true)
            }
        }
    }
}