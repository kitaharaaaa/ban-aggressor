package com.kitahara.home.domain.repository

//TODO add functions + implenment
interface CurrentSongStatusRepository {
    suspend fun isPlaying()
}