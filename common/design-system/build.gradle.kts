plugins {
  id("com.android.library")
  id("kotlin-android")
}

apply(from = "$rootDir/gradle/defaults-android-library.gradle")

android {
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
  }
}

dependencies {
  implementation(project(":common:coroutine-dispatchers"))
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

  testImplementation(project(":common:test-unit"))
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
