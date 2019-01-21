package com.corespark.pccompiler.app

import android.app.Application
import com.corespark.pccompiler.R
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

        val componentsList = arrayOf(
            cpuList, optDriveList, coolerList, graphicCardList, motherboardList, soundCardList, memoryList,
            powerSupplyList, storageList, caseList, extStorageList, opSystemList)
    }

    override fun onCreate() {
        super.onCreate()

        initialize(applicationContext)

        preferences = SharedPreferences(applicationContext)
        colors = Color(applicationContext)

        val query = Query()
        query.retrieve(applicationContext, getString(R.string.table_cpu), cpuList, 0)
        query.retrieve(applicationContext, getString(R.string.table_cooler), coolerList, 1)
        query.retrieve(applicationContext, getString(R.string.table_motherboard), motherboardList, 2)
        query.retrieve(applicationContext, getString(R.string.table_memory), memoryList, 3)
        query.retrieve(applicationContext, getString(R.string.table_storage), storageList, 4)
        query.retrieve(applicationContext, getString(R.string.table_external_storage), extStorageList, 5)
        query.retrieve(applicationContext, getString(R.string.table_optical_drive), optDriveList, 6)
        query.retrieve(applicationContext, getString(R.string.table_graphic_card), graphicCardList, 7)
        query.retrieve(applicationContext, getString(R.string.table_sound_card), soundCardList, 8)
        query.retrieve(applicationContext, getString(R.string.table_power_supply), powerSupplyList, 9)
        query.retrieve(applicationContext, getString(R.string.table_case), caseList, 10)
        query.retrieve(applicationContext, getString(R.string.table_operating_system), opSystemList, 11)
    }
}
