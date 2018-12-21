package com.corespark.pccompiler.model

import com.corespark.pccompiler.R


/**
 * @author Zachy Bazov.
 * @since 20/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Compilation(val id: Int, val title: String, val image: Int) {

    companion object {
        val list = mutableListOf<Compilation>()

        fun add() {
            if (list.size < 2) {
                list.add(0, Compilation(0, "PCCompiler", R.mipmap.ic_pccompiler))
                list.add(0, Compilation(1, "PCCompiler", R.mipmap.ic_pccompiler))
            }
        }
    }
}