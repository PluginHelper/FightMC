import kr.entree.spigradle.kotlin.spigot
import kr.entree.spigradle.kotlin.spigotmc

plugins {
    java
    id("kr.entree.spigradle") version "2.2.4"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "cloud.acog"
version = "1.0-SNAPSHOT"

tasks.compileJava.get().options.encoding = "UTF-8"

repositories {
    mavenCentral()
    spigotmc()
}

dependencies {
    compileOnly(spigot("1.14.4"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

spigot {
    debug {
        buildVersion = "1.14.4"
    }

    description = "Minecraft Plugin Support API"
    load = kr.entree.spigradle.data.Load.STARTUP
    commands {
        create("FightManager") {
            description = "Manager Command"
            permission = "fightmc.manger"
            permissionMessage = "You do not have the permission!!!"
        }
        create("FightUser") {
            aliases = listOf("대전", "맞짱")
            description = "User Command"
            permission = "fightmc.user"
            permissionMessage = "You do not have the permission!!!"
        }
    }

    permissions {
        create("fightmc.manger") {
            description = "MineFrame manager permission"
            defaults = "op"
        }
        create("fightmc.user") {
            description = "MineFrame manager permission"
            defaults = "op"
        }
    }
}
