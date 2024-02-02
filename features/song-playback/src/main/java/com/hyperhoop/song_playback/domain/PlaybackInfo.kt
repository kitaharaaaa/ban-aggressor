package com.hyperhoop.song_playback.domain

import kotlinx.coroutines.flow.StateFlow

interface PlaybackInfo {
    val artistFlow: StateFlow<String?>
}