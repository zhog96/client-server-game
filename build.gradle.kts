plugins {
    kotlin("jvm") version "1.9.21"
}

kotlin {
    jvmToolchain(21)
}

allprojects {
    repositories {
        mavenLocal(); mavenCentral(); google(); gradlePluginPortal()
    }
}