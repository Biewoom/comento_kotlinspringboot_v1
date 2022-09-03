plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // open API
    implementation("org.springdoc:springdoc-openapi-data-rest:1.5.12")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.12")
    implementation("org.springdoc:springdoc-openapi-kotlin:1.5.12")
}