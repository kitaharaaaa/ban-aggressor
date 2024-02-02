package com.hyperhoop.song_playback.presentation

import android.content.Context
import android.content.Intent
import com.kitahara.common.enums.SongPlaybackActions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ServiceManagement @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) {
    private val serviceBaseIntent: ((SongPlaybackActions) -> Intent) = {
        Intent(applicationContext, SongPlaybackService::class.java).apply {
            action = it.toString()

            applicationContext.startService(this)
        }
    }

    fun launchService() {
        serviceBaseIntent(SongPlaybackActions.Start)
    }

    fun stopService() {
        serviceBaseIntent(SongPlaybackActions.Stop)
    }
}