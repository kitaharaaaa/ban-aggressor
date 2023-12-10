package com.kitahara.data.connection.source

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.kitahara.data.general_data.PlayerGeneral.playerApiState
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.error.CouldNotFindSpotifyApp
import com.spotify.android.appremote.api.error.NotLoggedInException
import com.spotify.android.appremote.api.error.UserNotAuthorizedException
import javax.inject.Inject

class SpotifyConnectionHandler @Inject constructor(
    /*@ActivityContext */private val applicationContext: Context //todo check whether activity context needed
) {

    private var connectionParams: ConnectionParams? = null
    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    fun buildConnection(toastCallback: (String) -> Unit) {
        val isSpotifyInstalled = isSpotifyInstalled()
        Log.e(TAG, "buildConnection: isSpotifyInstalled = $isSpotifyInstalled")

        if (isSpotifyInstalled) {
            toastCallback("Spotify detected")
            connectionParams = ConnectionParams.Builder("e28855579e6b4e499adf252d742d7df3")
                /*.setRedirectUri("https://example.com/callback")*/
                .showAuthView(true)
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

                playerApiState.value = spotifyAppRemote?.playerApi
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
    }
}