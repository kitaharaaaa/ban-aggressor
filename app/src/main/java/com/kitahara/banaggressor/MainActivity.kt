package com.kitahara.banaggressor

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hyperhoop.song_playback.presentation.ServiceManagement
import com.kitahara.common.navigation.AppNavigation
import com.kitahara.log_in.domain.LogInLauncher
import com.kitahara.log_in.presentation.LogInScreen
import com.kitahara.song_management.presentation.playback_state.PlaybackStateBroadcastReceiver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var serviceManager: ServiceManagement

    @Inject
    lateinit var logInLauncher: LogInLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerSpotifyBroadcastReceiver()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = AppNavigation.LogIn.route) {
                composable(AppNavigation.LogIn.route) {
                    LogInScreen(logInLauncher)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            serviceManager.launchService()
        } else if (requestCode == 1337) {

            //if API above or equals - we need request permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            } else serviceManager.launchService()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        serviceManager.stopService()
    }

    private fun registerSpotifyBroadcastReceiver() {
        val intentFilter = IntentFilter().apply {
            addAction("com.spotify.music.playbackstatechanged")
            addAction("com.spotify.music.metadatachanged")
            addAction("com.spotify.music.queuechanged")
        }

        ContextCompat.registerReceiver(
            this,
            PlaybackStateBroadcastReceiver(),
            intentFilter,
            ContextCompat.RECEIVER_EXPORTED,
        )
    }
}



