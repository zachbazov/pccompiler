package com.corespark.pccompiler.app

import com.corespark.pccompiler.model.Component
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery


/**
 * @author Zachy Bazov.
 * @since 11/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Query(var type: Int) {

    lateinit var query: ParseQuery<ParseObject>

    fun retrieve(table: String, list: MutableList<Any>) {
        query = ParseQuery.getQuery<ParseObject>(table)

        query.findInBackground(object : FindCallback<ParseObject> {
            override fun done(objects: MutableList<ParseObject>?, e: ParseException?) {
                if (e == null) {
                    for (obj in objects!!) {
                        when (type) {
                            0 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.CPU(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("cpu") as String,
                                            paramA = obj.get("core") as String,
                                            paramB = obj.get("speed") as String,
                                            paramC = obj.get("tdp") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            1 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.Cooler(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("cooler") as String,
                                            paramA = obj.get("rpm") as String,
                                            paramB = obj.get("noise") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            2 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.Motherboard(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("motherboard") as String,
                                            paramA = obj.get("socketcpu") as String,
                                            paramB = obj.get("formfactor") as String,
                                            paramC = obj.get("ramslots") as String,
                                            paramD = obj.get("maxram") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            3 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.Memory(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("ram") as String,
                                            paramA = obj.get("type") as String,
                                            paramB = obj.get("speed") as String,
                                            paramC = obj.get("modules") as String,
                                            paramD = obj.get("cas") as String,
                                            paramE = obj.get("size") as String,
                                            paramF = obj.get("gbprice") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            4 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.Storage(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("storage") as String,
                                            paramA = obj.get("series") as String,
                                            paramB = obj.get("type") as String,
                                            paramC = obj.get("form") as String,
                                            paramD = obj.get("cache") as String,
                                            paramE = obj.get("capacity") as String,
                                            paramF = obj.get("gbprice") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            5 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.ExternalStorage(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("series") as String,
                                            paramA = obj.get("type") as String,
                                            paramB = obj.get("capacity") as String,
                                            paramC = obj.get("gbprice") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            6 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.OpticalDrive(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("opticaldrive") as String,
                                            paramA = obj.get("bd") as String,
                                            paramB = obj.get("cd") as String,
                                            paramC = obj.get("dvd") as String,
                                            paramD = obj.get("bdwrite") as String,
                                            paramE = obj.get("cdwrite") as String,
                                            paramF = obj.get("dvdwrite") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            7 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.GraphicCard(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("graphiccard") as String,
                                            paramA = obj.get("series") as String,
                                            paramB = obj.get("chipset") as String,
                                            paramC = obj.get("memory") as String,
                                            paramD = obj.get("coreclock") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            8 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.SoundCard(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("soundcard") as String,
                                            paramA = obj.get("chipset") as String,
                                            paramB = obj.get("bits") as String,
                                            paramC = obj.get("snr") as String,
                                            paramD = obj.get("channels") as String,
                                            paramE = obj.get("rate") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            9 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.PowerSupply(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("powersupply") as String,
                                            paramA = obj.get("series") as String,
                                            paramB = obj.get("form") as String,
                                            paramC = obj.get("efficiency") as String,
                                            paramD = obj.get("modular") as String,
                                            paramE = obj.get("watts") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            10 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.Case(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("case") as String,
                                            paramA = obj.get("type") as String,
                                            paramB = obj.get("external") as String,
                                            paramC = obj.get("internal") as String,
                                            paramD = obj.get("powersupply") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                            11 -> {
                                if (list.size < 10) {
                                    list.add(
                                        Component.OperatingSystem(
                                            id = obj.objectId,
                                            manufaturer = obj.get("manufacturer") as String,
                                            component = obj.get("operatingsystem") as String,
                                            price = obj.get("price") as String
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        })
    }
}