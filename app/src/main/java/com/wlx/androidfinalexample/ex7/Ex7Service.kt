package com.wlx.androidfinalexample.ex7

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class Ex7Service : Service() {
    private val TAG = "Ex7Service"
    inner class Ex7Thread : Thread() {
        override fun run() {
            try {
                while (!isInterrupted) {
                    val date = Date()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
                    val string = dateFormat.format(date)
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(
                            applicationContext,
                            "hello \n $string",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    sleep(4000)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private lateinit var thread: Ex7Thread
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand: ")
        thread = Ex7Thread()
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
        thread.interrupt()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}