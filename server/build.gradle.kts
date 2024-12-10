group = "com"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.9.21"
}

val ktorVersion: String by project

dependencies {
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-websockets:$ktorVersion")
    implementation("io.ktor:ktor-server-cio:$ktorVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}