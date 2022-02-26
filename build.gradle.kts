import kr.entree.spigradle.kotlin.spigot
import kr.entree.spigradle.kotlin.spigotmc

plugins {
    java
    kotlin("jvm") version "1.6.10"
    id("kr.entree.spigradle") version "2.3.2"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "cloud.acog"
version = "1.0-SNAPSHOT"

tasks.compileJava.get().options.encoding = "UTF-8"

repositories {
    mavenCentral()
    mavenLocal()
    spigotmc()
}

dependencies {
    compileOnly(spigot("1.16.5"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.pluginhelper:PluginHelper:1.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

spigot {
    debug {
        buildVersion = "1.16.5"
    }

    description = "Minecraft Plugin Support API"
    load = kr.entree.spigradle.data.Load.STARTUP
}
