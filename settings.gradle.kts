pluginManagement {
    repositories {
        maven("http://artifactory.solidsecurity:8081/artifactory/gradle-dev-local")
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
}

enableFeaturePreview("IMPROVED_POM_SUPPORT")
enableFeaturePreview("STABLE_PUBLISHING")

rootProject.name = "mapstruct-kotlin"

include("mapstruct-kotlin-builder")
include("mapstruct-kotlin-processor")
