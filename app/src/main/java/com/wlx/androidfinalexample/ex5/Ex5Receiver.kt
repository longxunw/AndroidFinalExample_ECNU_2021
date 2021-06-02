package com.wlx.androidfinalexample.ex5

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.wlx.androidfinalexample.R

class Ex5Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val to_B = Intent(context, Ex5BActivity::class.java)
        val pi = PendingIntent.getActivity(context, 0, to_B, 0)
        val notification = NotificationCompat.Builder(context, "normal")
            .setContentTitle("这是Ex5发出的通知")
            .setContentText(
                "参数1：${intent.getIntExtra("data", 0)}" +
                        " 参数2：${intent.getStringExtra("string")}"
            )
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.ic_launcher_foreground
                )
            )
            .setContentIntent(pi)
            .build()
        manager.notify(1, notification)
    }
}