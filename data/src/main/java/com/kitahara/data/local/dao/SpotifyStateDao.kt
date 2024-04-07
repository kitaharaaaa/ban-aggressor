package com.kitahara.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kitahara.data.local.entity.SpotifyStateEntity

@Dao
interface SpotifyStateDao {
    @Upsert
    suspend fun upsert(entity: SpotifyStateEntity)

    @Query("SELECT * FROM spotify_state_table WHERE id = 0")
    fun getSpotifyFullState(): SpotifyStateEntity

    @Query("UPDATE spotify_state_table SET cover_uri = :uri WHERE id = 0")
    fun updateCoverUri(uri: String)

    @Query("UPDATE spotify_state_table SET currently_playing = :isPlaying WHERE id = 0")
    fun updateIsPlaying(isPlaying: Boolean)
}