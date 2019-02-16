package com.corespark.pccompiler.service

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.corespark.pccompiler.impl.Input


/**
 * @author Zachy Bazov.
 * @since 25/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Input : Input {

    override fun hideKeyboard(context: Context) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        when { inputManager.isActive ->
            inputManager.hideSoftInputFromWindow((context as Activity).currentFocus?.applicationWindowToken, 0)
        }
    }
}