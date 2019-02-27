package com.corespark.pccompiler.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.corespark.pccompiler.R.layout.*
import com.corespark.pccompiler.activity.Auth
import com.corespark.pccompiler.activity.Compile
import com.corespark.pccompiler.activity.Workspace
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_compile.*
import kotlinx.android.synthetic.main.activity_workspace.*


/**
 * @author Zachy Bazov.
 * @since 25/02/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Layout {

    fun fetchLayout(context: Context, state: Int) : View? {
        val inflater = LayoutInflater.from(context)
        when (state) {
            0 -> return inflater.inflate(item_tab_bar, (context as Workspace).clWorkspaceParent, false)
            1 -> return inflater.inflate(item_action_bar, (context as Workspace).clWorkspaceParent, false)
            2 -> return inflater.inflate(item_control_bar, (context as Workspace).clWorkspaceParent, false)
            3 -> return inflater.inflate(item_control_panel, (context as Workspace).clWorkspaceParent, false)
            4 -> return inflater.inflate(item_compilation_bar, (context as Workspace).clWorkspaceParent, false)
            5 -> return inflater.inflate(item_cart_bar, (context as Workspace).clWorkspaceParent, false)
            6 -> return inflater.inflate(item_component_bar, (context as Compile).clCompileParent, false)
            7 -> return inflater.inflate(item_component, (context as Compile).clCompileParent, false)
            8 -> return inflater.inflate(item_empty, (context as Workspace).clWorkspaceParent, false)
            9 -> return inflater.inflate(dialog_progress, (context as Auth).cvFragAuthSignIn, false)
            10 -> return inflater.inflate(dialog_progress, (context as Workspace).clFragWorkspace, false)
            11 -> return inflater.inflate(dialog_prelim, (context as Workspace).clWorkspaceParent, false)
            12 -> return inflater.inflate(dialog_option, (context as Workspace).clWorkspaceParent, false)
            13 -> return inflater.inflate(dialog_overview, (context as Compile).clCompileParent, false)
        }
        return null
    }
}