package com.kitahara.banaggressor.glue.log_in.di

import com.kitahara.banaggressor.glue.log_in.repos.LogInLauncherImpl
import com.kitahara.log_in.domain.LogInLauncher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindLogInLauncher(impl: LogInLauncherImpl): LogInLauncher
}