package com.kitahara.data.auth

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kitahara.data.connection.source.SpotifyConnectionHandler.Companion.SPOTIFY_CLIENT_ID
import com.spotify.sdk.android.auth.AccountsQueryParameters.CLIENT_ID
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject


class SpotifyAuthImpl @Inject constructor(
    @ActivityContext private val context: Context
) {
    operator fun invoke() {

        val builder =
            AuthorizationRequest.Builder(SPOTIFY_CLIENT_ID, AuthorizationResponse.Type.TOKEN, REDIRECT_URI)

        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()

        AuthorizationClient.openLoginActivity(context as Activity, REQUEST_CODE, request)
    }

    fun getToken(intent: Intent?): String? {
       val request =  AuthorizationClient.getResponse(REQUEST_CODE, intent)

        Log.e("Tag", request.type.toString())
        return request.accessToken
    }

    companion object {
        val REQUEST_CODE = 1337
        val REDIRECT_URI = "https://example.com/callback"
    }
}