package com.kitahara.home.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kitahara.common.song_state.CurrentSongData
import com.kitahara.home.presentation.components.SongManagement
import com.kitahara.home.presentation.receiver.PlaybackStateBroadcastReceiver
import com.kitahara.home.presentation.receiver.PlaybackStateBroadcastReceiver.Companion.registerBroadcast

@Composable
fun HomeScreen() {
    val context = LocalContext.current

    val viewModel = hiltViewModel<HomeViewModel>()

    val coverUri by viewModel.getSongCoverUri().collectAsState(initial = null)
    val isPlaying by viewModel.getIsPlayingState().collectAsState(false)
    val songData by viewModel.getSongInfo().collectAsState(initial = CurrentSongData(null, null))

    LaunchedEffect(key1 = songData) {
        Log.e("SongData", songData.toString())
    }
    LaunchedEffect(key1 = songData) {
        Log.e("isPlaying", isPlaying.toString())
    }
  LaunchedEffect(key1 = coverUri) {
        Log.e("coverUri", coverUri.toString())
    }

    SongManagement(coverUri = coverUri, isPlaying, songData.authorName, songData.track)
    DisposableEffect(Unit) {
        registerBroadcast(context)

        onDispose {
            context.unregisterReceiver(PlaybackStateBroadcastReceiver())
        }
    }
}