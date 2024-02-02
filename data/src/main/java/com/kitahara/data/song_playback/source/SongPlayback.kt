package com.kitahara.data.song_playback.source

import com.kitahara.data.general_data.PlayerGeneral.isSpotifyPlaying
import com.kitahara.data.general_data.PlayerGeneral.playerApi
import javax.inject.Inject

//todo move into implementations
class SongPlayback@Inject constructor() {

    fun next() = playerApi?.skipNext()

    fun previous() = playerApi?.skipPrevious()

    fun pauseOrResume() {
        playerApi?.let {
            if (isSpotifyPlaying) it.pause() else it.resume()
        }
    }
}