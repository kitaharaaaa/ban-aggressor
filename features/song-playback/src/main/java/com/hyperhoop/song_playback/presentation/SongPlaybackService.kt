package com.hyperhoop.song_playback.presentation

import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.kitahara.common.enums.ChannelsEnum
import com.kitahara.common.enums.SongPlaybackActions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/*
* to call service you need create intent and pass action from SongPlaybackActions enum

        Intent(applicationContext, SongPlaybackService::class.java).apply {
            action = SongPlaybackActions.Start.toString()

            startService(this)
        }
* */

@AndroidEntryPoint
class SongPlaybackService@Inject constructor() : LifecycleService() {

   /* @Inject todo uncomment
    lateinit var playbackInfo: PlaybackInfo*/

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       /* lifecycleScope.launch {
            playbackInfo.artistFlow.collect {
                Log.e(TAG, "data = $it")
                pushSongStatusNotification(it)
            }
        }*/

        when (intent?.action) {
            SongPlaybackActions.Start.toString() -> pushSongStatusNotification("Start play songs")

            SongPlaybackActions.Stop.toString() -> stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun pushSongStatusNotification(s: String?) {

        startForeground(
            1,
            baseNotification
                .setContentText(s) //todo make dynamic name + customize notification
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

