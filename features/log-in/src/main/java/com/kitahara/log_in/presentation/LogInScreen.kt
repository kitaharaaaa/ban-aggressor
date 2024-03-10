package com.kitahara.log_in.presentation

import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.util.Consumer
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.kitahara.log_in.domain.LogInLauncher

@Composable
fun LogInScreen(
    logInLauncher: LogInLauncher
) {
    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)
    val activity = (LocalContext.current as ComponentActivity)

    val viewModel = hiltViewModel<LogInViewModel>()

    LaunchedEffect(key1 = Unit) {
        if (viewModel.isTokenExpired())
            logInLauncher.openLogInWindow()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Logging in screen"
        )
    }

    DisposableEffect(key1 = lifecycleOwner) {
        var listener: Consumer<Intent>? = null

        val observer = LifecycleEventObserver { _, event ->
            listener = Consumer<Intent> {
                Log.e("Consumer", it.data.toString())

                viewModel.saveNewToken(it)
            }

            if (event == Lifecycle.Event.ON_RESUME) {
                listener?.let {
                    activity.addOnNewIntentListener(it)
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            listener?.let {
                activity.removeOnNewIntentListener(it)
            }

            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
@Preview
fun LogInScreenPreview() {
    LogInScreen(
        object : LogInLauncher {
            override fun openLogInWindow() {

            }
        },
    )
}