# MojangAPI-Wrapper
***
The official documentation can be found here: https://wiki.vg/Mojang_API

## Usage
````java
//you can access everything with the MojangAPI class
MojangAPI.*
````

***
## Gradle

Add the repository to your build file:
````kotlin
repositories {
    maven("https://repo.aysu.tv/repository/releases/")
}
````

Add the dependency to your build file:
````kotlin
dependencies {
    implementation("xyz.daarkii.mojangapi:MojangAPI:1.0.0")
}
````

***
## Maven

Add the repository to your pom:
````xml
<repositories>
    <repository>
        <id>aysu-repo</id>
        <url>https://repo.aysu.tv/repository/releases/</url>
    </repository>
</repositories>
````

Add the dependency to your pom:
````xml
<dependencies>
    <dependency>
        <groupId>xyz.daarkii.mojangapi</groupId>
        <artifactId>MojangAPI</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
````