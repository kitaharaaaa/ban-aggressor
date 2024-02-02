package com.kitahara.banaggressor.app.di.application

import com.hyperhoop.song_playback.domain.PlaybackInfo
import com.kitahara.banaggressor.glue.song_playback.PlaybackInfoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {
    @Binds
    abstract fun provideArtisInfo(impl: PlaybackInfoImpl): PlaybackInfo
}