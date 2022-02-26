package cloud.acog.fightmc

import cloud.acog.fightmc.core.manager.FightConfig
import cloud.acog.fightmc.core.manager.PlayerConfig
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class FightMC : JavaPlugin() {

    var fightConfig: FightConfig = FightConfig(mutableMapOf())
    var playerConfig: PlayerConfig = PlayerConfig(mutableMapOf())

    override fun onEnable() {
        listOf(
            "MineCraft Fight Plugin",
            "- Version : 1.14.4",
            "- Plugin creator is Acog"
        ).forEach(logger::info)

        fightConfig = FightConfig.loadFightConfig(YamlConfiguration.loadConfiguration(getFightConfigFile()))
        playerConfig = PlayerConfig.loadPlayerConfig(YamlConfiguration.loadConfiguration(getPlayerConfigFile()))
    }

    override fun onDisable() {
        listOf(
            "FightMC Plugin System Down",
            "- Plugin creator is Acog"
        ).forEach(logger::info)

        FightConfig.saveFightConfig(fightConfig).save(getFightConfigFile())
        PlayerConfig.savePlayerConfig(playerConfig).save(getPlayerConfigFile())
    }

    private fun getFightConfigFile() = File("${dataFolder}/FightConfig", "fightConfig.yml")

    private fun getPlayerConfigFile() = File("${dataFolder}/PlayerConfig", "playerConfig.yml")

}