package com.wlx.androidfinalexample.ex10

import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.MyDatabaseHelper
import com.wlx.androidfinalexample.R

class Ex10Activity : AppCompatActivity() {
    private lateinit var dbHelper: MyDatabaseHelper
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var edit: EditText
    private val thread = StringThread()
    private var isStarted = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex10)
        dbHelper = MyDatabaseHelper(this, "Exercise.db", 2)
        val start: Button = findViewById(R.id.start)
        start.setOnClickListener {
            if (isStarted) {
                Toast.makeText(this, "已经启动过啦", Toast.LENGTH_SHORT).show()
            } else {
                isStarted = true
                thread.start()
            }
        }
        edit = findViewById(R.id.edit)
    }

    private fun getRandomStr(): String {
        val dictChars = mutableListOf<Char>().apply {
            "123456789zxcvbnmasdfghjklqwertyuiop".forEach {
                this.add(it)
            }
        }
        val randomStr =
            StringBuilder().apply { (1..((10..30).random())).onEach { append(dictChars.random()) } }
        return randomStr.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        thread.interrupt()
    }

    inner class StringThread : Thread() {
        override fun run() {
            while (!isInterrupted) {
                val str = getRandomStr()
                val db = dbHelper.writableDatabase
                val value = ContentValues().apply {
                    put("string", str)
                }
                db.insert("ex10", null, value)
                handler.post {
                    edit.setText(str)
                }
                sleep(10000)
            }
        }
    }

}