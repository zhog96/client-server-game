group = "com"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.9.21"
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}