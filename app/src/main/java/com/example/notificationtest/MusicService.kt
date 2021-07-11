package com.example.notificationtest

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import androidx.core.app.NotificationCompat


/**
 * Created by Prateek Sharma on 10/07/21.
 */
class MusicService: Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private val binder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.ringtone)
        mediaPlayer.setLooping(true)
    }

    fun startMusicBoundService() {
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    override fun onBind(intent: Intent?): IBinder? {
        //this binder object comes as an argument in
        //onServiceConnected()
        return binder
    }

    inner class LocalBinder: Binder() {
        // Return this instance of MusicService so clients can call public methods
        fun getMusicService(): MusicService = this@MusicService
    }
}
