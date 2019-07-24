import org.gradle.api.JavaVersion.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.github.pozo"
version = "1.0-SNAPSHOT"

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
    kotlin("kapt")
}

apply(plugin = "java-library")
apply(plugin = "kotlin")
apply(plugin = "kotlin-kapt")
apply(plugin = "maven-publish")

repositories {
    mavenLocal()
    mavenCentral()
    maven ( url = "https://kotlin.bintray.com/kotlinx" )
}

dependencies {
    implementation("org.mapstruct:mapstruct:${extra["mapstructVersion"]}")
    implementation("org.mapstruct:mapstruct-processor:${extra["mapstructVersion"]}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${extra["kotlin_version"]}")
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:${extra["kotlinxMetadataVersion"]}")
    implementation("com.google.auto.service:auto-service:${extra["autoServiceVersion"]}")
    implementation("com.squareup:javapoet:${extra["javaPoetVersion"]}")

    implementation(project(":mapstruct-kotlin-builder"))

    kapt ("com.google.auto.service:auto-service:${extra["autoServiceVersion"]}")

    testImplementation ("org.mockito:mockito-core:2.28.2")
    testImplementation ("junit:junit:4.12")
}

publishing {
    publications {
        create("maven", MavenPublication::class) {
            from(components["java"])
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    sourceCompatibility = VERSION_1_8

    sourceSets {
        val includeExtensions = setOf(
            "**/*.*"
        )

        named("main") {
            resources {
                includes += includeExtensions
            }
        }

        named("test") {
            resources {
                includes += includeExtensions
            }
        }
    }
}