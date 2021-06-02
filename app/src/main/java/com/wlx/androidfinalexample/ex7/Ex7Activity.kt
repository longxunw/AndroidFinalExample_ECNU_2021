package com.wlx.androidfinalexample.ex7

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.R

class Ex7Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex7)
        val start: Button = findViewById(R.id.start_service)
        start.setOnClickListener {
            val intent = Intent(this, Ex7Service::class.java)
            startService(intent)
        }
        val stop: Button = findViewById(R.id.stop_service)
        stop.setOnClickListener {
            val intent = Intent(this, Ex7Service::class.java)
            stopService(intent)
        }
    }
}