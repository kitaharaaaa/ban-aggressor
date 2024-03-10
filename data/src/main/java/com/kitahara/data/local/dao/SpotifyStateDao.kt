package com.kitahara.data.local.dao

import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.data.local.entity.SpotifyStateEntity
import kotlinx.coroutines.flow.Flow

interface SpotifyStateDao {
    @Upsert
    suspend fun upsert(entity: SpotifyStateEntity)

    @Query("SELECT * FROM spotify_state_table WHERE id = 0")
    fun getSpotifyFullState(): SpotifyStateEntity

    @Query("SELECT s.track FROM spotify_state_table s WHERE id = 0")
    suspend fun isPlaying(): Flow<Boolean>

    @Query("SELECT s.author FROM spotify_state_table s WHERE id = 0")
    suspend fun getAuthor(): Flow<String>
}