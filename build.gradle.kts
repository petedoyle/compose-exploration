import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.diffplug.gradle.spotless.SpotlessExtension
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath(libs.plugin.agp)
        classpath(libs.plugin.kgp)
        classpath(libs.plugin.hilt)
    }
}

plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

apply(from = "gradle/projectDependencyGraph.gradle")

val detektVersion = libs.versions.detekt.get()
allprojects {
    // Common Android config for both application and library modules
    val commonAndroidConfig: CommonExtension<*, *, *, *>.() -> Unit = {
        compileSdk = libs.versions.android.build.compileSdk.get().toInt()
        buildToolsVersion = libs.versions.android.build.buildToolsVersion.get()
        defaultConfig {
            minSdk = libs.versions.android.build.minSdk.get().toInt()
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        packagingOptions {
            resources {
                excludes += listOf(
                    "META-INF/{AL2.0,LGPL2.1}",
                    "META-INF/LICENSE.md",
                    "META-INF/LICENSE-notice.md",
                )
            }
        }
    }
    // Android config for application modules
    pluginManager.withPlugin("com.android.application") {
        with(extensions.getByType<ApplicationExtension>()) {
            commonAndroidConfig()
            defaultConfig {
                targetSdk = libs.versions.android.build.targetSdk.get().toInt()
            }
        }
    }

    // Android config for library modules
    pluginManager.withPlugin("com.android.library") {
        with(extensions.getByType<LibraryExtension>()) {
            commonAndroidConfig()
            defaultConfig {
                minSdk = libs.versions.android.build.minSdk.get().toInt()
                targetSdk = libs.versions.android.build.targetSdk.get().toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles.add(project.file("consumer-rules.pro"))
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                    isCoreLibraryDesugaringEnabled = true
                }
            }

            buildTypes {
                release {
                    isMinifyEnabled = false
                    proguardFiles.addAll(
                        listOf(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            project.file("proguard-rules.pro"),
                        ),
                    )
                }
            }

            testOptions {
                unitTests {
                    isIncludeAndroidResources = true
                    isReturnDefaultValues = true
                }
            }

            dependencies {
                add("coreLibraryDesugaring", libs.android.core.desugaring)
            }
        }
    }

    // Detekt
    pluginManager.withPlugin("io.gitlab.arturbosch.detekt") {
        configure<DetektExtension> {
            toolVersion = libs.versions.detekt.get()
            allRules = true
        }

        tasks.withType<Detekt>().configureEach {
            reports {
                html.required.set(true)
                xml.required.set(true)
                txt.required.set(true)
            }
            jvmTarget = "11"
            excludes.add("**/v3/model/**/*.kt") // exclude generated bigcommerce models
            config.setFrom(files(rootProject.file("detekt.yml")))
        }
    }

    // Spotless
    // https://github.com/diffplug/spotless/tree/main/plugin-gradle#dependency-resolution-modes
    // https://github.com/diffplug/spotless/issues/1213
    pluginManager.withPlugin("com.diffplug.spotless") {
        val spotlessFormatters: SpotlessExtension.() -> Unit = {
            format("misc") {
                target("*.md", ".gitignore")
                trimTrailingWhitespace()
                endWithNewline()
            }

            kotlin {
                target("src/**/*.kt")
                targetExclude(
                    "**/copyright.kt",
                    "**/v3/model/**/*.kt", // exclude generated bigcommerce models
                )
                ktlint(libs.versions.ktlint.get()).editorConfigOverride(
                        mapOf(
                            "disabled_rules" to "filename",
                            "ij_kotlin_allow_trailing_comma" to "true",
                            "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                        ),
                    )
                trimTrailingWhitespace()
                endWithNewline()
                licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "package ")
            }

            kotlinGradle {
                target("src/**/*.kts")
                ktlint(libs.versions.ktlint.get()).editorConfigOverride(
                        mapOf(
                            "disabled_rules" to "filename",
                            "ij_kotlin_allow_trailing_comma" to "true",
                            "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                        ),
                    )
                trimTrailingWhitespace()
                endWithNewline()
                licenseHeaderFile(
                    rootProject.file("spotless/copyright.kt"),
                    "(import|plugins|buildscript|dependencies|pluginManagement)",
                )
            }
        }

        configure<SpotlessExtension> {
            spotlessFormatters()
        }
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
        }
    }

    // Remove kotlinx-coroutines-debug, which is x86 only. Including it causes build errors:
    // https://github.com/Kotlin/kotlinx.coroutines/blob/5a71f7b8ec5fd636575a3b6408cbbc9a92b84f11/kotlinx-coroutines-debug/README.md#build-failures-due-to-duplicate-resource-files
    configurations.configureEach {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }
}
