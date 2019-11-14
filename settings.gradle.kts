pluginManagement {
    repositories {
        maven("http://artifactory.solidsecurity:8081/artifactory/gradle-dev-local")
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
}

rootProject.name = "mapstruct-kotlin"

include("mapstruct-kotlin-builder")
include("mapstruct-kotlin-processor")
include("mapstruct-kotlin-platform")
