plugins {
    id("mpp-setup")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
//                api(compose.runtime)
//                api(compose.foundation)
                implementation(compose.material)
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Decompose.extensionsComposeJetbrains)

                implementation(project(Module.Feature.root))
                implementation(project(Module.Feature.baselineColor))
            }
        }
        val androidMain by getting {
            dependencies {
//                api("androidx.appcompat:appcompat:1.2.0")
//                api("androidx.core:core-ktx:1.3.1")
            }
        }
        val desktopMain by getting {
            dependencies {
//                api(compose.preview)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}
