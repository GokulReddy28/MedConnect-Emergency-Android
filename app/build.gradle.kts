plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.medconnect.emergency"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.medconnect.emergency"
        minSdk = 21
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.google.code.gson:gson:2.10.1")
    // Play Services Location for FusedLocationProviderClient
    implementation("com.google.android.gms:play-services-location:21.0.1")
// Optional: lifecycle / coroutines (not required, using callbacks)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    // Add Volley FIRST (manual dependency)

    implementation(libs.volley)
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Existing dependencies from version catalog
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

