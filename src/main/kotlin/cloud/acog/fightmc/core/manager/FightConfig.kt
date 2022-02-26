package cloud.acog.fightmc.core.manager

import cloud.acog.fightmc.core.data.AreaData
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.util.*

class FightConfig(val fightAreaMap: MutableMap<String, AreaData>) {

    companion object {
        fun loadFightConfig(yaml: YamlConfiguration) : FightConfig {
            val map: MutableMap<String, AreaData> = mutableMapOf()
            val section = yaml.getConfigurationSection("AreaList") ?: return FightConfig(map)
            section.getKeys(false).forEach { areaName ->
                map[areaName] = AreaData(
                    areaName,
                    UUID.fromString(yaml.getString("AreaList.${areaName}.creatorUUID")),
                    UUID.fromString(yaml.getString("AreaList.${areaName}.firstPlayerUUID")),
                    UUID.fromString(yaml.getString("AreaList.${areaName}.secondPlayerUUID")),
                    yaml.getInt("AreaList.${areaName}.areaTime"),
                    yaml.getBoolean("AreaList.${areaName}.state"),
                    yaml.getBoolean("AreaList.${areaName}.managerState"),
                    yaml.getLocation("AreaList.${areaName}.watchPlaceLocation"),
                    yaml.getLocation("AreaList.${areaName}.spawnLocation"),
                    yaml.getLocation("AreaList.${areaName}.areaFirstLocation"),
                    yaml.getLocation("AreaList.${areaName}.areaSecondLocation")
                )
            }
            return FightConfig(map)
        }

        fun saveFightConfig(fightConfig: FightConfig) : YamlConfiguration {
            return YamlConfiguration().apply {
                fightConfig.fightAreaMap.forEach { (areaName, areaData) ->
                    set("AreaList.${areaName}.areaName", areaData.areaName)
                    set("AreaList.${areaName}.creatorUUID", areaData.creatorUUID)
                    set("AreaList.${areaName}.firstPlayerUUID", areaData.firstPlayerUUID)
                    set("AreaList.${areaName}.secondPlayerUUID", areaData.secondPlayerUUID)
                    set("AreaList.${areaName}.areaTime", areaData.areaTime)
                    set("AreaList.${areaName}.state", areaData.state)
                    set("AreaList.${areaName}.managerState", areaData.managerState)
                    set("AreaList.${areaName}.watchPlaceLocation", areaData.watchPlaceLocation)
                    set("AreaList.${areaName}.spawnLocation", areaData.spawnLocation)
                    set("AreaList.${areaName}.areaFirstLocation", areaData.areaFirstLocation)
                    set("AreaList.${areaName}.areaSecondLocation", areaData.areaSecondLocation)
                }
            }
        }
    }

    fun fightAreaCreate(areaName: String, creator: Player) {
        fightAreaMap[areaName] = AreaData(areaName, creator.uniqueId)
    }

    fun hasFightAreaData(areaName: String) : Boolean = fightAreaMap.containsKey(areaName)

    fun getFightAreaData(areaName: String) : AreaData = fightAreaMap[areaName]!!

    fun applyFightAreaData(areaName: String, areaData: AreaData) {
        fightAreaMap[areaName] = areaData
    }

}