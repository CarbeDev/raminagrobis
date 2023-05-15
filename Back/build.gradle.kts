import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.3"
    id("io.spring.dependency-management") version "1.1.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("jvm") version "1.7.22"

}

group = "com.raminagrobis"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.3")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.3")
    implementation("org.springframework.boot:spring-boot-starter-security:3.0.3")
    implementation("org.postgresql:postgresql:42.5.4")
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
    implementation("org.springframework.plugin:spring-plugin-core:3.0.0")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-gson:0.11.5")
    implementation("org.passay:passay:1.6.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
