package com.hyperhoop.song_playback.presentation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.kitahara.common.enums.ChannelsEnum
import com.kitahara.common.enums.SongPlaybackActions

/*
* to call service you need create intent and pass action from SongPlaybackActions enum

        Intent(applicationContext, SongPlaybackService::class.java).apply {
            action = SongPlaybackActions.Start.toString()

            startService(this)
        }
* */
class SongPlaybackService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            SongPlaybackActions.Start.toString() -> startSongPlayback()

            SongPlaybackActions.Update.toString() -> updateNotification()

            SongPlaybackActions.Stop.toString() -> stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun updateNotification() {
        TODO("Not yet implemented")
    }

    private fun startSongPlayback() {
        startForeground(
            1,
            baseNotification
                .setContentText("Bring Me The Horizone") //todo make dynamic name + customize notification
                .setContentInfo("якийсь вестерн чи шо")
                .build()
        )
    }

    private val baseNotification = NotificationCompat.Builder(
        this,
        ChannelsEnum.SongPlayback.channelId
    ).apply {
        setSmallIcon(com.kitahara.theme.R.drawable.baseline_music_indicator)
        setContentTitle(ChannelsEnum.SongPlayback.notificationName)
    }
}

