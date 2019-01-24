package com.corespark.pccompiler.app

import android.content.Context
import com.corespark.pccompiler.R
import com.corespark.pccompiler.model.Component
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

    lateinit var query: ParseQuery<ParseObject>

    fun retrieve(context: Context, table: String, list: MutableList<Any>) {
        query = ParseQuery.getQuery<ParseObject>(table)
        query.findInBackground { objects, e ->
            if (e == null) for (obj in objects!!) when (table) {
                context.getString(R.string.table_cpu) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_cpu)) as String,
                        paramA = obj.get(context.getString(R.string.key_core)) as String,
                        paramB = obj.get(context.getString(R.string.key_speed)) as String,
                        paramC = obj.get(context.getString(R.string.key_tdp)) as String,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_cooler) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_cooler)) as String,
                        paramA = obj.get(context.getString(R.string.key_rpm)) as String,
                        paramB = obj.get(context.getString(R.string.key_noise)) as String,
                        paramC = null,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_motherboard) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_motherboard)) as String,
                        paramA = obj.get(context.getString(R.string.key_socket_cpu)) as String,
                        paramB = obj.get(context.getString(R.string.key_form_factor)) as String,
                        paramC = obj.get(context.getString(R.string.key_ram_slots)) as String,
                        paramD = obj.get(context.getString(R.string.key_max_ram)) as String,
                        paramE = null,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_memory) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_ram)) as String,
                        paramA = obj.get(context.getString(R.string.key_type)) as String,
                        paramB = obj.get(context.getString(R.string.key_speed)) as String,
                        paramC = obj.get(context.getString(R.string.key_modules)) as String,
                        paramD = obj.get(context.getString(R.string.key_cas)) as String,
                        paramE = obj.get(context.getString(R.string.key_size)) as String,
                        paramF = obj.get(context.getString(R.string.key_gb_price)) as String,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_storage) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_storage)) as String,
                        paramA = obj.get(context.getString(R.string.key_series)) as String,
                        paramB = obj.get(context.getString(R.string.key_type)) as String,
                        paramC = obj.get(context.getString(R.string.key_form)) as String,
                        paramD = obj.get(context.getString(R.string.key_cache)) as String,
                        paramE = obj.get(context.getString(R.string.key_capacity)) as String,
                        paramF = obj.get(context.getString(R.string.key_gb_price)) as String,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_external_storage) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_series)) as String,
                        paramA = obj.get(context.getString(R.string.key_type)) as String,
                        paramB = obj.get(context.getString(R.string.key_capacity)) as String,
                        paramC = obj.get(context.getString(R.string.key_gb_price)) as String,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_optical_drive) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_optical_drive)) as String,
                        paramA = obj.get(context.getString(R.string.key_bd)) as String,
                        paramB = obj.get(context.getString(R.string.key_cd)) as String,
                        paramC = obj.get(context.getString(R.string.key_dvd)) as String,
                        paramD = obj.get(context.getString(R.string.key_bd_write)) as String,
                        paramE = obj.get(context.getString(R.string.key_cd_write)) as String,
                        paramF = obj.get(context.getString(R.string.key_dvd_write)) as String,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_graphic_card) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_graphic_card)) as String,
                        paramA = obj.get(context.getString(R.string.key_series)) as String,
                        paramB = obj.get(context.getString(R.string.key_chipset)) as String,
                        paramC = obj.get(context.getString(R.string.key_memory)) as String,
                        paramD = obj.get(context.getString(R.string.key_core_clock)) as String,
                        paramE = null,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_sound_card) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_sound_card)) as String,
                        paramA = obj.get(context.getString(R.string.key_chipset)) as String,
                        paramB = obj.get(context.getString(R.string.key_bits)) as String,
                        paramC = obj.get(context.getString(R.string.key_snr)) as String,
                        paramD = obj.get(context.getString(R.string.key_channels)) as String,
                        paramE = obj.get(context.getString(R.string.key_rate)) as String,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_power_supply) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_power_supply)) as String,
                        paramA = obj.get(context.getString(R.string.key_series)) as String,
                        paramB = obj.get(context.getString(R.string.key_form)) as String,
                        paramC = obj.get(context.getString(R.string.key_efficiency)) as String,
                        paramD = obj.get(context.getString(R.string.key_modular)) as String,
                        paramE = obj.get(context.getString(R.string.key_watts)) as String,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_case) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_case)) as String,
                        paramA = obj.get(context.getString(R.string.key_type)) as String,
                        paramB = obj.get(context.getString(R.string.key_external)) as String,
                        paramC = obj.get(context.getString(R.string.key_internal)) as String,
                        paramD = obj.get(context.getString(R.string.key_power_supply)) as String,
                        paramE = null,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
                context.getString(R.string.table_operating_system) -> if (list.size < 10) list.add(
                    Component(
                        id = obj.objectId,
                        manufaturer = obj.get(context.getString(R.string.key_manufacturer)) as String,
                        component = obj.get(context.getString(R.string.key_operating_system)) as String,
                        paramA = null,
                        paramB = null,
                        paramC = null,
                        paramD = null,
                        paramE = null,
                        paramF = null,
                        price = obj.get(context.getString(R.string.key_price)) as String
                    )
                )
            }
        }
    }
}