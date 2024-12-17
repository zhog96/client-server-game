plugins {
    id("com.google.devtools.ksp")
    id("com.soywiz.korge") version korlibs.korge.gradle.common.KorgeGradlePluginVersion.VERSION
}

val ktorVersion: String by project
val kspVersion: String by project

dependencies {
    commonMainImplementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
    commonMainImplementation("io.ktor:ktor-server-core:$ktorVersion")
    commonMainImplementation("io.ktor:ktor-websockets:$ktorVersion")
    commonMainImplementation("io.ktor:ktor-server-cio:$ktorVersion")
    commonMainImplementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
    commonMainImplementation("com.fasterxml.jackson.core:jackson-core:2.10.1")
    commonMainImplementation("com.fasterxml.jackson.core:jackson-annotations:2.10.1")
    commonMainImplementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
    commonMainImplementation(project(":multiplayer:processor"))
    kspJvm(project(":multiplayer:processor"))
}