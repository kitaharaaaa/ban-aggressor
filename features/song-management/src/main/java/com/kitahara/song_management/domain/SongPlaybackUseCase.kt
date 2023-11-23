package com.kitahara.song_management.domain

import com.kitahara.song_management.domain.repository.SongCheckerRepository
import com.kitahara.song_management.domain.repository.SongPlaymentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SongPlaybackUseCase @Inject constructor(
    private val songChecker: SongCheckerRepository,
    private val songPlayment: SongPlaymentRepository
) {
    private var _isButtonsClickable = MutableSharedFlow<Boolean>()
    private val isButtonClickable get() = _isButtonsClickable.asSharedFlow()

    private var isPlaying = false

    init {
        CoroutineScope(Dispatchers.IO).launch {
            isPlaying = songChecker.isCurrentlyPlaying()

            _isButtonsClickable.emit(true)
        }
    }

    suspend fun playOrPause() {
        if (isPlaying)
            songPlayment.pauseSession()
        else
            songPlayment.startSession()

        isPlaying = !isPlaying
    }

    suspend fun next() {
        songPlayment.next()

    }

    suspend fun back() {

    }
}