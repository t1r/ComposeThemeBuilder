plugins {
    id("com.android.library")
}

android {
    compileSdk = AndroidVersions.androidCompileSdk

    defaultConfig {
        minSdk = AndroidVersions.androidMinSdk
        targetSdk = AndroidVersions.androidTargetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
        }
    }
}