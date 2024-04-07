package com.kitahara.data.remote.song_cover.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class Album(
    @SerialName("album_type")
    val albumType: String? = "", // album
    @SerialName("artists")
    val artists: List<Artist>? = listOf(),
    @SerialName("available_markets")
    val availableMarkets: List<String>? = listOf(),
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = ExternalUrls(),
    @SerialName("href")
    val href: String? = "", // https://api.spotify.com/v1/albums/6caZ8JF16U4OLw3Ocl6HJg
    @SerialName("id")
    val id: String? = "", // 6caZ8JF16U4OLw3Ocl6HJg
    @SerialName("images")
    val images: List<Image>? = listOf(),
    @SerialName("name")
    val name: String? = "", // Переклад
    @SerialName("release_date")
    val releaseDate: String? = "", // 2018-06-21
    @SerialName("release_date_precision")
    val releaseDatePrecision: String? = "", // day
    @SerialName("total_tracks")
    val totalTracks: Int? = 0, // 11
    @SerialName("type")
    val type: String? = "", // album
    @SerialName("uri")
    val uri: String? = "" // spotify:album:6caZ8JF16U4OLw3Ocl6HJg
)