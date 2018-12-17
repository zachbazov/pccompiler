package com.corespark.pccompiler.service

import android.content.Intent
import android.support.design.widget.Snackbar
import android.view.View
import com.corespark.pccompiler.activity.AuthActivity
import com.corespark.pccompiler.model.User
import com.parse.LogInCallback
import com.parse.ParseException
import com.parse.ParseUser


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object AuthService {

    lateinit var mUser: User
    lateinit var mAuthUser: ParseUser

    fun signIn(view: View, username: String, password: String, complete: (Boolean) -> Unit) {
        ParseUser.logInInBackground(username, password) { parseUser, e ->
            if (parseUser != null) {
                mAuthUser = parseUser
                //fetchUserCompilationById()
                mUser = User
                mUser.id = parseUser.getObjectId()
                mUser.email = parseUser.getEmail()
                mUser.createdAt = parseUser.getCreatedAt()
                mUser.updatedAt = parseUser.getUpdatedAt()
                mUser.isEmailVerified = parseUser.isNew()
                mUser.isAuthenticated = parseUser.isAuthenticated()
                mUser.isAuthenticated = true
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