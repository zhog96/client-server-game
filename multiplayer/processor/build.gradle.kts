val kspVersion: String by project

plugins {
    kotlin("jvm")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:$kspVersion")
}