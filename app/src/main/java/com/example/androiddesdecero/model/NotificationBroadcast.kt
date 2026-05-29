package com.example.androiddesdecero.model

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.androiddesdecero.util.NotificationHelper
import kotlinx.serialization.json.Json

class NotificationBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationString = intent.getStringExtra("notification")
        notificationString?.let {
            val notification = Json.decodeFromString<MyNotification>(it)
            NotificationHelper.createNotification(context,notification)
        }
    }

}