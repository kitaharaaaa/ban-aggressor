@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.kitahara.data"
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

    //ktor
    implementation( libs.ktor.client.android)
    implementation (libs.ktor.client.serialization)
    implementation (libs.kotlinx.serialization.json)

    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation ("com.google.code.gson:gson:2.6.1")
    implementation ("com.spotify.android:auth:1.2.5")

    implementation(files("C:\\Users\\User Unknown\\AndroidStudioProjects\\ban-aggressor\\spotify-app-remote-release-0.8.0.aar"))
    implementation(project(":core:common"))
}