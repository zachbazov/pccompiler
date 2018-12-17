package com.corespark.pccompiler.service

import android.content.Intent
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

    fun signIn(username: String, password: String, complete: (Boolean) -> Unit) {
        ParseUser.logInInBackground(username, password) { parseUser, e ->
            if (parseUser != null) {
                mAuthUser = parseUser

                //fetchUserCompilationById()

                mUser.id = parseUser.getObjectId()
                mUser.email = parseUser.getEmail()
                mUser.createdAt = parseUser.getCreatedAt()
                mUser.updatedAt = parseUser.getUpdatedAt()
                mUser.isEmailVerified = parseUser.isNew()
                mUser.isAuthenticated = parseUser.isAuthenticated()

                mUser.isAuthenticated = true
                println("success")
            } else {
                ParseUser.logOut()
                println("failed")
            }
        }
    }
}