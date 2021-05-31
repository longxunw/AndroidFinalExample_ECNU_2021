package com.wlx.androidfinalexample.ex1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.R

class Ex1_2Activity : AppCompatActivity() {
    private val TAG = "Ex1_2Activity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex1_2)
        val group = intent.getIntExtra("group",0)
        val mainTeam = intent.getStringExtra("mainTeam")
        val guestTeam = intent.getStringExtra("guestTeam")
        val result = intent.getStringExtra("result")
        Log.d(TAG, "onCreate: ")
        val groupText: TextView = findViewById(R.id.group_text)
        val mainText: TextView = findViewById(R.id.main_team_text)
        val guestText: TextView = findViewById(R.id.guest_team_text)
        val resultText: TextView = findViewById(R.id.result_text)
        groupText.text = group.toString()
        mainText.text = mainTeam
        guestText.text = guestTeam
        resultText.text = result

        val back: Button = findViewById(R.id.back)
        back.setOnClickListener {
            finish()
        }
    }
}