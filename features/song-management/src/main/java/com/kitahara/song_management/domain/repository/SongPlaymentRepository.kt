package com.kitahara.song_management.domain.repository

interface SongPlaymentRepository {
    suspend fun next()
    suspend fun startSession()
    suspend fun pauseSession()
    suspend fun back()
}