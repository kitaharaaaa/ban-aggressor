package com.kitahara.common.enums

import android.app.NotificationManager

enum class ChannelsEnum(
    val channelId: String,
    val notificationName: String,
    val importance: Int
) {
    SongPlayback(
        "playment_channel",
        "Currently playing",
        NotificationManager.IMPORTANCE_HIGH
    );
}