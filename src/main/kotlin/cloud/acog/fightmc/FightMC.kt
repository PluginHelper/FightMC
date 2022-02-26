package cloud.acog.fightmc

import cloud.acog.fightmc.core.manager.FightConfig
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class FightMC : JavaPlugin() {

    var fightConfig: FightConfig = FightConfig(mutableMapOf())

    override fun onEnable() {
        listOf(
            "MineCraft Fight Plugin",
            "- Version : 1.14.4",
            "- Plugin creator is Acog"
        ).forEach(logger::info)

        fightConfig = FightConfig.loadFightConfig(YamlConfiguration.loadConfiguration(getFightConfigFile()))
    }

    override fun onDisable() {
        listOf(
            "FightMC Plugin System Down",
            "- Plugin creator is Acog"
        ).forEach(logger::info)

        FightConfig.saveFightConfig(fightConfig).save(getFightConfigFile())
    }

    private fun getFightConfigFile() = File("${dataFolder}/FightConfig", "fightConfig.yml")
}