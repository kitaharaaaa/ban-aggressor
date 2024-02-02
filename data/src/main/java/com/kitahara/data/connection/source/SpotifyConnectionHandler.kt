package com.kitahara.data.connection.source

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.kitahara.data.auth.SpotifyAuthImpl.Companion.REDIRECT_URI
import com.kitahara.data.general_data.PlayerGeneral.connectionParams
import com.kitahara.data.general_data.PlayerGeneral.mSpotifyAppRemote
import com.kitahara.data.general_data.PlayerGeneral.playerApi
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.error.CouldNotFindSpotifyApp
import com.spotify.android.appremote.api.error.NotLoggedInException
import com.spotify.android.appremote.api.error.UserNotAuthorizedException
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


//todo write interface and use this class as data source
class SpotifyConnectionHandler @Inject constructor(
    @ActivityContext private val applicationContext: Context //todo check whether activity context needed
) {
    fun buildConnection(toastCallback: (String) -> Unit) {
        val isSpotifyInstalled = isSpotifyInstalled()
        Log.e(TAG, "buildConnection: isSpotifyInstalled = $isSpotifyInstalled")

        if (isSpotifyInstalled) {
            toastCallback("Spotify installed")
            connectionParams = ConnectionParams.Builder(SPOTIFY_CLIENT_ID)
                .showAuthView(true)
                .setRedirectUri(REDIRECT_URI)
                .build()
        } else toastCallback("Spotify Not Installed")
    }

    fun startSpotifyConnection(pushToast: (String) -> Unit) {
        stopSpotifyConnection()

        SpotifyAppRemote.connect(
            applicationContext,
            connectionParams,
            spotifyConnectionListener {
                pushToast(it)
            }
        )
    }

    private fun spotifyConnectionListener(onException: (String) -> Unit) =
        object : Connector.ConnectionListener {
            val TAG = "SpotifyListener"
            override fun onConnected(spotifyAppRemote: SpotifyAppRemote?) {
                mSpotifyAppRemote = spotifyAppRemote
                Log.e(TAG, "onConnected: $spotifyAppRemote")

                playerApi = spotifyAppRemote?.playerApi
            }

            override fun onFailure(error: Throwable?) {
                Log.e(TAG, "onFailure: $error")

                //throw in each case event
                when (error) {
                    is NotLoggedInException -> {
                        // Show login button and trigger the login flow from auth library when clicked

                        onException("Not Logged")
                    }

                    is UserNotAuthorizedException -> {
                        // Show there no such user
                        onException("Not Authorized")
                    }

                    is CouldNotFindSpotifyApp -> {
                        // Show button to download Spotify
                        onException("Spotify is not installed")
                    }

                    else -> {
                        // Show internet exception
                        onException("Internet trouble")
                    }
                }
            }
        }

    fun stopSpotifyConnection() {
        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
    }

    private fun isSpotifyInstalled(): Boolean = try {
        applicationContext.packageManager.getPackageInfo("com.spotify.music", 0)

        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }

    companion object {
        const val TAG = "SpotifyConnectionHandler"
        const val SPOTIFY_CLIENT_ID = "e28855579e6b4e499adf252d742d7df3"
    }
}