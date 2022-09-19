plugins {
  id("com.android.library")
  id("kotlin-android")
}

apply(from = "$rootDir/gradle/defaults-android-library.gradle")

android {
  buildFeatures {
    // none required
  }
}

dependencies {
  implementation(project(":common:coroutine-dispatchers"))
  implementation(libs.androidx.annotation)
  implementation(libs.kotlinx.coroutines.core)

  testImplementation(libs.junit.junit)
  testImplementation(libs.mockk)
  testImplementation(libs.kotlin.test)
  testImplementation(libs.kotlinx.coroutines.core)
  testImplementation(libs.kotlinx.coroutines.test)
}
