plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.dependencyGuard)
}

android {
    defaultConfig {
        versionCode = 1
        versionName = "1.0 ($versionCode)"
        applicationId = "dev.petedoyle.commerce"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    namespace = "dev.petedoyle.commerce"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(projects.apps.composeCommerce.api.bigcommerceClient)
    implementation(projects.apps.composeCommerce.api.bigcommerceModelsCartsV3)
    implementation(projects.apps.composeCommerce.api.bigcommerceModelsCatalogV3)
    implementation(projects.common.coroutineDispatchers)
    implementation(projects.common.designSystem)
    implementation(projects.common.flux)
    implementation(projects.common.mvi)

    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.insets)
    implementation(libs.accompanist.insets.ui)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose) {
        exclude(group = "androidx.lifecycle", module = "lifecycle-viewmodel-ktx")
    }
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.eithernet)
    implementation(libs.retrofit)
    implementation(libs.play.core)
    implementation(libs.play.core.ktx)

    // Desugaring
    coreLibraryDesugaring(libs.android.core.desugaring)

    debugImplementation(libs.androidx.compose.ui.tooling)

    testImplementation(projects.common.testUnit)
    testImplementation(libs.cashapp.turbine)
    testImplementation(libs.expectKt)
    testImplementation(libs.junit.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(testFixtures(libs.eithernet))

    androidTestImplementation(projects.common.testAndroid)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.ext.truth)
    androidTestImplementation(libs.cashapp.turbine)
    androidTestImplementation(libs.expectKt)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.mockk.android)
}

dependencyGuard {
    configuration("releaseRuntimeClasspath")
}
