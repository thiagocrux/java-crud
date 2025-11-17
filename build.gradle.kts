plugins {
    id("java")
    id("application")
}

group = "com.todoapp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
}

application {
    mainClass = "com.todoapp.Main"
}

tasks.test {
    useJUnitPlatform()
}