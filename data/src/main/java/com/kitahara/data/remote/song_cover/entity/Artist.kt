package com.kitahara.data.remote.song_cover.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Artist(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = null,
    @SerialName("href")
    val href: String? = null, // https://api.spotify.com/v1/artists/3vpYUXugRgKGTneMdO8kI7
    @SerialName("id")
    val id: String? = null, // 3vpYUXugRgKGTneMdO8kI7
    @SerialName("name")
    val name: String? = null, // Кашляючий Ед
    @SerialName("type")
    val type: String? = null, // artist
    @SerialName("uri")
    val uri: String? = null // spotify:artist:3vpYUXugRgKGTneMdO8kI7
)