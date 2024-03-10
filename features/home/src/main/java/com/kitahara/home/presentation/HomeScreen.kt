package com.kitahara.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.kitahara.home.presentation.receiver.PlaybackStateBroadcastReceiver
import com.kitahara.home.presentation.receiver.PlaybackStateBroadcastReceiver.Companion.registerBroadcast

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    DisposableEffect(context) {
        registerBroadcast(context)

        onDispose {
            context.unregisterReceiver(PlaybackStateBroadcastReceiver())
        }
    }
}