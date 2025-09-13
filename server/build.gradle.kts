plugins {
    id("java")
}

group = "com.sji"
version = "1.0-SNAPSHOT"

java {
    modularity.inferModulePath = true
}
repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}