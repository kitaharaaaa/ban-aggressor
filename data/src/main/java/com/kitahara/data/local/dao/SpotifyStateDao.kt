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
    fun getSpotifyBaseDataState(): Flow<SpotifyStateEntity>

    @Query("UPDATE spotify_state_table SET cover_uri = :uri WHERE id = 0")
    fun updateCoverUri(uri: String)

    @Query("SELECT cover_uri FROM spotify_state_table WHERE id = 0")
    fun getSongCoverUri(): Flow<String?>
    @Query("SELECT currently_playing FROM spotify_state_table WHERE id = 0")
    fun getIsPlaying(): Flow<Boolean>

    @Query("UPDATE spotify_state_table SET currently_playing = :isPlaying WHERE id = 0")
    fun updateIsPlaying(isPlaying: Boolean)
}