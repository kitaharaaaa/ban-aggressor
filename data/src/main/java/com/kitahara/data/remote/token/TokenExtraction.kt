package com.kitahara.data.remote.token

import android.content.Intent
import android.util.Log
import com.kitahara.data.remote.token.entity.TokenData
import com.spotify.sdk.android.auth.AuthorizationResponse
import javax.inject.Inject

class TokenExtraction @Inject constructor() {
    fun getTokenData(intent: Intent?): TokenData? {
        Log.e("onNewIntent", "triggered")
        val uri = intent?.data

        Log.e("Uri", uri.toString())
        return uri?.let {
            val response: AuthorizationResponse = AuthorizationResponse.fromUri(uri)

            return when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    Log.e("AuthResult", "token = " + response.accessToken)

                    val expiresIn = response.expiresIn
                    val token = response.accessToken

                    if (token.isNullOrBlank())
                        throw Exception("Invalid token format")

                    TokenData(
                        token, expiresIn
                    )
                }

                else -> {
                    Log.e("AuthResult", "else ")

                    null
                }
            }
        }
    }
}