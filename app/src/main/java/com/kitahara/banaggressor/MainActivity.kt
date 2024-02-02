package com.kitahara.banaggressor

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import com.hyperhoop.song_playback.presentation.ServiceManagement
import com.kitahara.data.auth.SpotifyAuthImpl
import com.kitahara.data.connection.source.SpotifyConnectionHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var serviceManager: ServiceManagement

    @Inject
    lateinit var connectionHandler: SpotifyConnectionHandler

    @Inject
    lateinit var auth: SpotifyAuthImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        connectionHandler.buildConnection {
            customToast(it)
        }
        setContent {
//todo set stop service button
        }
    }

    override fun onStart() {
        super.onStart()

        connectionHandler.startSpotifyConnection {
            customToast(it)
        }
      /*  auth()*/
    }

    private fun customToast(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
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
}



