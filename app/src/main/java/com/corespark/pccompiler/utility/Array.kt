package com.corespark.pccompiler.utility


/**
 * @author Zachy Bazov.
 * @since 13/02/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
class Array {

    companion object {

        val tableArray = arrayOf(
            TABLE_CPU, TABLE_OPT_DRIVE, TABLE_COOLER, TABLE_GRAPHIC_CARD, TABLE_MOTHERBOARD, TABLE_SOUND_CARD,
            TABLE_MEMORY, TABLE_POWER_SUPPLY, TABLE_STORAGE, TABLE_CASE, TABLE_EXT_STORAGE, TABLE_OP_SYSTEM
        )

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

        val componentsArray = arrayOf(
            cpuList, optDriveList, coolerList, graphicCardList, motherboardList, soundCardList, memoryList,
            powerSupplyList, storageList, caseList, extStorageList, opSystemList
        )

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

        val oldPositionsArray = arrayOf(
            cpuOldPositionList, optDriveOldPositionList, coolerOldPositionList, graphicCardOldPositionList,
            motherboardOldPositionList, soundCardOldPositionList, memoryOldPositionList, powerSupplyOldPositionList,
            storageOldPositionList, caseOldPositionList, extStorageOldPositionList, opSystemOldPositionList
        )
    }
}