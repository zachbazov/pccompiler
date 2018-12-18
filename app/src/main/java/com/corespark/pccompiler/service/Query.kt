package com.corespark.pccompiler.service

import android.content.Context
import com.corespark.pccompiler.app.Compiler
import com.corespark.pccompiler.utility.ACTIVITY_WORKSPACE
import com.parse.ParseObject
import com.parse.ParseQuery


/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Query<T : ParseObject?> {

    var mQuery: ParseQuery<T>? = null

    fun getCurrentUser(context: Context, complete: (Boolean) -> Unit) {
        mQuery = ParseQuery.getQuery("_User")
        mQuery?.findInBackground { objects, e ->
            if (e == null) {
                for (o in objects) {
                    if (Compiler.preferences.username.equals(o?.get("username"))) {
                        context.startActivity(Intent.launch(context, ACTIVITY_WORKSPACE))
                        complete(true)
                    }
                }
            }
        }
    }
}