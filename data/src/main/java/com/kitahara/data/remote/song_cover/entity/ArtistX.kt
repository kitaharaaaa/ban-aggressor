package com.kitahara.data.remote.song_cover.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class ArtistX(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = ExternalUrls(),
    @SerialName("href")
    val href: String? = "", // https://api.spotify.com/v1/artists/3vpYUXugRgKGTneMdO8kI7
    @SerialName("id")
    val id: String? = "", // 3vpYUXugRgKGTneMdO8kI7
    @SerialName("name")
    val name: String? = "", // Кашляючий Ед
    @SerialName("type")
    val type: String? = "", // artist
    @SerialName("uri")
    val uri: String? = "" // spotify:artist:3vpYUXugRgKGTneMdO8kI7
)