// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false

    id("com.google.dagger.hilt.android") version "2.48.1" apply false

    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
}
true // Needed to make the Suppress annotation work for the plugins block