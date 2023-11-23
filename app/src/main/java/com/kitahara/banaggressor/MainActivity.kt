package com.kitahara.banaggressor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kitahara.banaggressor.ui.theme.BanAggressorTheme
import com.kitahara.song_management.presentation.SongManagement

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanAggressorTheme {
               SongManagement()
            }
        }
    }
}