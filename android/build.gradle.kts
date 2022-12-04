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
    implementation(project(":common"))
    implementation(project(":uicompose"))
    implementation("androidx.activity:activity-compose:1.3.0")
}

android {
    compileSdkVersion(32)
    defaultConfig {
        applicationId = "dev.t1r.themebuilder.android"
        minSdkVersion(24)
        targetSdkVersion(32)
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
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