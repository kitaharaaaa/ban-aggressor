package com.kitahara.song_management

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SongManagement() {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Song Management",
        textAlign = TextAlign.Center
    )
}