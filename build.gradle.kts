plugins {
	kotlin("jvm") version "1.9.25"
    kotlin("kapt") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
	id("org.springframework.boot") version "3.5.5"
	id("io.spring.dependency-management") version "1.1.7"
    id("org.openapi.generator") version "7.15.0"
    id("io.swagger.core.v3.swagger-gradle-plugin") version "2.2.36"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "Social network for polyglots"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.moshi:moshi:1.15.2")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.36")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
    implementation("com.h2database:h2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Prevent kapt from processing OpenAPI-generated code
kapt {
    correctErrorTypes = true
    includeCompileClasspath = false

    // exclude the generated sources
    javacOptions {
        option("-Xmaxerrs", 500)
    }
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    dependsOn(tasks.named("openApiGenerate"))
    // Prevent kapt from processing OpenAPI-generated code
    kotlinOptions {
        freeCompilerArgs += listOf("-Xskip-metadata-version-check")
    }
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    library.set("spring-boot")
    inputSpec.set("$rootDir/src/main/resources/specs/user-spec-api.yaml") // your spec path
    outputDir.set("$buildDir/generated")
    packageName.set("com.example.generated")

    // Prevent test stubs if you don’t need them
    generateApiTests.set(false)               // optional
    generateModelTests.set(false)             // optional

    // Key option: generate interfaces only
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true",
            "useTags" to "true", // optional: groups controllers by tag
            "serializableModel" to "true", // generates plain models without extra funky annotations
            "useBeanValidation" to "true",         // makes models cleaner
            "annotationLibrary" to "SWAGGER2"       // ensures only @JsonProperty on fields
        )
    )
}

sourceSets {
    main {
        kotlin {
            srcDir("$buildDir/generated/src/main/kotlin")
            exclude("**/*.java")
        }
    }
    create("openApi") {
        kotlin.srcDir("$buildDir/generated/src/main/kotlin")
    }
}

configurations {
    // kapt should not use the openApi set
    kapt.get().exclude(group = "com.example.model")
}


