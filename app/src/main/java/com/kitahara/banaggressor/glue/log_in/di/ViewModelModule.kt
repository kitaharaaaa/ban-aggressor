package com.kitahara.banaggressor.glue.log_in.di

import com.kitahara.banaggressor.glue.log_in.repos.TokenHandlingImpl
import com.kitahara.log_in.domain.TokenHandling
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindTokenHandlingImpl(impl: TokenHandlingImpl): TokenHandling
}