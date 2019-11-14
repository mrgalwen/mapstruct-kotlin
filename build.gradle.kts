import io.gitlab.arturbosch.detekt.Detekt

group = "com.github.pozo"
version = "1.0-SNAPSHOT"

plugins {
    base
    id("io.gitlab.arturbosch.detekt") version "1.1.1"
}

buildscript {
    extra["kotlin_version"] = "1.3.21"
    extra["kotlinx-metadata.version"] = "0.1.0"
    extra["kotlin-builder-annotation.version"] = "1.0-SNAPSHOT"
    extra["auto-service-version"] = "1.0-rc6"
    extra["mapstruct.version"] = "1.3.1.Final"
    extra["javapoet.version"] = "1.11.1"
    extra["mockito-core.version"] = "2.28.2"
    extra["junit4.version"] = "4.12"

    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.extra["kotlin_version"]}")
    }
}

allprojects {
    extra["kotlin_version"] = "1.3.21"
    extra["kotlinx-metadata.version"] = "0.1.0"
    extra["kotlin-builder-annotation.version"] = "1.0-SNAPSHOT"
    extra["auto-service-version"] = "1.0-rc6"
    extra["mapstruct.version"] = "1.3.1.Final"
    extra["javapoet.version"] = "1.11.1"
    extra["mockito-core.version"] = "2.28.2"
    extra["junit4.version"] = "4.12"
}

val detektVersion = "1.1.1"
val detekt: Configuration by configurations.getting {}
val detektPlugins: Configuration by configurations.getting {}

dependencies {
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
}

val detektFormat by tasks.registering(Detekt::class) {
    description = "Reformats whole code base."
    parallel = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true
    autoCorrect = true
    ignoreFailures = false
    setSource(files(projectDir))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/buildSrc/**")
    exclude("**/build/**")
    exclude("**/.gradle/**")

    config.setFrom(
        rootProject.files("detekt/detekt-formatting-config.yml")
    )

    reports {
        xml { enabled = false }
        html { enabled = false }
    }
}

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs over whole code base without the starting overhead for each module."
    parallel = true
    buildUponDefaultConfig = true
    ignoreFailures = false
    setSource(files(projectDir))
    config.setFrom(
        rootProject.files(
            "detekt/default-detekt-config.yml",
            "detekt/detekt-formatting-config.yml"
        )
    )
    include("**/*.kt")
    exclude("**/resources/**")
    exclude("**/buildSrc/**")
    exclude("**/build/**")
    exclude("**/test/**")
    exclude("**/.gradle/**")

    baseline.set(rootProject.file("detekt/baseline.xml"))

    reports {
        xml.enabled = false
        html.enabled = false
    }
}
