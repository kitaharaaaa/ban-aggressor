package com.kitahara.banaggressor.glue.log_in.repos

import com.kitahara.data.local.dao.SpotifyStateDao
import com.kitahara.data.local.entity.SpotifyStateEntity
import com.kitahara.data.remote.song_cover.SongCoverDataSource
import com.kitahara.home.domain.repository.CurrentSongStatusUpdateRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrentSongStatusUpdateImpl @Inject constructor(
    private val stateDao: SpotifyStateDao,
    private val songCoverDataSource: SongCoverDataSource
) : CurrentSongStatusUpdateRepository {
    override suspend fun updateCurrentState(id: String?, artistName: String?, trackName: String?) {
        withContext(IO) {
            stateDao.upsert(
                SpotifyStateEntity(
                    id = 0,
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

    override suspend fun updatePlayingParameter(isPlaying: Boolean) {
        withContext(IO) {
            stateDao.updateIsPlaying(isPlaying)
        }
    }
}