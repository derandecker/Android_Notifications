package com.derandecker.androidnotifications.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import com.derandecker.androidnotifications.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "$packageName.deranchannel"


        btn_get_notification.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "New Notification Channel - Deran Decker"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val description = "Android Notifications Demo - Deran Decker"

                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description

                val notificationBuilder = NotificationCompat.Builder(this, channelId)

            }
        }
    }
}
