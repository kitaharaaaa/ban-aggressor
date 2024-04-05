package com.kitahara.data.remote.auth

import android.app.Activity
import android.content.Context
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

//Spotify token extraction
class SpotifyAuth @Inject constructor(
    @ActivityContext private val context: Context
) {
    fun callInitWindow() {
        val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
            SPOTIFY_CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            REDIRECT_URI

        )
            .setScopes(arrayOf("streaming"))
            .setShowDialog(true)

        AuthorizationClient.openLoginInBrowser(context as Activity, builder.build())
    }

    companion object {
        const val REDIRECT_URI = "yourcustomprotocol://callback"

        const val SPOTIFY_CLIENT_ID = "e28855579e6b4e499adf252d742d7df3"
    }
}