plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.myhomemachine"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myhomemachine"
        minSdk = 21
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Core Android and Jetpack Compose dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // For animations
    implementation("com.google.accompanist:accompanist-navigation-animation:0.30.1")

    // For extended icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // For custom fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")

    // For coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Jetpack Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.7.2")

    // Matter dependencies (replace with actual versions)
    implementation("com.google.home.matter:matter-core:1.0.0")  // Replace with actual version
    implementation("com.google.home.matter:matter-client:1.0.0") // Replace with actual version
    implementation("com.google.home.matter:matter-commissioning:1.0.0") // Replace with actual version

    // DNS Java library (removed duplicate version)
    implementation("com.github.dnsjava:dnsjava:3.5.0")

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debugging
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
