plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
    namespace = "dev.petedoyle.commerce.design.compose"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(projects.common.coroutineDispatchers)

    implementation(libs.accompanist.placeholder.material)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.kotlinx.coroutines.core)

    // Compose preview tooling
    debugImplementation(libs.androidx.compose.ui.tooling)

    // Transitive deps needed for libs.androidx.compose.ui.tooling
    // Not sure why they don't get pulled in automatically
    debugImplementation(libs.androidx.lifecycle.viewmodel.ktx)
    debugImplementation(libs.androidx.savedstate)
    debugImplementation(libs.androidx.core.ktx)

    testImplementation(projects.common.testUnit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.cashapp.turbine)
    testImplementation(libs.expectKt)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
