@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.kitahara.banaggressor"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    //room
    implementation(libs.androidx.room.ktx)
    // To use Kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.room.runtime)

    //ktor
    implementation( libs.ktor.client.android)
    implementation (libs.ktor.client.serialization)
    implementation (libs.kotlinx.serialization.json)


    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.gson)
    implementation(libs.auth)

    implementation(project(":core:common"))
}