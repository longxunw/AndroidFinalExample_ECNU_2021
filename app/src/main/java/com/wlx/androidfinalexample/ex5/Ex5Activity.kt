package com.wlx.androidfinalexample.ex5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.R

class Ex5Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex5)
        val sendBc: Button = findViewById(R.id.send_broadcast)
        sendBc.setOnClickListener {
            val intent = Intent("com.wlx.androidfinalexample.ex5.broadcast")
            intent.setPackage(packageName)
            intent.putExtra("data", 100)
            intent.putExtra("string", "hello")
            sendBroadcast(intent)
        }
    }
}