plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply(from = "$rootDir/gradle/defaults-android-library.gradle")

android {
    buildFeatures {
        // none needed
    }
    namespace = "dev.petedoyle.common.test"
}

dependencies {
    implementation(projects.common.coroutineDispatchers)

    implementation(libs.androidx.arch.core)
    implementation(libs.junit.junit)
    implementation(libs.mockk)
    implementation(libs.kotlin.test)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.test)
}
