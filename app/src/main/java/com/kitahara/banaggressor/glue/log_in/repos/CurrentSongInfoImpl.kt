package com.kitahara.banaggressor.glue.log_in.repos

import android.util.Log
import com.kitahara.common.song_state.CurrentSongData
import com.kitahara.data.local.dao.SpotifyStateDao
import com.kitahara.home.domain.repository.CurrentSongInfoSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentSongInfoImpl @Inject constructor(
    private val dao: SpotifyStateDao
) : CurrentSongInfoSource {
    override fun getSongState(): Flow<CurrentSongData> {
        CoroutineScope(Main).launch {
            dao.getSpotifyBaseDataState().collect {
                Log.e("UpdatedData", it.toString())
            }
        }
        return dao.getSpotifyBaseDataState().map { spotifyState ->
            CurrentSongData(
                authorName = spotifyState.authorName,
                track = spotifyState.track
            )
        }
    }

    override fun getIsPlaying(): Flow<Boolean> {
        return dao.getIsPlaying()
    }

    override fun getSongCover(): Flow<String?> {
        return dao.getSongCoverUri()
    }
}