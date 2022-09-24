plugins {
    java
    `java-library`
    id ("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "xyz.daarkii"
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

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}