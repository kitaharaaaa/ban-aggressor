package com.kitahara.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.data.local.entity.SpotifyStateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpotifyStateDao {
    @Upsert
    suspend fun upsert(entity: SpotifyStateEntity)

    @Query("SELECT * FROM spotify_state_table WHERE id = 0")
    fun getSpotifyFullState(): SpotifyStateEntity

    @Query("SELECT s.currently_playing FROM spotify_state_table s WHERE id = 0")
    fun isPlaying(): Flow<Boolean>

    @Query("SELECT s.author FROM spotify_state_table s WHERE id = 0")
    fun getAuthor(): Flow<String>
}