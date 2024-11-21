group = "com"
version = "1.0-SNAPSHOT"

plugins {
    id("com.soywiz.korge") version korlibs.korge.gradle.common.KorgeGradlePluginVersion.VERSION
}

dependencies {
    commonTestImplementation("org.jetbrains.kotlin:kotlin-test")
}

korge {
    id = "com.client.game"
    targetJvm()
}
