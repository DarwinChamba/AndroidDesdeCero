package com.example.androiddesdecero.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.androiddesdecero.MainActivity
import com.example.androiddesdecero.model.MyNotification
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object NotificationHelper {


    val channelId="channel_id"

    fun createChannel(context: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "channel_id",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager =
                context.getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }


    @SuppressLint("MissingPermission")
    fun createNotification(context: Context, myNotification: MyNotification){


        val intent = Intent(context, MainActivity::class.java)
        intent.putExtra("notification",Json.encodeToString(myNotification))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        val pendingIntent = PendingIntent.getActivity(
            context,
            myNotification.requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )


        val notification = NotificationCompat.Builder(context,channelId)
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentTitle(myNotification.title)
            .setContentText(myNotification.description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(context)
            .notify(myNotification.requestCode,notification)
    }
}