plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group "dev.t1r.themebuilder"
version "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(compose.runtime)
    implementation(Deps.AndroidX.appCompat)
    implementation(Deps.AndroidX.Compose.activity)

    implementation(project(Module.UI.compose))
    implementation(project(Module.Feature.root))
    implementation(project(Module.Feature.materialColorsPallet))
    implementation(project(Module.Data.data))

    implementation(Deps.Decompose.decompose)
    implementation(Deps.Decompose.extensionsComposeJetbrains)
    implementation(Deps.MviKotlin.mvikotlin)
    implementation(Deps.MviKotlin.mvikotlinLogging)
    implementation(Deps.MviKotlin.mvikotlinTimetravel)
}

android {
    compileSdk = Versions.androidCompileSdk
    defaultConfig {
        applicationId = App.id
        minSdk = Versions.androidMinSdk
        targetSdk = Versions.androidTargetSdk
        versionCode = App.versionCode
        versionName = App.versionName
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}