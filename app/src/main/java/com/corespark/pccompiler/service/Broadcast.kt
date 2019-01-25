package com.corespark.pccompiler.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import com.corespark.pccompiler.R


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
            .registerReceiver(channel, IntentFilter(context.getString(R.string.broadcast_channel_auth)))

        val broadcast = Intent(context.getString(R.string.broadcast_channel_auth))
        LocalBroadcastManager.getInstance(context).sendBroadcast(broadcast)
    }

    fun abort(context: Context, channel: BroadcastReceiver) = LocalBroadcastManager.getInstance(context).unregisterReceiver(channel)
}