plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "2.1.10"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("plugin.serialization") version "1.9.25"
    `java-test-fixtures`
}

group = "at.jku"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

extra["springCloudVersion"] = "2024.0.0"

repositories {
    mavenCentral()
}

subprojects {
    group = "at.jku"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.serialization")
    apply(plugin = "java-test-fixtures")

    repositories {
        mavenCentral()
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.2")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    dependencies {
        implementation("io.github.microutils:kotlin-logging:3.0.5")


        // mockk dependency
        testImplementation("io.mockk:mockk:1.13.16")
        testImplementation("org.assertj:assertj-core:3.27.3")

        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        constraints {
            implementation("io.swagger.core.v3:swagger-annotations:2.2.28")
            implementation("org.springframework.boot:spring-boot-starter-actuator")
            implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            implementation("org.springframework.boot:spring-boot-starter-data-rest")
            implementation("org.springframework.boot:spring-boot-starter-jdbc")
            implementation("org.springframework.boot:spring-boot-starter-security")
            implementation("org.springframework.boot:spring-boot-starter-validation")
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

            // kotlinx serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
            // jsoup...
            implementation("org.jsoup:jsoup:1.18.3")

            // cloud
            implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
            implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")

            // db
            implementation("org.flywaydb:flyway-core")
            implementation("org.flywaydb:flyway-database-postgresql")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            runtimeOnly("org.postgresql:postgresql")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("org.springframework.boot:spring-boot-testcontainers")
            testImplementation("org.springframework.security:spring-security-test")
            testImplementation("org.testcontainers:junit-jupiter")
            testImplementation("org.testcontainers:postgresql")
        }
    }



    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

/*allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}*/

/*
dependencies {
    constraints {

    }
}
*/


tasks.withType<Test> {
    useJUnitPlatform()
}
