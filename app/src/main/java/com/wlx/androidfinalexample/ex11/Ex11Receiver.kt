package com.wlx.androidfinalexample.ex11

import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.widget.EditText
import com.wlx.androidfinalexample.MyDatabaseHelper

class Ex11Receiver(val dbHelper: MyDatabaseHelper, val editText: EditText) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val random = intent.getIntExtra("random", 0)
        editText.setText(random.toString())
        val db = dbHelper.writableDatabase
        val value = ContentValues().apply {
            put("num", random)
        }
        db.insert("ex11", null, value)
    }
}