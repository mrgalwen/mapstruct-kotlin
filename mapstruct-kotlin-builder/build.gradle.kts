import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.github.pozo"
version = "1.0-SNAPSHOT"

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(platform(project(":mapstruct-kotlin-platform")))

    implementation(kotlin("stdlib-jdk8"))
}

publishing {
    publications {
        create("maven", MavenPublication::class) {
            groupId = group.toString()
            artifactId = "mapstruct-kotlin"
            version = version

            from(components["java"])
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
