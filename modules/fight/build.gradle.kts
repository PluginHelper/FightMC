import kr.entree.spigradle.kotlin.spigot

plugins {
    id("kr.entree.spigradle") version "2.3.2"
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

tasks.compileJava.get().options.encoding = "UTF-8"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(spigot("1.16.5"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":library"))
    implementation(project(":bukkit-view"))

}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        configurations = listOf(project.configurations["runtimeClasspath"], project.configurations.shadow.get())
    }
}