plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.9.24"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
}

group = "com.gumroad.api"
version = "1.0.9"

repositories {
    mavenCentral()
}

dependencies {
    // GSON
    api("com.google.code.gson:gson:2.11.0")

    // Retrofit
    api("com.squareup.retrofit2:retrofit:2.11.0") {
        exclude(module = "okhttp")
    }
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.9.24")

    // Use the JUnit 5 integration.
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.jar {
    archiveBaseName.set("gumroad-api")
    manifest {
        attributes(mapOf(
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version
        ))
    }
}
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}