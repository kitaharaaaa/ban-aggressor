package com.kitahara.data.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.kitahara.data.local.SpotifyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @Singleton
    @Provides
    fun provideKtorClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL

            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    Log.d("Ktor", message)
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    @Singleton
    @Provides
    fun provideSpotifyDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            SpotifyDatabase::class.java,
            "BanAggressor"
        ).build()

    @Singleton
    @Provides
    fun provideTokenDao(spotifyDatabase: SpotifyDatabase) =
        spotifyDatabase.tokenDao()

    @Singleton
    @Provides
    fun provideSpotifyStateDao(spotifyDatabase: SpotifyDatabase) =
        spotifyDatabase.spotifyStateDao()
}