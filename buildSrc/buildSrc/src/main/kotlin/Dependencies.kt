object App {
    const val id = "dev.t1r.themebuilder"
    const val versionCode = 2
    const val versionName = "0.2.0"
}

object Versions {
    const val androidMinSdk = 21
    const val androidCompileSdk = 32
    const val androidTargetSdk = androidCompileSdk

    const val decompose = "1.0.0-beta-01"
    const val mviKotlin = "3.0.2"
}

object Deps {
    const val gradle = "com.android.tools.build:gradle:7.0.4"

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
        const val coroutinesSwing = "org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.4.0"

        object Compose {
            const val version = "1.2.0"
            const val activity = "androidx.activity:activity-compose:1.3.0"
            const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01"
        }
    }

    object Decompose {
        const val decompose = "com.arkivanov.decompose:decompose:${Versions.decompose}"
        const val extensionsComposeJetbrains =
            "com.arkivanov.decompose:extensions-compose-jetbrains:${Versions.decompose}"
    }

    object MviKotlin {
        const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:${Versions.mviKotlin}"
        const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:${Versions.mviKotlin}"
        const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:${Versions.mviKotlin}"
        const val mvikotlinTimetravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:${Versions.mviKotlin}"
        const val mvikotlinExtensionsCoroutines =
            "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines:${Versions.mviKotlin}"
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

object Module {
    const val entity = ":entity"

    object Feature {
        const val root = ":feature-root"
        const val baselineColor = ":feature-baseline-colors"
        const val materialColorsPalette = ":feature-material-colors-palette"
        const val export = ":feature-export"
    }

    object UI {
        const val compose = ":ui-compose"
    }

    object Data {
        const val data = ":data"
    }
}