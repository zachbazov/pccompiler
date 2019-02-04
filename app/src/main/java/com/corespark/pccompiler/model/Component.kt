package com.corespark.pccompiler.model

import com.parse.ParseClassName
import com.parse.ParseObject


/**
 * @author Zachy Bazov.
 * @since 11/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
@ParseClassName("Component")
class Component() : ParseObject() {

    lateinit var id: String
    lateinit var manufaturer: String
    lateinit var component: String
    var paramA: String? = null
    var paramB: String? = null
    var paramC: String? = null
    var paramD: String? = null
    var paramE: String? = null
    var paramF: String? = null
    lateinit var price: String

    constructor(id: String,
                manufaturer: String,
                component: String,
                paramA: String?,
                paramB: String?,
                paramC: String?,
                paramD: String?,
                paramE: String?,
                paramF: String?,
                price: String) : this() {
        this.id = id
        this.manufaturer = manufaturer
        this.component = component
        this.paramA = paramA
        this.paramB = paramB
        this.paramC = paramC
        this.paramD = paramD
        this.paramE = paramE
        this.paramF = paramF
        this.price = price
    }
}