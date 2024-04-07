package com.kitahara.data.remote.song_cover.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ExternalIds(
    @SerialName("isrc")
    val isrc: String? = null // FR2X41897720
)