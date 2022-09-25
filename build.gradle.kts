plugins {
    java
    `java-library`
    `maven-publish`
    id ("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "xyz.daarkii.mojangapi"
version = "1.0.0"

apply {
    plugin("java")
    plugin("com.github.johnrengelman.shadow")
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.konghq:unirest-java:3.11.09")
    compileOnlyApi("org.jetbrains:annotations:23.0.0")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

publishing {

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "nexus"

            url = if(version.toString().contains("SNAPSHOT"))
                uri("https://repo.aysu.tv/repository/snapshots/")
            else
                uri("https://repo.aysu.tv/repository/releases/")

            credentials {
                username = System.getenv("NEXUS_USERNAME")
                password = System.getenv("NEXUS_PASSWORD")
            }
        }
    }
}