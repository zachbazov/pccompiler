package com.corespark.pccompiler.database

import com.corespark.pccompiler.R
import com.corespark.pccompiler.model.Bar
import com.corespark.pccompiler.model.Component
import com.corespark.pccompiler.service.Auth
import com.corespark.pccompiler.utility.*
import com.parse.ParseObject
import com.parse.ParseQuery


/**
 * @author Zachy Bazov.
 * @since 11/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Query {

    private lateinit var query: ParseQuery<ParseObject>

    lateinit var cpu: ParseObject

    fun fetchComponents(table: String, list: MutableList<Any>) {
        query = ParseQuery.getQuery<ParseObject>(table)
        query.findInBackground { objects, e ->
            if (e == null) for (obj in objects!!) when (table) {
                TABLE_CPU -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_CPU) as String,
                        paramA = obj.get(KEY_CORE) as String,
                        paramB = obj.get(KEY_SPEED) as String,
                        paramC = obj.get(KEY_TDP) as String,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_COOLER -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_COOLER) as String,
                        paramA = obj.get(KEY_RPM) as String,
                        paramB = obj.get(KEY_NOISE) as String,
                        paramC = null,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_MOTHERBOARD -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_MOTHERBOARD) as String,
                        paramA = obj.get(KEY_SOCKET_CPU) as String,
                        paramB = obj.get(KEY_FORM_FACTOR) as String,
                        paramC = obj.get(KEY_RAM_SLOTS) as String,
                        paramD = obj.get(KEY_MAX_RAM) as String,
                        paramE = null,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_MEMORY -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_RAM) as String,
                        paramA = obj.get(KEY_TYPE) as String,
                        paramB = obj.get(KEY_SPEED) as String,
                        paramC = obj.get(KEY_MODULES) as String,
                        paramD = obj.get(KEY_CAS) as String,
                        paramE = obj.get(KEY_SIZE) as String,
                        paramF = obj.get(KEY_GB_PRICE) as String,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_STORAGE -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_STORAGE) as String,
                        paramA = obj.get(KEY_SERIES) as String,
                        paramB = obj.get(KEY_TYPE) as String,
                        paramC = obj.get(KEY_FORM) as String,
                        paramD = obj.get(KEY_CACHE) as String,
                        paramE = obj.get(KEY_CAPACITY) as String,
                        paramF = obj.get(KEY_GB_PRICE) as String,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_EXT_STORAGE -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_SERIES) as String,
                        paramA = obj.get(KEY_TYPE) as String,
                        paramB = obj.get(KEY_CAPACITY) as String,
                        paramC = obj.get(KEY_GB_PRICE) as String,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_OPT_DRIVE -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_OPTICAL_DRIVE) as String,
                        paramA = obj.get(KEY_BD) as String,
                        paramB = obj.get(KEY_CD) as String,
                        paramC = obj.get(KEY_DVD) as String,
                        paramD = obj.get(KEY_BD_WRITE) as String,
                        paramE = obj.get(KEY_CD_WRITE) as String,
                        paramF = obj.get(KEY_DVD_WRITE) as String,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_GRAPHIC_CARD -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_GRAPHIC_CARD) as String,
                        paramA = obj.get(KEY_SERIES) as String,
                        paramB = obj.get(KEY_CHIPSET) as String,
                        paramC = obj.get(KEY_MEMORY) as String,
                        paramD = obj.get(KEY_CORE_CLOCK) as String,
                        paramE = null,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_SOUND_CARD -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_SOUND_CARD) as String,
                        paramA = obj.get(KEY_CHIPSET) as String,
                        paramB = obj.get(KEY_BITS) as String,
                        paramC = obj.get(KEY_SNR) as String,
                        paramD = obj.get(KEY_CHANNELS) as String,
                        paramE = obj.get(KEY_RATE) as String,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_POWER_SUPPLY -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_POWER_SUPPLY) as String,
                        paramA = obj.get(KEY_SERIES) as String,
                        paramB = obj.get(KEY_FORM) as String,
                        paramC = obj.get(KEY_EFFICIENCY) as String,
                        paramD = obj.get(KEY_MODULAR) as String,
                        paramE = obj.get(KEY_WATTS) as String,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_CASE -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_CASE) as String,
                        paramA = obj.get(KEY_TYPE) as String,
                        paramB = obj.get(KEY_EXTERNAL) as String,
                        paramC = obj.get(KEY_INTERNAL) as String,
                        paramD = obj.get(KEY_POWER_SUPPLY) as String,
                        paramE = null,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
                TABLE_OP_SYSTEM -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(KEY_MANUFACTURER) as String,
                        component = obj.get(KEY_OPERATING_SYSTEM) as String,
                        paramA = null,
                        paramB = null,
                        paramC = null,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(KEY_PRICE) as String
                    )
                )
            }
        }
    }

    fun fetchCompilations() {
        query = ParseQuery.getQuery<ParseObject>("Compilation")
        query.whereEqualTo("user", Auth.parseUser)
        query.include("Compilation")
        val list: MutableList<ParseObject> = query.find()
        val size = list.size - 1

        for (i in 0..size) when (i) {
            0 -> {
                myCompilation00 = list[i].fetchIfNeeded<ParseObject>()
                cpu = list[i].getParseObject("cpu").fetchIfNeeded()
                val cpu = Component(
                    id = cpu.objectId,
                    manufaturer = cpu.get(KEY_MANUFACTURER) as String,
                    component = cpu.get(KEY_CPU) as String,
                    paramA = cpu.get(KEY_CORE) as String,
                    paramB = cpu.get(KEY_SPEED) as String,
                    paramC = cpu.get(KEY_TDP) as String,
                    paramD = null,
                    paramE = null,
                    paramF = null,
                    price = cpu.get(KEY_PRICE) as String
                )
                Bar.Compilation.list.add(i, Bar.Compilation(
                    myCompilation00.objectId, myCompilation00.get("title").toString(), R.mipmap.ic_pccompiler))
            }
            1 -> {
                myCompilation01 = list[i].fetchIfNeeded<ParseObject>()
                cpu = list[i].getParseObject("cpu").fetchIfNeeded()
                val cpu = Component(
                    id = cpu.objectId,
                    manufaturer = cpu.get(KEY_MANUFACTURER) as String,
                    component = cpu.get(KEY_CPU) as String,
                    paramA = cpu.get(KEY_CORE) as String,
                    paramB = cpu.get(KEY_SPEED) as String,
                    paramC = cpu.get(KEY_TDP) as String,
                    paramD = null,
                    paramE = null,
                    paramF = null,
                    price = cpu.get(KEY_PRICE) as String
                )
                Bar.Compilation.list.add(i, Bar.Compilation(
                    myCompilation01.objectId, myCompilation01.get("title").toString(), R.mipmap.ic_pccompiler))
            }
            2 -> {
                myCompilation02 = list[i].fetchIfNeeded<ParseObject>()
                cpu = list[i].getParseObject("cpu").fetchIfNeeded()
                val cpu = Component(
                    id = cpu.objectId,
                    manufaturer = cpu.get(KEY_MANUFACTURER) as String,
                    component = cpu.get(KEY_CPU) as String,
                    paramA = cpu.get(KEY_CORE) as String,
                    paramB = cpu.get(KEY_SPEED) as String,
                    paramC = cpu.get(KEY_TDP) as String,
                    paramD = null,
                    paramE = null,
                    paramF = null,
                    price = cpu.get(KEY_PRICE) as String
                )
                Bar.Compilation.list.add(i, Bar.Compilation(
                    myCompilation02.objectId, myCompilation02.get("title").toString(), R.mipmap.ic_pccompiler))
            }
        }
    }

    companion object {
        lateinit var myCompilation00: ParseObject
        lateinit var myCompilation01: ParseObject
        lateinit var myCompilation02: ParseObject
    }
}