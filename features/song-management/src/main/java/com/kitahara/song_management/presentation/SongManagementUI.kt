package com.kitahara.song_management.presentation

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun SongManagement() {
    Box(modifier = Modifier.fillMaxSize()) {
        /*Column(
            modifier = Modifier.fillMaxSize(),*//*
            verticalArrangement = Arrangement.spacedBy(15.dp)*//*
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = "Song Management",
                textAlign = TextAlign.Center
            )*/

            Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red),
                horizontalArrangement = Arrangement.Center
                ) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = "Arrow Left"
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Outlined.PlayArrow,
                        contentDescription = "Arrow Play"
                    )
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(70.dp),
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription ="Arrow Right"
                    )
                }
            }
        }
}