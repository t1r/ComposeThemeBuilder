object App {
    const val id = "dev.t1r.themebuilder"
    const val versionCode = 1
    const val versionName = "0.1.0"
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
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.4.0"

        object Compose {
            const val version = "1.2.0"
            const val activity = "androidx.activity:activity-compose:1.3.0"
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
}

object Module {
    object Feature {
        const val root = ":feature-root"
        const val baselineColor = ":feature-baseline-color"
    }

    object UI {
        const val compose = ":ui-compose"
    }
}