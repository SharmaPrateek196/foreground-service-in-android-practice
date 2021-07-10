package com.example.notificationtest

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

/**
 * Created by Prateek Sharma on 10/07/21.
 */
class MusicService: Service() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.ringtone)
        mediaPlayer.setLooping(false)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()
    }

    override fun onBind(intent: Intent?): IBinder? {
        //returning null for now
        return null;
    }
}
