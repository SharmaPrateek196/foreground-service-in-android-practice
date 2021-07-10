package com.example.notificationtest

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat


/**
 * Created by Prateek Sharma on 10/07/21.
 */
class MusicService: Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private val NOTIFICATION_REQ_CODE = 0

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.ringtone)
        mediaPlayer.setLooping(false)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()

        val notificationIntent: Intent = Intent(this, MainActivity::class.java)
        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(this, NOTIFICATION_REQ_CODE, notificationIntent, 0)

        val notification: Notification = NotificationCompat
            .Builder(
                this,
                resources.getString(R.string.notification_channel_id)
            )
            .setContentTitle("Music Foreground Service")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        //returning null for now
        return null;
    }
}
