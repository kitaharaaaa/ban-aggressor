package com.kitahara.common

import android.content.Context
import android.content.pm.PackageManager

fun isSpotifyInstalled(context: Context): Boolean = try {
    context.packageManager.getPackageInfo("com.spotify.music", 0)
    true
} catch (e: PackageManager.NameNotFoundException) {
    false
}