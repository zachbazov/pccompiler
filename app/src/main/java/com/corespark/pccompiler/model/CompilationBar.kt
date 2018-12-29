package com.corespark.pccompiler.model

import android.content.Context
import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 20/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class CompilationBar(val id: Int, val title: String, val image: Int) {

    companion object {
        val list = mutableListOf<CompilationBar>()
        val empty = mutableListOf<EmptyBar>()

        fun add() {
            if (list.size < 1) {
                list.add(0, CompilationBar(0, "PCCompiler", R.mipmap.ic_pccompiler))
            }
        }

        fun addEmpty(context: Context) {
            if (empty.size < 1) {
                empty.add(0, EmptyBar(R.drawable.ic_pccompiler_inactive, context.getString(R.string.text_no_compilations)))
            }
        }
    }
}