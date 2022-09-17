
plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

val KOTEST_VERSION = "5.3.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("com.h2database:h2")
    runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // open API
    implementation("org.springdoc:springdoc-openapi-data-rest:1.5.12")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.12")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.12")

    // KLogger
    implementation("io.github.microutils:kotlin-logging:2.1.21")

    // log4j2
    implementation("org.springframework.boot:spring-boot-starter-log4j2")

    modules {
        module("org.springframework.boot:spring-boot-starter-logging") {
            replacedBy("org.springframework.boot:spring-boot-starter-log4j2", "Use Log4j2 instead of Logback")
        }
    }

    // h2 DB for Test
    testRuntimeOnly("com.h2database:h2")

    // mockk
    testImplementation("io.mockk:mockk:1.9.3")
    // Kotest
    testImplementation("io.kotest:kotest-runner-junit5:${KOTEST_VERSION}")
    testImplementation("io.kotest:kotest-assertions-core:${KOTEST_VERSION}")

    // SpringMockk
    testImplementation("com.ninja-squad:springmockk:3.1.1")
}