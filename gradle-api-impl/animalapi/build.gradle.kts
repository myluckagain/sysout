plugins {
    kotlin("jvm") version "1.9.25"
    id("maven-publish")
}

group = "ru.sysout"
version = "0.0.1-SNAPSHOT"
val jarName = "animal-api"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
    }
}

// JAR с исходниками
tasks.register<Jar>("sourcesJar") {
    archiveBaseName.set(jarName)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}


tasks.test {
    useJUnitPlatform()
}

// Конфигурация публикации
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = jarName
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
    repositories {
        mavenLocal() // Публикация в локальный Maven репозиторий (~/.m2/repository)
    }
}