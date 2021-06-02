package com.wlx.androidfinalexample.ex11

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.wlx.androidfinalexample.MyDatabaseHelper
import com.wlx.androidfinalexample.R

class Ex11Activity : AppCompatActivity() {
    private lateinit var edit: EditText
    private lateinit var dbHelper: MyDatabaseHelper
    private lateinit var receiver: Ex11Receiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex11)
        edit = findViewById(R.id.edit)
        dbHelper = MyDatabaseHelper(this, "Exercise.db", 2)
        val start: Button = findViewById(R.id.start)
        start.setOnClickListener {
            val intent = Intent(this, Ex11Service::class.java)

            startService(intent)
        }
        val stop: Button = findViewById(R.id.stop)
        stop.setOnClickListener {
            val intent = Intent(this, Ex11Service::class.java)
            stopService(intent)
            val db = dbHelper.writableDatabase
            var all = 0
            db.rawQuery("select * from ex11", null).apply {
                while (moveToNext()) {
                    all += getInt(getColumnIndex("num"))
                }
                close()
            }
            edit.setText(all.toString())
        }
        receiver = Ex11Receiver(dbHelper, edit)
        val filter = IntentFilter().apply {
            addAction("com.wlx.androidfinalexample.ex11")
        }
        registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }
}