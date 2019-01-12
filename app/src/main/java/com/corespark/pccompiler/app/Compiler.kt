package com.corespark.pccompiler.app

import android.app.Application
import com.corespark.pccompiler.model.Component
import com.corespark.pccompiler.utility.Color
import com.parse.Parse.initialize


/**
 * @author Zachy Bazov.
 * @since 13/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Compiler : Application() {

    companion object {
        lateinit var preferences: SharedPreferences
        lateinit var queries: Query
        lateinit var colors: Color

        val cpuList = mutableListOf<Any>()
        val coolerList = mutableListOf<Any>()
        val motherboardList = mutableListOf<Any>()
        val memoryList = mutableListOf<Any>()
        val storageList = mutableListOf<Any>()
        val extStorageList = mutableListOf<Any>()
        val optDriveList = mutableListOf<Any>()
        val graphicCardList = mutableListOf<Any>()
        val soundCardList = mutableListOf<Any>()
        val powerSupplyList = mutableListOf<Any>()
        val caseList = mutableListOf<Any>()
        val opSystemList = mutableListOf<Any>()
    }

    override fun onCreate() {
        super.onCreate()

        initialize(applicationContext)

        preferences = SharedPreferences(applicationContext)
        colors = Color(applicationContext)

        queries = Query(0)
        queries.retrieve("CPU", cpuList)
        queries = Query(1)
        queries.retrieve("Cooler", coolerList)
        queries = Query(2)
        queries.retrieve("Motherboard", motherboardList)
        queries = Query(3)
        queries.retrieve("Memory", memoryList)
        queries = Query(4)
        queries.retrieve("Storage", storageList)
        queries = Query(5)
        queries.retrieve("ExternalStorage", extStorageList)
        queries = Query(6)
        queries.retrieve("OpticalDrive", optDriveList)
        queries = Query(7)
        queries.retrieve("GraphicCard", graphicCardList)
        queries = Query(8)
        queries.retrieve("SoundCard", soundCardList)
        queries = Query(9)
        queries.retrieve("PowerSupply", powerSupplyList)
        queries = Query(10)
        queries.retrieve("Case", caseList)
        queries = Query(11)
        queries.retrieve("OS", opSystemList)
    }
}
