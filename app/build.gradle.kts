plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt") // ✅ Kapt plugin for annotation processing
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.malik21.malikstudentstudytime"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.malik21.malikstudentstudytime"
        minSdk = 24
        targetSdk = 35
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
        // Enable core library desugaring for Java 8+ APIs (e.g. java.time)
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // AndroidX Core and Lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose and Material3
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Kotlin Serialization for JSON
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Retrofit Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ComposeCalendar (Calendar UI)
    implementation("io.github.boguszpawlowski.composecalendar:composecalendar:1.4.0")
    implementation("io.github.boguszpawlowski.composecalendar:kotlinx-datetime:1.4.0")

    // Core Library Desugaring (required for Java 8+ time APIs on older Android)
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("androidx.compose.material:material-icons-extended:<compose_version>")

    // Instrumented testing dependencies
    androidTestImplementation ("androidx.room:room-testing:2.5.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")      // JUnit for Android tests
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1") // Espresso UI testing
    // For coroutine testing
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

// For mocking
    testImplementation ("io.mockk:mockk:1.13.5")



}
