package com.kitahara.song_management.presentation.playback_state

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaybackStateBroadcastReceiver @Inject constructor() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "com.spotify.music.playbackstatechanged" -> {
                val isPlaying = intent.getBooleanExtra("playing", false)

                val playingSecond = intent.getIntExtra("playbackPosition", 0)

                val sendTime = intent.getLongExtra("timeSent", 0)

                Log.e(
                    TAG,
                    "isPlaying = $isPlaying \n playingSecond = $playingSecond\n sendDelay = ${System.currentTimeMillis() - sendTime}\n"
                )
            }

            "com.spotify.music.metadatachanged" -> {
                val trackId = intent.getStringExtra("id")
                val artistName = intent.getStringExtra("artist")
                val albumName = intent.getStringExtra("album")
                val trackName = intent.getStringExtra("track")
                val trackLengthInSec = intent.getIntExtra("length", 0)

                Log.e(
                    TAG,
                    "length = $trackLengthInSec \n artist = $artistName\n track = $trackName\n"
                )
            }

            else -> {
                Log.e("QueueChanged", intent?.action.toString())
            }
        }
    }

    companion object {
        const val TAG = "SpotifyBroadcast"
    }
}