plugins {
    id("mpp-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Kotlin.coroutines)
                implementation(Deps.Kotlin.coroutinesSwing)
            }
        }
    }
}