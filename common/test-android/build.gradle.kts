plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    buildFeatures {
        // none needed
    }
    namespace = "dev.petedoyle.common.test.android"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(projects.common.coroutineDispatchers)

    implementation(libs.androidx.arch.core)
    implementation(libs.androidx.compose.ui.test.junit4)
    implementation(libs.androidx.test.core)
    implementation(libs.androidx.test.espresso.core)
    implementation(libs.androidx.test.ext.junit)
    implementation(libs.androidx.test.ext.truth)
    implementation(libs.androidx.test.rules)
    implementation(libs.androidx.test.runner)
    implementation(libs.cashapp.turbine)
    implementation(libs.expectKt)
    implementation(libs.junit.junit)
    implementation(libs.kotlin.test)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.mockk.android)
}
