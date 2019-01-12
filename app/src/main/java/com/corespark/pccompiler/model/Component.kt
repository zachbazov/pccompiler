package com.corespark.pccompiler.model

import com.corespark.pccompiler.R
import com.parse.ParseClassName


/**
 * @author Zachy Bazov.
 * @since 11/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
open class Component {

    @ParseClassName("CPU")
    data class CPU(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var paramC: String,
        var price: String
    ) : Component() {
        var image = R.mipmap.ic_cpu
    }

    @ParseClassName("Cooler")
    data class Cooler(
        var id: String,
        var manufaturer: String,
        var component: String,
        var paramA: String,
        var paramB: String,
        var price: String
    ) : Component() {
        var image = R.mipmap.ic_cooler
    }
}