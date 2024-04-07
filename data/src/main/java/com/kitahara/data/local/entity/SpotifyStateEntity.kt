package com.kitahara.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("spotify_state_table")
data class SpotifyStateEntity(
    @PrimaryKey(false)
    val id: Int = 0,
    @ColumnInfo("author_name")
    val authorName: String?,
    val track: String?,
    @ColumnInfo("cover_uri")
    val coverUri: String?,
    @ColumnInfo("currently_playing")
    val currentlyPlaying: Boolean,
)