package com.wlx.androidfinalexample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.ex1.Ex1_1Activity
import com.wlx.androidfinalexample.ex2.Ex2Activity
import com.wlx.androidfinalexample.ex3.Ex3Activity
import com.wlx.androidfinalexample.ex4.Ex4Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val exercise1: Button = findViewById(R.id.exercise1)
        exercise1.setOnClickListener {
            val intent = Intent(this, Ex1_1Activity::class.java)
            startActivity(intent)
        }
        val exercise2: Button = findViewById(R.id.exercise2)
        exercise2.setOnClickListener {
            val intent = Intent(this, Ex2Activity::class.java)
            startActivity(intent)
        }
        val exercise3: Button = findViewById(R.id.exercise3)
        exercise3.setOnClickListener {
            val intent = Intent(this, Ex3Activity::class.java)
            startActivity(intent)
        }
        val exercise4: Button = findViewById(R.id.exercise4)
        exercise4.setOnClickListener {
            val intent = Intent(this, Ex4Activity::class.java)
            startActivity(intent)
        }
    }
}