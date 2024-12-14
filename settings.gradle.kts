pluginManagement {
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
    "multiplayer"
)

