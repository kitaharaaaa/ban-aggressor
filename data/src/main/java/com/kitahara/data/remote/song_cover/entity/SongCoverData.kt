package com.kitahara.data.remote.song_cover.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class SongCoverData(
    @SerialName("album")
    val album: Album? = Album(),
    @SerialName("artists")
    val artists: List<ArtistX>? = listOf(),
    @SerialName("available_markets")
    val availableMarkets: List<String>? = listOf(),
    @SerialName("disc_number")
    val discNumber: Int? = 0, // 1
    @SerialName("duration_ms")
    val durationMs: Int? = 0, // 163636
    @SerialName("explicit")
    val explicit: Boolean? = false, // false
    @SerialName("external_ids")
    val externalIds: ExternalIds? = ExternalIds(),
    @SerialName("external_urls")
    val externalUrls: ExternalUrls? = ExternalUrls(),
    @SerialName("href")
    val href: String? = "", // https://api.spotify.com/v1/tracks/5JosnbNvjsZEnmjP0AIdSo
    @SerialName("id")
    val id: String? = "", // 5JosnbNvjsZEnmjP0AIdSo
    @SerialName("is_local")
    val isLocal: Boolean? = false, // false
    @SerialName("name")
    val name: String? = "", // Завтра
    @SerialName("popularity")
    val popularity: Int? = 0, // 23
    @SerialName("preview_url")
    val previewUrl: String? = "", // https://p.scdn.co/mp3-preview/fce2e3245a6e47438e29f107b5918a3073b6ee84?cid=cfe923b2d660439caf2b557b21f31221
    @SerialName("track_number")
    val trackNumber: Int? = 0, // 8
    @SerialName("type")
    val type: String? = "", // track
    @SerialName("uri")
    val uri: String? = "" // spotify:track:5JosnbNvjsZEnmjP0AIdSo
)