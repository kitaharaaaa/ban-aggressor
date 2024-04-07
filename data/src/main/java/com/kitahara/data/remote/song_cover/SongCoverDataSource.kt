package com.kitahara.data.remote.song_cover

import android.util.Log
import com.kitahara.data.local.dao.TokenDao
import com.kitahara.data.remote.song_cover.entity.SongCoverData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.path
import javax.inject.Inject

class SongCoverDataSource @Inject constructor(
    private val ktorClient: HttpClient,
    private val tokenDao: TokenDao
) {
    private val tag = "SongCoverDataSource"
    suspend fun getSongCover(artistSpotifyId: String): String? {
        val token = tokenDao.getToken()
        val body = try {
            val response = ktorClient.get {
                url {
                    Log.e("SongCoverDataSource", "query with id = $artistSpotifyId")

                    /*https://api.spotify.com/v1/tracks/5JosnbNvjsZEnmjP0AIdSo*/
                    protocol = URLProtocol.HTTPS
                    host = "api.spotify.com"
                    path("v1/tracks/$artistSpotifyId")

                    header(
                        "Authorization",
                        "Bearer " + token.toString()
                    )
                }
            }.body<SongCoverData>()

            response.album?.images?.get(0)?.url
        } catch (e: Exception) {
            Log.e(tag, e.message.toString())
            null
        }

        Log.e(tag, body.toString())

        return body
    }
}