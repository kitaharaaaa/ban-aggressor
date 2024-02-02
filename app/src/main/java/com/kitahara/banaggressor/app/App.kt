package com.kitahara.banaggressor.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.kitahara.banaggressor.R
import com.kitahara.common.enums.ChannelsEnum
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val channel = NotificationChannel(
            ChannelsEnum.SongPlayback.channelId,
            getString(R.string.app_name),
            ChannelsEnum.SongPlayback.importance
        )

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
    }
}