plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "dev.t1r.themebuilder"
version = "1.0-SNAPSHOT"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                implementation(project(Module.UI.compose))
                implementation(project(Module.Feature.root))
                implementation(project(Module.Feature.materialColorsPalette))
                implementation(project(Module.Data.data))
                implementation(project(Module.entity))

                implementation(Deps.Decompose.decompose)
                implementation(Deps.Decompose.extensionsComposeJetbrains)
                implementation(Deps.MviKotlin.mvikotlin)
                implementation(Deps.MviKotlin.mvikotlinLogging)
                implementation(Deps.MviKotlin.mvikotlinTimetravel)
                implementation(Deps.RusshwolfSettings.core)
                implementation(Deps.RusshwolfSettings.coroutines)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "dev.t1r.themebuilder.MainKt"
//        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
//            packageName = "ComposeThemeBuilder"
//            packageVersion = "1.0.0"
//        }
    }
}
