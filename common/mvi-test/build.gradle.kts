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
  implementation(project(":common:mvi"))
  implementation(libs.kotlinx.coroutines.core)
}
