package com.kitahara.home.presentation

import androidx.lifecycle.ViewModel
import com.kitahara.home.domain.repository.CurrentSongInfoSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentSongInfoSource: CurrentSongInfoSource,
) : ViewModel() {
    fun getSongInfo() = currentSongInfoSource.getSongState()
    fun getIsPlayingState() = currentSongInfoSource.getIsPlaying()
    fun getSongCoverUri() = currentSongInfoSource.getSongCover()
}
