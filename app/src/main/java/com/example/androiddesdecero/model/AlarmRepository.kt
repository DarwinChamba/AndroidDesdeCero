package com.example.androiddesdecero.model

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AlarmRepository {

    fun programarNotificacion(context: Context, myNotification: MyNotification) {
        /*
        AlarmManager me permite programar una accion que se ejecutara
        en un momemento especifico es decir dentro de (10 minutos 20 minutos o
        el tiempo que el usuario seleccione en el dialogo)
         */

        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                return
            }
        }

        /*
        cuando se cumple tiempo, AlarmManager ejecuta un PendingIntent
        que apunta a un BroadcastReceiver
        el método getBroadcast -> envia un intent al BroadCastReceiver
         */
        val intent = Intent(context, NotificationBroadcast::class.java)
        intent.putExtra("notification", Json.encodeToString(myNotification))
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            myNotification.requestCode,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            myNotification.time,
            pendingIntent
        )

    }
}