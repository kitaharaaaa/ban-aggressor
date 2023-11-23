package com.kitahara.song_management.domain.repository

interface SongCheckerRepository {
    suspend fun isAggressor(): Boolean
    suspend fun isCurrentlyPlaying(): Boolean
}