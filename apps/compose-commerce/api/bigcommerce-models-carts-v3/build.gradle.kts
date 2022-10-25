plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    alias(libs.plugins.openapi.generator)
    alias(libs.plugins.ksp)
}

apply(from = "$rootDir/gradle/defaults-android-library.gradle")

android {
    buildFeatures {
        // none needed
    }
}

openApiValidate {
    inputSpec.set("$projectDir/specs/carts.v3.yml")
    recommend.set(true)
}

openApiGenerate {
    globalProperties.set(
        mutableMapOf(
            "apis" to "false",
            "models" to "",
            "modelTests" to "false",
            "modelDocs" to "false",
        ),
    )
    validateSpec.set(false)
    generatorName.set("kotlin")
    inputSpec.set("$projectDir/specs/carts.v3.yml")
    outputDir.set("$projectDir")
    apiPackage.set("dev.petedoyle.commerce.common.api.bigcommerce.carts.v3")
    modelPackage.set("dev.petedoyle.commerce.common.api.bigcommerce.carts.v3.model")
    invokerPackage.set("dev.petedoyle.commerce")
    configOptions.set(
        mutableMapOf(
            "dateLibrary" to "java8",
            "library" to "jvm-retrofit2",
            "moshiCodeGen" to "true",
            "useCoroutines" to "true",
        ),
    )
}

dependencies {
    implementation(libs.eithernet)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.logging)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    testImplementation(libs.junit.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}
