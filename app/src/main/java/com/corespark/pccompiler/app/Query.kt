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
                        }
                    }
                }
            }
        })
    }
}