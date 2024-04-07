package com.kitahara.home.domain.repository

interface CurrentSongStatusUpdateRepository {
    suspend fun updateCurrentState(
        id: String?,
        artistName: String?,
        trackName: String?,
    )

    suspend fun updatePlayingParameter(isPlaying: Boolean)
}