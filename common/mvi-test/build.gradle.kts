plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply(from = "$rootDir/gradle/defaults-android-library.gradle")

android {
    buildFeatures {
        // none required
    }
    namespace = "dev.petedoyle.common.mvi.test"
}

dependencies {
    implementation(projects.common.mvi)

    implementation(libs.kotlinx.coroutines.core)
}
