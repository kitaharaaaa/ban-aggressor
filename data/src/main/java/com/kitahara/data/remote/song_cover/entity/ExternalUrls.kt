package com.kitahara.data.remote.song_cover.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ExternalUrls(
    @SerialName("spotify")
    val spotify: String? = null // https://open.spotify.com/artist/3vpYUXugRgKGTneMdO8kI7
)