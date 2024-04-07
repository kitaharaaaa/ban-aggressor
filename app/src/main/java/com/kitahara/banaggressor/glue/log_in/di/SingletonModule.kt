package com.kitahara.banaggressor.glue.log_in.di

import com.kitahara.banaggressor.glue.log_in.repos.CurrentSongStatusUpdateImpl
import com.kitahara.home.domain.repository.CurrentSongStatusUpdateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonModule {

    @Binds
    @Singleton
    abstract fun bindCurrentSongStatusUpdate(impl: CurrentSongStatusUpdateImpl): CurrentSongStatusUpdateRepository
}