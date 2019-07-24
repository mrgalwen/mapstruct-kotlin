group = "com.github.pozo"
version = "1.0-SNAPSHOT"

buildscript {
    extra["kotlin_version"] = "1.3.21"
    extra["kotlinxMetadataVersion"] = "0.0.5"
    extra["kotlinBuilderAnnotationVersion"] = "1.0-SNAPSHOT"
    extra["autoServiceVersion"] = "1.0-rc4"
    extra["mapstructVersion"] = "1.3.0.Final"
    extra["javaPoetVersion"] = "1.11.1"

    repositories {
        mavenCentral()
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${extra["kotlin_version"]}")
    }
}

allprojects {
    extra["kotlin_version"] = "1.3.21"
    extra["kotlinxMetadataVersion"] = "0.0.5"
    extra["kotlinBuilderAnnotationVersion"] = "1.0-SNAPSHOT"
    extra["autoServiceVersion"] = "1.0-rc4"
    extra["mapstructVersion"] = "1.3.0.Final"
    extra["javaPoetVersion"] = "1.11.1"
}