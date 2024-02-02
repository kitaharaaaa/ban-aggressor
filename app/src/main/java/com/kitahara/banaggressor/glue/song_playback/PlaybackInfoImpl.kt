package com.kitahara.banaggressor.glue.song_playback

import com.hyperhoop.song_playback.domain.PlaybackInfo
import com.kitahara.data.general_data.PlayerGeneral.artistNameFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class PlaybackInfoImpl@Inject constructor() : PlaybackInfo {
    override val artistFlow: StateFlow<String?> = artistNameFlow.asStateFlow()
}