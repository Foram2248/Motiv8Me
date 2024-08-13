package com.example.dalitymotivation

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationUtil {

    private const val CHANNEL_ID = "MotivationNotificationChannel"

    @SuppressLint("MissingPermission")
    fun showNotification(context: Context, quote: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelName = "Motivation Notification"
            val channel = NotificationChannel(CHANNEL_ID, channelName, IMPORTANCE_DEFAULT).apply {
                description = "Channel for motivation notifications"
                enableLights(true)
                enableVibration(true)
                lightColor = android.graphics.Color.GREEN
                vibrationPattern = longArrayOf(500, 500, 500, 500)
            }
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Stay Motivated!")
            .setContentText(quote)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setSound(soundUri)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(1, notificationBuilder.build())
        }
    }
}
