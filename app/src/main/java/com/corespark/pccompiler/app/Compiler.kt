package com.corespark.pccompiler.app

import android.app.Application
import com.corespark.pccompiler.R
import com.corespark.pccompiler.database.Query
import com.corespark.pccompiler.utility.Attribute
import com.corespark.pccompiler.utility.Color
import com.parse.Parse.initialize
import com.parse.ParseObject


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
        lateinit var attributes: Attribute
        lateinit var query: Query

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

        private val cpuOldPositionList = mutableListOf<Int>()
        private val coolerOldPositionList = mutableListOf<Int>()
        private val motherboardOldPositionList = mutableListOf<Int>()
        private val memoryOldPositionList = mutableListOf<Int>()
        private val storageOldPositionList = mutableListOf<Int>()
        private val extStorageOldPositionList = mutableListOf<Int>()
        private val optDriveOldPositionList = mutableListOf<Int>()
        private val graphicCardOldPositionList = mutableListOf<Int>()
        private val soundCardOldPositionList = mutableListOf<Int>()
        private val powerSupplyOldPositionList = mutableListOf<Int>()
        private val caseOldPositionList = mutableListOf<Int>()
        private val opSystemOldPositionList = mutableListOf<Int>()

        val oldPositionsList = arrayOf(
            cpuOldPositionList, optDriveOldPositionList, coolerOldPositionList, graphicCardOldPositionList,
            motherboardOldPositionList, soundCardOldPositionList, memoryOldPositionList, powerSupplyOldPositionList,
            storageOldPositionList, caseOldPositionList, extStorageOldPositionList, opSystemOldPositionList)
    }

    override fun onCreate() {
        super.onCreate()

        initialize(applicationContext)
        ParseObject.registerSubclass(com.corespark.pccompiler.model.Component::class.java)

        preferences = SharedPreferences(applicationContext)
        colors = Color(applicationContext)
        attributes = Attribute()
        query = Query()

        query.retrieveComponents(applicationContext, getString(R.string.table_cpu), cpuList)
        query.retrieveComponents(applicationContext, getString(R.string.table_cooler), coolerList)
        query.retrieveComponents(applicationContext, getString(R.string.table_motherboard), motherboardList)
        query.retrieveComponents(applicationContext, getString(R.string.table_memory), memoryList)
        query.retrieveComponents(applicationContext, getString(R.string.table_storage), storageList)
        query.retrieveComponents(applicationContext, getString(R.string.table_external_storage), extStorageList)
        query.retrieveComponents(applicationContext, getString(R.string.table_optical_drive), optDriveList)
        query.retrieveComponents(applicationContext, getString(R.string.table_graphic_card), graphicCardList)
        query.retrieveComponents(applicationContext, getString(R.string.table_sound_card), soundCardList)
        query.retrieveComponents(applicationContext, getString(R.string.table_power_supply), powerSupplyList)
        query.retrieveComponents(applicationContext, getString(R.string.table_case), caseList)
        query.retrieveComponents(applicationContext, getString(R.string.table_operating_system), opSystemList)
    }
}
