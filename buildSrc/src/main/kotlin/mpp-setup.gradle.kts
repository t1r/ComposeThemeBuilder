import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("android-setup")
}

kotlin {
    android()
    jvm("desktop")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}