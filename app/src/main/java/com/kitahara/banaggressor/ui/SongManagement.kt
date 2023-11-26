package com.kitahara.banaggressor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kitahara.banaggressor.ButtonManagement

@Composable
fun SongManagement(buttonManagement: ButtonManagement) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { buttonManagement.previous() }) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = "Arrow Left"
                )
            }

            IconButton(onClick = { buttonManagement.pauseOrResume() }) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = "Arrow Play"
                )
            }

            IconButton(onClick = { buttonManagement.next() }) {
                Icon(
                    modifier = Modifier.size(70.dp),
                    imageVector = Icons.Outlined.KeyboardArrowRight,
                    contentDescription = "Arrow Right"
                )
            }
        }
    }
}