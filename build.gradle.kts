plugins {
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")
    implementation("com.charleskorn.kaml:kaml:0.26.0")
    testImplementation("io.kotest:kotest-runner-junit5:4.3.1")
}
