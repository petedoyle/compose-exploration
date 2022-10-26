plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.openapi.generator)
}

apply(from = "$rootDir/gradle/defaults-android-library.gradle")

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "dev.petedoyle.commerce.common.api.bigcommerce"
}

dependencies {
    implementation(projects.apps.composeCommerce.api.bigcommerceModelsCatalogV3)
    implementation(projects.apps.composeCommerce.api.bigcommerceModelsCartsV3)
    implementation(projects.common.coroutineDispatchers)

    implementation(libs.androidx.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.eithernet)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.logging)
    kapt(libs.moshi.kotlin.codegen)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    testImplementation(libs.junit.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}
