object Deps {
    const val gradle = "com.android.tools.build:gradle:7.0.4"

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
        const val coroutinesSwing = "org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"

        object Compose {
            const val version = "1.2.0"
            const val activity = "androidx.activity:activity-compose:1.3.0"
            const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01"
        }
    }

    object Android {
        const val material = "com.google.android.material:material:1.6.0"
    }

    object Decompose {
        const val version = "1.0.0-beta-01"
        const val decompose = "com.arkivanov.decompose:decompose:${version}"
        const val extensionsComposeJetbrains =
            "com.arkivanov.decompose:extensions-compose-jetbrains:${version}"
    }

    object MviKotlin {
        private const val version = "3.0.2"
        const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:${version}"
        const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:${version}"
        const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:${version}"
        const val mvikotlinTimetravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:${version}"
        const val mvikotlinExtensionsCoroutines =
            "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:${version}"
    }

    object SqlDelight {
        private const val version = "2.0.0-alpha05"
        const val coroutines = "app.cash.sqldelight:coroutines-extensions:$version"
        const val runtime = "app.cash.sqldelight:runtime:$version"
        const val primitiveAdapters = "app.cash.sqldelight:primitive-adapters:$version"

        object Driver {
            const val android = "app.cash.sqldelight:android-driver:$version"
            const val sqlite = "app.cash.sqldelight:sqlite-driver:$version"
        }
    }

    object RusshwolfSettings {
        const val core = "com.russhwolf:multiplatform-settings:1.0.0"
        const val coroutines = "com.russhwolf:multiplatform-settings-coroutines:1.0.0"
    }
}