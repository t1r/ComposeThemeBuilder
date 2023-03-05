plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

repositories {
    jcenter()
}

dependencies {
    implementation(compose.runtime)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.Compose.activity)
    implementation(Deps.AndroidX.Compose.lifecycleRuntime)

    implementation(project(Module.UI.compose))
    implementation(project(Module.Component.root))
    implementation(project(Module.Component.materialColorsPalette))
    implementation(project(Module.Data.data))
    implementation(project(Module.entity))

    implementation(Deps.Decompose.decompose)
    implementation(Deps.Decompose.extensionsComposeJetbrains)
    implementation(Deps.MviKotlin.mvikotlin)
    implementation(Deps.MviKotlin.mvikotlinLogging)
    implementation(Deps.MviKotlin.mvikotlinTimetravel)
    implementation(Deps.RusshwolfSettings.core)
    implementation(Deps.RusshwolfSettings.coroutines)
    implementation(Deps.Android.material)
}

android {
    compileSdk = AndroidVersions.androidCompileSdk
    defaultConfig {
        applicationId = App.id
        minSdk = AndroidVersions.androidMinSdk
        targetSdk = AndroidVersions.androidTargetSdk
        versionCode = App.versionCode
        versionName = App.versionName
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("android/signingcreds/debug.keystore")
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storePassword = "android"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
        }
    }
}