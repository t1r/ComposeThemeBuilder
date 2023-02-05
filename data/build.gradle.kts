plugins {
    id("mpp-setup")
    id("app.cash.sqldelight")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.Kotlin.coroutines)
                implementation(Deps.Kotlin.coroutinesSwing)
                implementation(Deps.SqlDelight.coroutines)
                implementation(Deps.SqlDelight.runtime)
                implementation(Deps.russhwolfSettings)

                implementation(project(Module.entity))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.SqlDelight.Driver.android)
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(Deps.SqlDelight.Driver.sqlite)
            }
        }
    }
}

sqldelight {
    databases {
        create("ThemeBuilderDb") {
            packageName.set("dev.t1r.themebuilder.data")
            sourceFolders.set(listOf("sqldelight"))
        }
    }
}