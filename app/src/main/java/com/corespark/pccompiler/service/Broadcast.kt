package com.corespark.pccompiler.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.corespark.pccompiler.utility.BROADCAST_USER_UPDATE


/**
 * @author Zachy Bazov.
 * @since 18/12/2018.
 * CoreSpark Ltd.
 * PCCompiler.
 * All Rights Reserved. Copyright (c) 2018.
 */
object Broadcast {

    fun emit(context: Context, channel: BroadcastReceiver) {
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(channel, IntentFilter(BROADCAST_USER_UPDATE))

        val broadcast = Intent(BROADCAST_USER_UPDATE)
        LocalBroadcastManager.getInstance(context).sendBroadcast(broadcast)
    }

    fun abort(context: Context, channel: BroadcastReceiver) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(channel)
    }
}