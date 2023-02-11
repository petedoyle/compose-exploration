plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    buildFeatures {
        // none required
    }
    namespace = "dev.petedoyle.common.mvi"
}

dependencies {
    implementation(projects.common.coroutineDispatchers)

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}
