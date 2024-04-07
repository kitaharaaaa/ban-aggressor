package com.kitahara.home.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kitahara.home.R

@Composable
fun SongManagement(coverUri: String?, isPlaying: Boolean, authorName: String?, track: String?) {

    LaunchedEffect(key1 = coverUri) {
        Log.e("CoverChanged", coverUri.toString())
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(horizontal = 19.dp),
        shape = RoundedCornerShape(25.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterStart
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(coverUri)
                    .crossfade(true)
                    .build(),
                /*     placeholder = painterResource(R.drawable.placeholder),*/
                contentDescription = stringResource(R.string.song_cover_description),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = authorName ?: "",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = track ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Card(
                    modifier = Modifier.alpha(.4f),
                    shape = CircleShape,
                    colors = CardColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.DarkGray,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Gray,
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        text = (if (isPlaying) "Sounds" else "Paused").uppercase(),
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun SongManagementPreview() {
    SongManagement(
        coverUri = "https://i.scdn.co/image/ab67616d0000b2731c1364032a25ec5376aad526",
        isPlaying = false,
        authorName = "the feels",
        track = "все навколо дивно"
    )
}