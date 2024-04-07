package com.kitahara.home.domain.repository

interface CurrentSongStatusUpdateRepository {
    fun updateCurrentState(
        id: String?,
        artistName: String?,
        trackName: String?,
    )

    fun updatePlayingParameter(isPlaying: Boolean)
}