pluginManagement {
    val kspVersion: String by settings

    plugins {
        id("com.google.devtools.ksp") version kspVersion
    }

    repositories { mavenLocal(); mavenCentral(); google(); gradlePluginPortal() }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
    id("com.soywiz.korge.settings") version "6.0.0-alpha9"
}

rootProject.name = "game"

include(
    "client",
    "server",
    "multiplayer",
    "multiplayer:processor"
)

