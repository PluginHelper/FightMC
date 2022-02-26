import kr.entree.spigradle.kotlin.lombok
import kr.entree.spigradle.kotlin.spigot
import kr.entree.spigradle.kotlin.spigotmc

plugins {
    java
    id("kr.entree.spigradle") version "2.3.2"
}

tasks.compileJava.get().options.encoding = "UTF-8"

repositories {
    mavenCentral()
    spigotmc()
}

dependencies {
    compileOnly(spigot("1.16.5"))
    compileOnly(lombok())
    annotationProcessor(lombok())
}
