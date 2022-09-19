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
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.spotless) apply false
}

apply(from = "gradle/projectDependencyGraph.gradle")

val detektVersion = libs.versions.detekt.get()
allprojects {
    // Detekt
    apply(plugin = "io.gitlab.arturbosch.detekt")
    configure<DetektExtension> {
        toolVersion = detektVersion
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

    // Spotless
    apply(plugin = "com.diffplug.spotless")
    configure<SpotlessExtension> {
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
            ktlint(libs.versions.ktlint.get())
                .editorConfigOverride(
                    mapOf(
                        "disabled_rules" to "filename",
                        "ij_kotlin_allow_trailing_comma" to "true",
                        "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                    )
                )
            trimTrailingWhitespace()
            endWithNewline()
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"), "package ")
        }

        kotlinGradle {
            target("src/**/*.kts")
            ktlint(libs.versions.ktlint.get())
                .editorConfigOverride(
                    mapOf(
                        "disabled_rules" to "filename",
                        "ij_kotlin_allow_trailing_comma" to "true",
                        "ij_kotlin_allow_trailing_comma_on_call_site" to "true",
                    )
                )
            trimTrailingWhitespace()
            endWithNewline()
            licenseHeaderFile(
                rootProject.file("spotless/copyright.kt"),
                "(import|plugins|buildscript|dependencies|pluginManagement)"
            )
        }
    }
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }

    // Remove kotlinx-coroutines-debug, which is x86 only. Including it causes build errors:
    // https://github.com/Kotlin/kotlinx.coroutines/blob/5a71f7b8ec5fd636575a3b6408cbbc9a92b84f11/kotlinx-coroutines-debug/README.md#build-failures-due-to-duplicate-resource-files
    configurations.configureEach {
        exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
    }
}
