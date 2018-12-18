package com.corespark.pccompiler.service

import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.TextView
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.User
import com.corespark.pccompiler.utility.*
import com.parse.ParseUser


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Auth {

    var user = User

    fun signIn(view: View, username: String, password: String, complete: (Boolean) -> Unit) {
        ParseUser.logInInBackground(username, password) { parseUser, e ->
            if (parseUser != null) {
                user.id = parseUser.getObjectId()
                user.username = parseUser.username
                user.email = parseUser.getEmail()
                user.password = password
                user.createdAt = parseUser.getCreatedAt()
                user.updatedAt = parseUser.getUpdatedAt()
                user.isEmailVerified = parseUser.isNew()
                user.isAuthenticated = parseUser.isAuthenticated()
                user.isAuthenticated = true
                Compiler.preferences.username = user.username
                Compiler.preferences.isLoggedIn = true
                complete(true)
            } else {
                ParseUser.logOut()
                Snackbar.make(view, MESSAGE_INCORRECT_CREDENTIALS, Snackbar.LENGTH_SHORT).show()
                complete(false)
            }
        }
    }

    fun signUp(view: View, username: String, email: String, password: String, complete: (Boolean) -> Unit) {
        val user = ParseUser()
        user.username = username
        user.email = email
        user.setPassword(password)
        user.signUpInBackground {
            if (it == null) {
                Snackbar.make(view, MESSAGE_USER_CREATION_SUCCESS, Snackbar.LENGTH_LONG).show()
                complete(true)
            } else {
                Snackbar.make(view, MESSAGE_USER_CREATION_FAILURE, Snackbar.LENGTH_LONG).show()
                complete(false)
            }
        }
    }

    fun auth(context: Context, complete: (Boolean) -> Unit) {
        if (Compiler.preferences.isLoggedIn) {
            context.startActivity(Intent.launch(context, ACTIVITY_WORKSPACE))
            complete(true)
        }
    }

    fun logOut(user: TextView, complete: (Boolean) -> Unit) {
        ParseUser.logOutInBackground {
            if (it == null) {
                user.text = BLANK
                Compiler.preferences.username = BLANK
                Compiler.preferences.isLoggedIn = false
                Auth.user = User()
                complete(true)
            }
        }
    }
}