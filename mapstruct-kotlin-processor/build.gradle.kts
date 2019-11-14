import org.gradle.api.JavaVersion.VERSION_1_8
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
    maven(url = "https://kotlin.bintray.com/kotlinx")
}

dependencies {
    implementation(platform(project(":mapstruct-kotlin-platform")))
    kapt(platform(project(":mapstruct-kotlin-platform")))

    implementation(kotlin("stdlib-jdk8"))

    implementation("org.mapstruct:mapstruct")
    implementation("org.mapstruct:mapstruct-processor")

    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm")
    implementation("com.google.auto.service:auto-service")
    implementation("com.squareup:javapoet")

    implementation(project(":mapstruct-kotlin-builder"))

    kapt("com.google.auto.service:auto-service")

    testImplementation("org.mockito:mockito-core")
    testImplementation("junit:junit")
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
