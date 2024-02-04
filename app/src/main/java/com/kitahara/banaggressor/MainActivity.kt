package com.kitahara.banaggressor

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.kitahara.banaggressor.ui.SongManagement
import com.kitahara.banaggressor.ui.theme.BanAggressorTheme
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.PlayerApi
import com.spotify.android.appremote.api.SpotifyAppRemote
import com.spotify.android.appremote.api.error.CouldNotFindSpotifyApp
import com.spotify.android.appremote.api.error.NotLoggedInException
import com.spotify.android.appremote.api.error.UserNotAuthorizedException
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class MainActivity : ComponentActivity() {
    private var connectionParams: ConnectionParams? = null
    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    private val buttonManagementFlow: MutableStateFlow<ButtonManagement> =
        MutableStateFlow(ButtonManagement(null))

    private val spotifyConnectionListener = object : Connector.ConnectionListener {
        override fun onConnected(spotifyAppRemote: SpotifyAppRemote?) {
            mSpotifyAppRemote = spotifyAppRemote
            Log.e(TAG, "onConnected: $spotifyAppRemote")

            buttonManagementFlow.value = ButtonManagement(spotifyAppRemote?.playerApi)
        }

        override fun onFailure(error: Throwable?) {
            Log.e(TAG, "onFailure: $error")

            when (error) {
                is NotLoggedInException -> {
                    // Show login button and trigger the login flow from auth library when clicked
                }

                is UserNotAuthorizedException -> {
                    // Show there no such user
                }

                is CouldNotFindSpotifyApp -> {
                    // Show button to download Spotify
                }

                else -> {
                    // Show internet exception
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder: AuthorizationRequest.Builder = AuthorizationRequest.Builder(
            "e28855579e6b4e499adf252d742d7df3",
            AuthorizationResponse.Type.TOKEN,
            "yourcustomprotocol://callback"

        )
            .setScopes(arrayOf("streaming"))
            .setShowDialog(true)

        AuthorizationClient.openLoginInBrowser(this, builder.build())


        setContent {
            BanAggressorTheme {
                val buttonManagement by buttonManagementFlow.asStateFlow().collectAsState()
                SongManagement(buttonManagement)
            }

            LaunchedEffect(Unit) {

            }
        }
    }

    private fun customToast(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }

    //make service from it to avoid start/stop lifecycle

    /* if (isSpotifyInstalled()) {
         customToast("Spotify detected")
         connectionParams = ConnectionParams.Builder("e28855579e6b4e499adf252d742d7df3")
             .setRedirectUri("https://example.com/callback")
             .showAuthView(true)
             .build()

         SpotifyAppRemote.disconnect(mSpotifyAppRemote)
         SpotifyAppRemote.connect(this.applicationContext, connectionParams, spotifyConnectionListener)

     } else {
         customToast("Spotify not installed")
     }*/


    override fun onStart() {
        super.onStart()

        Log.e("onStart", "triggered")


        val uri = intent?.data
        if (uri != null) {
            val response: AuthorizationResponse = AuthorizationResponse.fromUri(uri)

            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    Log.e("AuthResult", "token = " + response.accessToken)
                }

                else -> {
                    Log.e("AuthResult", "else ")
                }

            }
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("onNewIntent", "triggered")
        val uri = intent?.data

        Log.e("Uri", uri.toString())
        if (uri != null) {
            val response: AuthorizationResponse = AuthorizationResponse.fromUri(uri)

            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    Log.e("AuthResult", "token = " + response.accessToken)
                }

                else -> {
                    Log.e("AuthResult", "else ")
                }

            }
        }
    }

    override fun onStop() {
        super.onStop()

        SpotifyAppRemote.disconnect(mSpotifyAppRemote)
    }

    private fun isSpotifyInstalled(): Boolean = try {
        packageManager.getPackageInfo("com.spotify.music", 0)

        Log.e(TAG, "Spotify Installed")

        true
    } catch (e: PackageManager.NameNotFoundException) {
        Log.e(TAG, "Spotify NOT Installed: $e")

        false
    }

    companion object {
        const val TAG = "MainActivity"
    }
}


class ButtonManagement(
    private val playerApi: PlayerApi?
) {
    private var isSpotifyPlaying = false

    init {
        val subscriptionTag = "SubscriptionTag"
        playerApi?.subscribeToPlayerState()
            ?.setEventCallback {
                isSpotifyPlaying = !it.isPaused
                Log.e(subscriptionTag, "event came: track = ${it.track}")

                it.track.artist.name
            }
            ?.setErrorCallback { throwable ->
                // =( =( =(
            }
    }

    fun next() = playerApi?.skipNext()

    fun previous() = playerApi?.skipPrevious()

    fun pauseOrResume() {
        playerApi?.let {
            if (isSpotifyPlaying) it.pause() else it.resume()
        }
    }
}
