import kr.entree.spigradle.kotlin.spigot

plugins {
    id("kr.entree.spigradle") version "2.3.2"
    kotlin("jvm") version "1.6.10"
}

tasks.compileJava.get().options.encoding = "UTF-8"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(spigot("1.16.5"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}