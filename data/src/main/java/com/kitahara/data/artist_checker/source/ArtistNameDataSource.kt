package com.kitahara.data.artist_checker.source

import android.util.Log
import com.kitahara.data.general_data.PlayerGeneral
import com.kitahara.data.general_data.PlayerGeneral.isSpotifyPlaying

class ArtistNameDataSource {

    fun retrieveArtisName() {
        val subscriptionTag = "SubscriptionTag"
        PlayerGeneral.playerApi?.subscribeToPlayerState()
            ?.setEventCallback {
                isSpotifyPlaying = !it.isPaused
                Log.e(subscriptionTag, "event came: track = ${it.track}")

                it.track.artist.name //emit
                //is possible observe pending result
            }
            ?.setErrorCallback { throwable ->
                // =( =( =(
            }
    }
}