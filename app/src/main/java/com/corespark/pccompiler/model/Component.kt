package com.corespark.pccompiler.model


/**
 * @author Zachy Bazov.
 * @since 11/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
data class Component(val id: String,
                     val manufaturer: String,
                     val component: String,
                     val paramA: String?,
                     val paramB: String?,
                     val paramC: String?,
                     val paramD: String?,
                     val paramE: String?,
                     val paramF: String?,
                     val price: String)