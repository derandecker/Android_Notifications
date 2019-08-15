package com.derandecker.androidnotifications.ui

import android.app.Notification.Action.SEMANTIC_ACTION_THUMBS_UP
import android.app.Notification.DEFAULT_ALL
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import com.derandecker.androidnotifications.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_ID = 99
        const val INTENT_STRING_KEY = "INTENT_STRING_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "$packageName.deranchannel"

        val notificationFullscreenIntent = Intent(this, FullscreenNotificationActivity::class.java)
        notificationFullscreenIntent.putExtra(INTENT_STRING_KEY, "Opened from Notification Shade")

        val pendingContentIntent = PendingIntent.getActivity(this, 0,
            notificationFullscreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)


        btn_get_notification.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "New Notification Channel - Deran Decker"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val description = "Android Notifications Demo - Deran Decker"

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description

            }

            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationManager.IMPORTANCE_LOW)
                .setContentTitle("High Importance")
                .setContentText("High Importance Notification - Deran Decker")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setColor(Color.GREEN)
                .setDefaults(DEFAULT_ALL)
                .setContentIntent(pendingContentIntent)
                .addAction(R.drawable.abc_ic_menu_cut_mtrl_alpha,
                    "Open", pendingContentIntent)

            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())

            val intent = Intent(this, FullscreenNotificationActivity::class.java)
            intent.putExtra(INTENT_STRING_KEY, "Notification created")
            startActivity(intent)
        }
    }
}
