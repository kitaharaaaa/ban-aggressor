package com.kitahara.data.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

//Spotify token extraction
class SpotifyAuthImpl @Inject constructor(
    @ActivityContext private val context: Context
) {
    operator fun invoke() {
        val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
            SPOTIFY_CLIENT_ID,
            AuthorizationResponse.Type.TOKEN,
            REDIRECT_URI

        )
            .setScopes(arrayOf("streaming"))
            .setShowDialog(true)

        AuthorizationClient.openLoginInBrowser(context as Activity, builder.build())
    }

    fun getTokenFromIntent(intent: Intent?): String? {
        Log.e("onNewIntent", "triggered")
        val uri = intent?.data

        Log.e("Uri", uri.toString())
        return uri?.let {
            val response: AuthorizationResponse = AuthorizationResponse.fromUri(uri)

            return when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    Log.e("AuthResult", "token = " + response.accessToken)

                    response.accessToken
                }

                else -> {
                    Log.e("AuthResult", "else ")

                    null
                }
            }
        }
    }

    companion object {
        val REDIRECT_URI = "yourcustomprotocol://callback"

        const val SPOTIFY_CLIENT_ID = "e28855579e6b4e499adf252d742d7df3"
    }
}