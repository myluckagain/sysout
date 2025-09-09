plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"

    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("maven-publish")
}

group = "ru.sysout"
version = "0.0.1-SNAPSHOT"
val artifactName = "impl-animalclient"
kotlin{
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.set(listOf("-Xjsr305=strict"))
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("ru.sysout:animal-api:0.0.1-SNAPSHOT")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

// Отключаем BootJar
tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

// Включаем создание обычного JAR
tasks.getByName<Jar>("jar") {
    enabled = true
    archiveClassifier.set("") // убирает "-plain"
}


tasks.register<Jar>("sourcesJar") {
    archiveBaseName.set(artifactName)
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}


// Конфигурация публикации
publishing {
    repositories {
        mavenLocal() // Публикация в локальный Maven репозиторий (~/.m2/repository)
    }
    publications {
        create<MavenPublication>("mavenJava") {

            groupId = project.group.toString()
            artifactId = artifactName
            version = project.version.toString()

            from(components["java"])
            artifact(tasks["sourcesJar"])

        }
    }

}