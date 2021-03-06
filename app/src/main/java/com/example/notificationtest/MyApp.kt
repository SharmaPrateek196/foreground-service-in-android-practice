package com.example.notificationtest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build


/**
 * Created by Prateek Sharma on 10/07/21.
 */
class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannelForOreaPlus();
    }

    private fun createNotificationChannelForOreaPlus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                resources.getString(R.string.notification_channel_id),
                resources.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}