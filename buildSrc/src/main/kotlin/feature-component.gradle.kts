plugins {
    id("mpp-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Decompose.decompose)
                implementation(Deps.Decompose.extensionsComposeJetbrains)

                implementation(Deps.MviKotlin.mvikotlin)
                implementation(Deps.MviKotlin.mvikotlinMain)
                implementation(Deps.MviKotlin.mvikotlinLogging)
                implementation(Deps.MviKotlin.mvikotlinTimetravel)
                implementation(Deps.MviKotlin.mvikotlinExtensionsCoroutines)
            }
        }
    }
}