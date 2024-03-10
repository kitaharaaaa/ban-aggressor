package com.kitahara.banaggressor.glue.log_in.repos

import com.kitahara.data.remote.auth.SpotifyAuth
import com.kitahara.log_in.domain.LogInLauncher
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LogInLauncherImpl@Inject constructor(
    private val spotifyAuth: SpotifyAuth
): LogInLauncher {
    override fun openLogInWindow() {
        spotifyAuth.callInitWindow()
    }
}