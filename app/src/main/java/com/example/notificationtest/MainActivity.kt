package com.example.notificationtest

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.notificationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var musicService: MusicService
    private var hasBound = false

    val connection = object : ServiceConnection {
        //below service named IBinder instance is the same binder object we get
        //from onBind()'s return value
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.LocalBinder
            musicService = binder.getMusicService()
            hasBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            hasBound = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.startBoundService.setOnClickListener {
            if(!hasBound) {
                val intent = Intent(this, MusicService::class.java)
                bindService(intent, connection, BIND_AUTO_CREATE)
            }
            musicService.startMusicBoundService()
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show()
        }

        binding.endBoundService.setOnClickListener {
            //If service is already bound
            if(hasBound) {
                unbindService(connection)
                hasBound = false
                Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Service is already not running", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MusicService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

    // We should unbind the service; In case there is any kind of unwanted killing
    // of this activity by the system
    override fun onStop() {
        super.onStop()
        if(hasBound) {
            unbindService(connection)
            hasBound = false
            Toast.makeText(this, "Service Stopped from onStop()", Toast.LENGTH_SHORT).show()
        }
    }
}