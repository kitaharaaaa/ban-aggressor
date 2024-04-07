package com.kitahara.data.remote.song_cover.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Image(
    @SerialName("height")
    val height: Int? = null, // 640
    @SerialName("url")
    val url: String? = null, // https://i.scdn.co/image/ab67616d0000b2730112cd9fcaf80cfa69d3b2e2
    @SerialName("width")
    val width: Int? = null // 640
)