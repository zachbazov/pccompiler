package com.corespark.pccompiler.model

import java.util.*


/**
 * @author Zachy Bazov.
 * @since 17/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object User {

    lateinit var id: String
    lateinit var username: String
    lateinit var email: String
    lateinit var password: String
    lateinit var createdAt: Date
    lateinit var updatedAt: Date
    var isEmailVerified = false
    var isAuthenticated = false
}