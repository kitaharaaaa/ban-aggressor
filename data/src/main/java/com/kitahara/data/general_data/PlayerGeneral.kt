package com.kitahara.data.general_data

import com.spotify.android.appremote.api.PlayerApi
import kotlinx.coroutines.flow.MutableStateFlow

object PlayerGeneral {
    val playerApiState = MutableStateFlow<PlayerApi?>(null)

    val playerApi get() = playerApiState.value

    var isSpotifyPlaying = false
}