plugins {
    id("com.android.library")
    id("kotlin-android")
}

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
