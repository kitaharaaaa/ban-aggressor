package com.kitahara.data.artist_checker.source

import android.util.Log
import com.kitahara.data.general_data.PlayerGeneral
import com.kitahara.data.general_data.PlayerGeneral.artistName
import com.kitahara.data.general_data.PlayerGeneral.isSpotifyPlaying

class ArtistNameDataSource {

    fun retrieveArtisName() {
        val subscriptionTag = "SubscriptionTag"

        PlayerGeneral.playerApi?.subscribeToPlayerState()
            ?.setEventCallback {
                isSpotifyPlaying = !it.isPaused
                Log.e(
                    subscriptionTag,
                    "event came: \ntrack = ${it.track} \nfrom \nartist = ${it.track.artist.name}"
                )

                artistName = it.track.artist.name
            }
            ?.setErrorCallback { throwable ->
                // =( =( =(
            }
    }
}