package com.kitahara.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("spotify_state_table")
data class SpotifyStateEntity(
    @PrimaryKey(false)
    val id: Int = 0,
    val author: String,
    val track: String,
    @ColumnInfo("currently_playing")
    val currentlyPlaying: Boolean,
)