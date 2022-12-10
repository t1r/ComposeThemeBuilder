plugins {
    id("mpp-setup")
    id("feature-component")
    id("kotlin-parcelize")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Module.Data.data))
                implementation(project(Module.Feature.baselineColor))
            }
        }
    }
}