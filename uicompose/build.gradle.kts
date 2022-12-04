import org.jetbrains.compose.ComposeBuildConfig.composeVersion
import org.jetbrains.compose.compose

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

group = "dev.t1r.themebuilder"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val commonMain by getting {
            val decomposeVersion = "1.0.0-beta-01"
            val mviKotlinVersion = "3.0.2"
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)

                api("com.arkivanov.decompose:decompose:$decomposeVersion")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:$decomposeVersion")
//                api("com.arkivanov.mvikotlin:mvikotlin:$mviKotlinVersion")
//                api("com.arkivanov.mvikotlin:mvikotlin-main:$mviKotlinVersion")
//                api("com.arkivanov.mvikotlin:mvikotlin-logging:$mviKotlinVersion")
//                api("com.arkivanov.mvikotlin:mvikotlin-timetravel:$mviKotlinVersion")
//                api("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:$mviKotlinVersion")

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.2.0")
                api("androidx.core:core-ktx:1.3.1")
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 32
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}