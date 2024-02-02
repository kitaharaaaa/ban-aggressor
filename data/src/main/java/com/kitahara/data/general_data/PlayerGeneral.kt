package com.kitahara.data.general_data

import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.PlayerApi
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.coroutines.flow.MutableStateFlow

object PlayerGeneral {
    private val playerApiState = MutableStateFlow<PlayerApi?>(null)
    val artistNameFlow = MutableStateFlow<String?>(null)

    var connectionParams: ConnectionParams? = null
    var mSpotifyAppRemote: SpotifyAppRemote? = null

    var artistName
        get() = artistNameFlow.value
        set(value) {
            artistNameFlow.value = value
        }

    var playerApi
        get() = playerApiState.value
        set(apiInstance) {
            playerApiState.value = apiInstance
        }

    var isSpotifyPlaying = false

}