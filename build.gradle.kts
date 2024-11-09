// build.gradle.kts (project level)
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.5.1") // Android Gradle Plugin version (update to your version)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0") // Kotlin plugin version (update to your version)
        // Add other necessary classpath dependencies if needed (e.g., Firebase, Hilt)
    }
}

allprojects {
    // repositories block has been removed from here
}