plugins {
    `java-platform`
}

javaPlatform {
    allowDependencies()
}

dependencies {
    // The platform declares constraints on all components that
    // require alignment
    constraints {
        api(project(":mapstruct-kotlin-builder"))
        api(project(":mapstruct-kotlin-processor"))

        api("org.jetbrains.kotlinx:kotlinx-metadata-jvm:${project.extra["kotlinx-metadata.version"]}")
        api("com.google.auto.service:auto-service:${project.extra["auto-service-version"]}")
        api("com.squareup:javapoet:${project.extra["javapoet.version"]}")

        api("org.mapstruct:mapstruct:${project.extra["mapstruct.version"]}")
        api("org.mapstruct:mapstruct-processor:${project.extra["mapstruct.version"]}")

        api("org.mockito:mockito-core:${project.extra["mockito-core.version"]}")
        api("junit:junit:${project.extra["junit4.version"]}")
    }
}
