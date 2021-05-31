package com.wlx.androidfinalexample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.ex1.Ex1_1Activity
import com.wlx.androidfinalexample.ex2.Ex2Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val exercise1: Button = findViewById(R.id.exercise1)
        exercise1.setOnClickListener {
            val intent = Intent(this,Ex1_1Activity::class.java)
            startActivity(intent)
        }
        val exercise2: Button = findViewById(R.id.exercise2)
        exercise2.setOnClickListener {
            val intent = Intent(this,Ex2Activity::class.java)
            startActivity(intent)
        }
    }
}