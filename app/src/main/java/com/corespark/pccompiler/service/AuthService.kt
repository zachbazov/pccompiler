package com.corespark.pccompiler.service

import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.model.User
import com.corespark.pccompiler.utility.BROADCAST_USER_UPDATE
import com.parse.ParseUser


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object AuthService {

    lateinit var mAuthUser: ParseUser

    fun signIn(view: View, username: String, password: String, complete: (Boolean) -> Unit) {
        ParseUser.logInInBackground(username, password) { parseUser, e ->
            if (parseUser != null) {
                mAuthUser = parseUser
                val mUser = User
                mUser.id = parseUser.getObjectId()
                mUser.username = parseUser.username
                mUser.email = parseUser.getEmail()
                mUser.password = password
                mUser.createdAt = parseUser.getCreatedAt()
                mUser.updatedAt = parseUser.getUpdatedAt()
                mUser.isEmailVerified = parseUser.isNew()
                mUser.isAuthenticated = parseUser.isAuthenticated()
                mUser.isAuthenticated = true
                Compiler.sharedPreferences.isLoggedIn = true
                Compiler.sharedPreferences.username = mUser.username
                complete(true)
            } else {
                ParseUser.logOut()
                Snackbar.make(view, "Incorrect credentials", Snackbar.LENGTH_SHORT).show()
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
                Snackbar.make(view, "SUCCESS", Snackbar.LENGTH_SHORT).show()
                complete(true)
            } else {
                Snackbar.make(view, "FAILED", Snackbar.LENGTH_SHORT).show()
                complete(false)
            }
        }
    }
}