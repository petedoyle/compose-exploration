plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    alias(libs.plugins.dependencyGuard)
}

android {
    defaultConfig {
        applicationId = "dev.petedoyle.moviesearch"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }
    namespace = "dev.petedoyle.moviesearch"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(projects.common.coroutineDispatchers)
    implementation(projects.common.designSystem)
    implementation(projects.common.mvi)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.activity.compose)

    testImplementation(projects.common.testUnit)
    testImplementation(libs.junit.junit)
    testImplementation(libs.cashapp.turbine)
    testImplementation(libs.expectKt)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}
