group = "com"
version = "1.0-SNAPSHOT"

plugins {
    id("com.soywiz.korge") version korlibs.korge.gradle.common.KorgeGradlePluginVersion.VERSION
}

val ktorVersion: String by project

dependencies {
    commonMainImplementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
    commonMainImplementation("io.ktor:ktor-client-websockets:$ktorVersion")
    commonMainImplementation("com.fasterxml.jackson.core:jackson-core:2.10.1")
    commonMainImplementation("com.fasterxml.jackson.core:jackson-annotations:2.10.1")
    commonMainImplementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
    commonMainImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    commonMainImplementation(project(":multiplayer"))
    jvmMainApi("io.ktor:ktor-client-cio:$ktorVersion")
    commonTestImplementation("org.jetbrains.kotlin:kotlin-test")
}

korge {
    id = "com.client.game"
    targetJvm()
}
