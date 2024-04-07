package com.kitahara.home.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.core.content.ContextCompat
import com.kitahara.home.domain.repository.CurrentSongStatusUpdateRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaybackStateBroadcastReceiver @Inject constructor() : BroadcastReceiver() {
    @Inject
    lateinit var currentSongStatusRepository: CurrentSongStatusUpdateRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            "com.spotify.music.playbackstatechanged" -> {
                val isPlaying = intent.getBooleanExtra("playing", false)

                val playbackPosition = intent.getIntExtra("playbackPosition", 0)

                val sendTime = intent.getLongExtra("timeSent", 0)

                Log.e(
                    TAG,
                    "isPlaying = $isPlaying \n playingSecond = $playbackPosition\n sendDelay = ${System.currentTimeMillis() - sendTime}\n"
                )

                currentSongStatusRepository.updatePlayingParameter(isPlaying)
            }

            "com.spotify.music.metadatachanged" -> {
                val trackId = intent.getStringExtra("id")
                val artistName = intent.getStringExtra("artist")
                val trackName = intent.getStringExtra("track")

                Log.e(
                    TAG,
                    "trackId = $trackId \n artist = $artistName\n track = $trackName\n"
                )

                currentSongStatusRepository.updateCurrentState(
                    trackId,
                    artistName, trackName
                )
            }

            else -> {
                Log.e("QueueChanged", intent?.action.toString())
            }
        }
    }

    companion object {
        const val TAG = "SpotifyBroadcast"

        fun registerBroadcast(context: Context) {
            val intentFilter = IntentFilter().apply {
                addAction("com.spotify.music.playbackstatechanged")
                addAction("com.spotify.music.metadatachanged")
                addAction("com.spotify.music.queuechanged")
            }

            ContextCompat.registerReceiver(
                context,
                PlaybackStateBroadcastReceiver(),
                intentFilter,
                ContextCompat.RECEIVER_EXPORTED,
            )
        }
    }
}