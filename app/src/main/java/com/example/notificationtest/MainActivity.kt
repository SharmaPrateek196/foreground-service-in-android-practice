package com.example.notificationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.notificationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.startService.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            startService(intent)
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show()
        }

        binding.endService.setOnClickListener {
            val intent = Intent(this, MusicService::class.java)
            stopService(intent)
            Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show()
        }
    }
}