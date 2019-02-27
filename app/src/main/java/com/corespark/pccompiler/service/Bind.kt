package com.corespark.pccompiler.service

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.corespark.pccompiler.R
import com.corespark.pccompiler.model.Component


/**
 * @author Zachy Bazov.
 * @since 27/01/2019.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Bind {

    fun componentImage(view: ImageView, componentType: Int) = when (componentType) {
        0 -> view.setImageResource(R.drawable.ic_cpu)
        1 -> view.setImageResource(R.drawable.ic_opt_drive)
        2 -> view.setImageResource(R.drawable.ic_cooler)
        3 -> view.setImageResource(R.drawable.ic_graphic_card)
        4 -> view.setImageResource(R.drawable.ic_motherboard)
        5 -> view.setImageResource(R.drawable.ic_sound_card)
        6 -> view.setImageResource(R.drawable.ic_memory)
        7 -> view.setImageResource(R.drawable.ic_power_supply)
        8 -> view.setImageResource(R.drawable.ic_storage)
        9 -> view.setImageResource(R.drawable.ic_case)
        10 -> view.setImageResource(R.drawable.ic_ext_storage)
        else -> view.setImageResource(R.drawable.ic_os)
    }

    fun componentInfo(context: Context, item: Component, componentType: Int, params: Array<View>) = when (componentType) {
        0 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_cpu)
            (params[1] as TextView).text = context.getString(R.string.type_cpu)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_core)
            (params[4] as TextView).text = context.getString(R.string.dialog_speed)
            (params[5] as TextView).text = context.getString(R.string.dialog_tdp)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[6] as TextView))
            (params[16] as LinearLayout).removeView((params[7] as TextView))
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[12] as TextView))
            (params[17] as LinearLayout).removeView((params[13] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        1 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_opt_drive)
            (params[1] as TextView).text = context.getString(R.string.type_optical_drive)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_bd)
            (params[4] as TextView).text = context.getString(R.string.dialog_cd)
            (params[5] as TextView).text = context.getString(R.string.dialog_dvd)
            (params[6] as TextView).text = context.getString(R.string.dialog_bd_write)
            (params[7] as TextView).text = context.getString(R.string.dialog_cd_write)
            (params[8] as TextView).text = context.getString(R.string.dialog_dvd_write)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[13] as TextView).text = item.paramE
            (params[14] as TextView).text = item.paramF
            (params[15] as TextView).text = String.format("$%s", item.price)
        }
        2 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_cooler)
            (params[1] as TextView).text = context.getString(R.string.type_cooler)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_rpm)
            (params[4] as TextView).text = context.getString(R.string.dialog_noise)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[5] as TextView))
            (params[16] as LinearLayout).removeView((params[6] as TextView))
            (params[16] as LinearLayout).removeView((params[7] as TextView))
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[11] as TextView))
            (params[17] as LinearLayout).removeView((params[12] as TextView))
            (params[17] as LinearLayout).removeView((params[13] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        3 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_graphic_card)
            (params[1] as TextView).text = context.getString(R.string.type_graphic_card)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_series)
            (params[4] as TextView).text = context.getString(R.string.dialog_chipset)
            (params[5] as TextView).text = context.getString(R.string.dialog_memory)
            (params[6] as TextView).text = context.getString(R.string.dialog_core_clock)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[7] as TextView))
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[13] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        4 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_motherboard)
            (params[1] as TextView).text = context.getString(R.string.type_motherboard)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_socket_cpu)
            (params[4] as TextView).text = context.getString(R.string.dialog_form_factor)
            (params[5] as TextView).text = context.getString(R.string.dialog_ram_slots)
            (params[6] as TextView).text = context.getString(R.string.dialog_max_ram)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[7] as TextView))
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[13] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        5 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_sound_card)
            (params[1] as TextView).text = context.getString(R.string.type_sound_card)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_chipset)
            (params[4] as TextView).text = context.getString(R.string.dialog_bits)
            (params[5] as TextView).text = context.getString(R.string.dialog_snr)
            (params[6] as TextView).text = context.getString(R.string.dialog_channels)
            (params[7] as TextView).text = context.getString(R.string.dialog_rate)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[13] as TextView).text = item.paramE
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        6 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_memory)
            (params[1] as TextView).text = context.getString(R.string.type_memory)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_type)
            (params[4] as TextView).text = context.getString(R.string.dialog_speed)
            (params[5] as TextView).text = context.getString(R.string.dialog_modules)
            (params[6] as TextView).text = context.getString(R.string.dialog_cas)
            (params[7] as TextView).text = context.getString(R.string.dialog_size)
            (params[8] as TextView).text = context.getString(R.string.dialog_gb_price)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[13] as TextView).text = item.paramE
            (params[14] as TextView).text = String.format("$%s", item.paramF)
            (params[15] as TextView).text = String.format("$%s", item.price)
        }
        7 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_power_supply)
            (params[1] as TextView).text = context.getString(R.string.type_power_supply)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_series)
            (params[4] as TextView).text = context.getString(R.string.dialog_form)
            (params[5] as TextView).text = context.getString(R.string.dialog_efficiency)
            (params[6] as TextView).text = context.getString(R.string.dialog_modular)
            (params[7] as TextView).text = context.getString(R.string.dialog_watts)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[13] as TextView).text = item.paramE
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        8 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_storage)
            (params[1] as TextView).text = context.getString(R.string.type_storage)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_series)
            (params[4] as TextView).text = context.getString(R.string.dialog_form)
            (params[5] as TextView).text = context.getString(R.string.dialog_type)
            (params[6] as TextView).text = context.getString(R.string.dialog_cache)
            (params[7] as TextView).text = context.getString(R.string.dialog_capacity)
            (params[8] as TextView).text = context.getString(R.string.dialog_gb_price)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[13] as TextView).text = item.paramE
            (params[14] as TextView).text = String.format("$%s", item.paramF)
            (params[15] as TextView).text = String.format("$%s", item.price)
        }
        9 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_case)
            (params[1] as TextView).text = context.getString(R.string.type_case)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_type)
            (params[4] as TextView).text = context.getString(R.string.dialog_external)
            (params[5] as TextView).text = context.getString(R.string.dialog_internal)
            (params[6] as TextView).text = context.getString(R.string.dialog_power_supply)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = item.paramC
            (params[12] as TextView).text = item.paramD
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[7] as TextView))
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[13] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        10 -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_ext_storage)
            (params[1] as TextView).text = context.getString(R.string.type_external_storage)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[3] as TextView).text = context.getString(R.string.dialog_type)
            (params[4] as TextView).text = context.getString(R.string.dialog_capacity)
            (params[5] as TextView).text = context.getString(R.string.dialog_gb_price)
            (params[9] as TextView).text = item.paramA
            (params[10] as TextView).text = item.paramB
            (params[11] as TextView).text = String.format("$%s", item.paramC)
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[6] as TextView))
            (params[16] as LinearLayout).removeView((params[7] as TextView))
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[12] as TextView))
            (params[17] as LinearLayout).removeView((params[13] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
        else -> {
            (params[0] as ImageView).setImageResource(R.drawable.ic_os)
            (params[1] as TextView).text = context.getString(R.string.type_os)
            (params[2] as TextView).text = String.format("%s %s", item.manufaturer, item.component)
            (params[15] as TextView).text = String.format("$%s", item.price)
            (params[16] as LinearLayout).removeView((params[18] as TextView))
            (params[17] as LinearLayout).removeView((params[19] as TextView))
            (params[16] as LinearLayout).removeView((params[3] as TextView))
            (params[16] as LinearLayout).removeView((params[4] as TextView))
            (params[16] as LinearLayout).removeView((params[5] as TextView))
            (params[16] as LinearLayout).removeView((params[6] as TextView))
            (params[16] as LinearLayout).removeView((params[7] as TextView))
            (params[16] as LinearLayout).removeView((params[8] as TextView))
            (params[17] as LinearLayout).removeView((params[9] as TextView))
            (params[17] as LinearLayout).removeView((params[10] as TextView))
            (params[17] as LinearLayout).removeView((params[11] as TextView))
            (params[17] as LinearLayout).removeView((params[12] as TextView))
            (params[17] as LinearLayout).removeView((params[13] as TextView))
            (params[17] as LinearLayout).removeView((params[14] as TextView))
        }
    }
}