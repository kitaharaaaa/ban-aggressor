package com.kitahara.banaggressor.glue.log_in.repos

import com.kitahara.data.local.dao.SpotifyStateDao
import com.kitahara.data.local.entity.SpotifyStateEntity
import com.kitahara.data.remote.song_cover.SongCoverDataSource
import com.kitahara.home.domain.repository.CurrentSongStatusUpdateRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentSongStatusUpdateImpl @Inject constructor(
    private val stateDao: SpotifyStateDao,
    private val songCoverDataSource: SongCoverDataSource
) : CurrentSongStatusUpdateRepository {
    override fun updateCurrentState(id: String?, artistName: String?, trackName: String?) {
        CoroutineScope(IO).launch {
            stateDao.upsert(
                SpotifyStateEntity(
                    authorName = artistName,
                    track = trackName,
                    coverUri = null,
                    currentlyPlaying = true
                )
            )

            id?.removePrefix("spotify:track:")?.let {
                val coverUri = songCoverDataSource.getSongCover(it) ?: return@let

                stateDao.updateCoverUri(coverUri)
            }
        }
    }

    override fun updatePlayingParameter(isPlaying: Boolean) {
        CoroutineScope(IO).launch {
            stateDao.updateIsPlaying(isPlaying)
        }
    }
}