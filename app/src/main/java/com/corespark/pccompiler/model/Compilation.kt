package com.corespark.pccompiler.model

import com.parse.ParseUser


/**
 * @author Zachy Bazov.
 * @since 24/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Compilation {

    var id: String? = null
    var title: String? = null
    private var user: ParseUser? = null
    var cpu: Component? = null
    var cooler: Component? = null
    var motherboard: Component? = null
    var memory: Component? = null
    var storage: Component? = null
    var extStorage: Component? = null
    var optDrive: Component? = null
    var graphicCard: Component? = null
    var soundCard: Component? = null
    var powerSupply: Component? = null
    var case: Component? = null
    var opSystem: Component? = null

    fun assignCompilation(user: ParseUser?, title: String?) {
        this.user = user
        this.title = title
    }

    fun assignComponent(componentType: Int, item: Component?) = when (componentType) {
        0 -> cpu = item
        1 -> optDrive = item
        2 -> cooler = item
        3 -> graphicCard = item
        4 -> motherboard = item
        5 -> soundCard = item
        6 -> memory = item
        7 -> powerSupply = item
        8 -> storage = item
        9 -> case = item
        10 -> extStorage = item
        else -> opSystem = item
    }

    fun deassignComponent(componentType: Int) = when (componentType) {
        0 -> cpu = null
        1 -> optDrive = null
        2 -> cooler = null
        3 -> graphicCard = null
        4 -> motherboard = null
        5 -> soundCard = null
        6 -> memory = null
        7 -> powerSupply = null
        8 -> storage = null
        9 -> case = null
        10 -> extStorage = null
        else -> opSystem = null
    }
}