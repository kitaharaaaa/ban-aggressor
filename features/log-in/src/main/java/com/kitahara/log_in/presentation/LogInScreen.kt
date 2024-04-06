package com.kitahara.log_in.presentation

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.Consumer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.kitahara.common.R
import com.kitahara.log_in.domain.LogInLauncher

@Composable
fun LogInScreen(
    logInLauncher: LogInLauncher,
    navigateHomeScreen: () -> Unit
) {
    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)
    val activity = LocalContext.current

    val viewModel = hiltViewModel<LogInViewModel>()

    LaunchedEffect(key1 = Unit) {
        if (viewModel.isTokenExpired().not()) navigateHomeScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            strokeWidth = 4.dp,
            trackColor = Color.Black,
            color = White
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Welcome to ${stringResource(R.string.app_name)}"
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            textAlign = TextAlign.Center,
            text = "For managing your music from this app we need to extract temporary Spotify token." +
                    "\nWith him we can obtain and handle data about your playback",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 29.dp)
        )

        Spacer(Modifier.height(40.dp))

        FilledTonalButton(onClick = logInLauncher::openLogInWindow) {
            Text(
                text = "Provide valid token"
            )
        }
    }

    DisposableEffect(key1 = lifecycleOwner) {
        var listener: Consumer<Intent>? = null

        val observer = LifecycleEventObserver { _, event ->
            listener = Consumer<Intent> {
                Log.e("Consumer", it.data.toString())

                viewModel.saveNewToken(
                    intent = it,
                    onComplete = navigateHomeScreen,
                    onFailure = logInLauncher::openLogInWindow
                )
            }

            if (event == Lifecycle.Event.ON_RESUME) {
                listener?.let {
                    activity.toInstance().addOnNewIntentListener(it)
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            listener?.let {
                activity.toInstance().removeOnNewIntentListener(it)
            }

            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

fun Context.toInstance(): ComponentActivity = this as ComponentActivity

@Composable
@Preview
fun LogInScreenPreview() {
    LogInScreen(
        object : LogInLauncher {
            override fun openLogInWindow() {

            }
        }
    ) {}
}