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
    var compilationTitle: String? = null
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
    var isCompilationFetched = false
    var isCompiling = false

    fun assignCompilation(user: ParseUser?, title: String?) {
        this.user = user
        this.compilationTitle = title
    }

    fun assignComponent(componentType: Int, item: Component?) {
        when (componentType) {
            0 -> {
                cpu = item
                Bar.Cart.list.add(cpu!!)
            }
            1 -> {
                optDrive = item
                Bar.Cart.list.add(optDrive!!)
            }
            2 -> {
                cooler = item
                Bar.Cart.list.add(cooler!!)
            }
            3 -> {
                graphicCard = item
                Bar.Cart.list.add(graphicCard!!)
            }
            4 -> {
                motherboard = item
                Bar.Cart.list.add(motherboard!!)
            }
            5 -> {
                soundCard = item
                Bar.Cart.list.add(soundCard!!)
            }
            6 -> {
                memory = item
                Bar.Cart.list.add(memory!!)
            }
            7 -> {
                powerSupply = item
                Bar.Cart.list.add(powerSupply!!)
            }
            8 -> {
                storage = item
                Bar.Cart.list.add(storage!!)
            }
            9 -> {
                case = item
                Bar.Cart.list.add(case!!)
            }
            10 -> {
                extStorage = item
                Bar.Cart.list.add(extStorage!!)
            }
            11 -> {
                opSystem = item
                Bar.Cart.list.add(opSystem!!)
            }
        }
    }

    fun deassignComponent(componentType: Int, item: Component?) {
        Bar.Cart.list.removeIf { (it as Component) == item }
        when (componentType) {
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
            11 -> opSystem = null
        }
    }

    fun replaceComponent(componentType: Int, item: Component?) {
        when (componentType) {
            0 -> {
                deassignComponent(componentType, cpu)
                assignComponent(componentType, item)
            }
            1 -> {
                deassignComponent(componentType, optDrive)
                assignComponent(componentType, item)
            }
            2 -> {
                deassignComponent(componentType, cooler)
                assignComponent(componentType, item)
            }
            3 -> {
                deassignComponent(componentType, graphicCard)
                assignComponent(componentType, item)
            }
            4 -> {
                deassignComponent(componentType, motherboard)
                assignComponent(componentType, item)
            }
            5 -> {
                deassignComponent(componentType, soundCard)
                assignComponent(componentType, item)
            }
            6 -> {
                deassignComponent(componentType, memory)
                assignComponent(componentType, item)
            }
            7 -> {
                deassignComponent(componentType, powerSupply)
                assignComponent(componentType, item)
            }
            8 -> {
                deassignComponent(componentType, storage)
                assignComponent(componentType, item)
            }
            9 -> {
                deassignComponent(componentType, case)
                assignComponent(componentType, item)
            }
            10 -> {
                deassignComponent(componentType, extStorage)
                assignComponent(componentType, item)
            }
            11 -> {
                deassignComponent(componentType, opSystem)
                assignComponent(componentType, item)
            }
        }
    }

    fun clearAll() {
        Bar.Cart.list.clear()
        cpu = null
        optDrive = null
        cooler = null
        graphicCard = null
        motherboard = null
        soundCard = null
        memory = null
        powerSupply = null
        storage = null
        case = null
        extStorage = null
        opSystem = null
    }
}