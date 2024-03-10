package com.kitahara.log_in.domain

import android.content.Intent

interface TokenHandling {
    suspend fun isTokenExpired(): Boolean

    suspend fun saveNewToken(intent: Intent?)
}