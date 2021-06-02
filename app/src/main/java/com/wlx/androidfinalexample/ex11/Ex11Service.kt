package com.wlx.androidfinalexample.ex11

import android.app.Service
import android.content.Intent
import android.os.IBinder

class Ex11Service : Service() {

    private lateinit var thread: Ex11Thread
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread = Ex11Thread()
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        thread.interrupt()
    }

    inner class Ex11Thread : Thread() {
        override fun run() {
            try {
                while (!isInterrupted) {
                    val random = (0..100).random()
                    val intent = Intent("com.wlx.androidfinalexample.ex11")
                    intent.setPackage(packageName)
                    intent.putExtra("random", random)
                    sendBroadcast(intent)
                    sleep(3000)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}