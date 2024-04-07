package com.kitahara.home.domain.repository

import com.kitahara.common.song_state.CurrentSongData
import kotlinx.coroutines.flow.Flow

interface CurrentSongInfoSource {
    fun getSongState(): Flow<CurrentSongData>
    fun getIsPlaying(): Flow<Boolean>
    fun getSongCover(): Flow<String?>
}