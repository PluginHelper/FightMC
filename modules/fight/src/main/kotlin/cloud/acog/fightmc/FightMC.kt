package cloud.acog.fightmc

import cloud.acog.fightmc.core.manager.FightConfig
import cloud.acog.fightmc.core.manager.PlayerConfig
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class FightMC : JavaPlugin() {

    var fightConfig: FightConfig = FightConfig(hashSetOf(), mutableMapOf())
    var playerConfig: PlayerConfig = PlayerConfig(mutableMapOf())

    companion object {
        var infoItemDisplay: String = ""
        var infoItemLore: List<String> = listOf()
    }

    override fun onEnable() {
        listOf(
            "MineCraft Fight Plugin",
            "- Version : 1.14.4",
            "- Plugin creator is Acog"
        ).forEach(logger::info)

        runCatching {
            loadConfig(getConfigFile())
            fightConfig = FightConfig.loadFightConfig(YamlConfiguration.loadConfiguration(getFightConfigFile()))
            playerConfig = PlayerConfig.loadPlayerConfig(YamlConfiguration.loadConfiguration(getPlayerConfigFile()))
        }.recoverCatching {
            when (it) {
                is NullPointerException -> {
                    listOf(
                        "플러그인의 모든 Config 데이터를 확인해주세요.",
                        "→ 확인이 불가능 하다면 해당 Config 를 삭제해주세요. :: ${it.message}"
                    ).forEach(logger::info)
                }
            }
        }
    }

    override fun onDisable() {
        listOf(
            "FightMC Plugin System Down",
            "- Plugin creator is Acog"
        ).forEach(logger::info)

        FightConfig.saveFightConfig(fightConfig).save(getFightConfigFile())
        PlayerConfig.savePlayerConfig(playerConfig).save(getPlayerConfigFile())
    }

    fun loadConfig(file : File) {
        if (!file.exists()) {
            config.apply {
                set("option.infoItem.display", "[ {fightAreaName} ]")
                set(
                    "option.infoItem.lore", listOf(
                        "",
                        " 플라이 쿠폰 : {time}",
                        ""
                    )
                )
            }
            saveConfig()
        }

        runCatching {
            infoItemDisplay = config.getString("option.infoItem.display")!!
            infoItemLore = config.getStringList("option.infoItem.lore")
        }.recoverCatching {
            if(it is NullPointerException) throw NullPointerException("오류 파일 : /FightMC/config.yml")
        }
    }

    private fun getFightConfigFile() = File("${dataFolder}/FightConfig", "fightConfig.yml")
    private fun getPlayerConfigFile() = File("${dataFolder}/PlayerConfig", "playerConfig.yml")
    private fun getConfigFile() = File(dataFolder, "config.yml")
}