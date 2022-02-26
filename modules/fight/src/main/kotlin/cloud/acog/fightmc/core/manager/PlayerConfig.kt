package cloud.acog.fightmc.core.manager

import cloud.acog.fightmc.core.data.PlayerData
import org.bukkit.configuration.file.YamlConfiguration
import java.util.UUID

class PlayerConfig(val playerDataMap: MutableMap<UUID, PlayerData>) {

    companion object {
        fun loadPlayerConfig(yaml: YamlConfiguration) : PlayerConfig {
            val map: MutableMap<UUID, PlayerData> = mutableMapOf()
            val section = yaml.getConfigurationSection("Players") ?: return PlayerConfig(map)
            section.getKeys(false).forEach { uuid ->
                map[UUID.fromString(uuid)] = PlayerData(
                    UUID.fromString(uuid), yaml.getInt("Players.${uuid}.winScore"), yaml.getInt("Players.${uuid}.failScore")
                )
            }
            return PlayerConfig(map)
        }

        fun savePlayerConfig(playerConfig: PlayerConfig) : YamlConfiguration {
            return YamlConfiguration().apply {
                playerConfig.playerDataMap.forEach { (uuid, playerData) ->
                    set("Players.${uuid}.winScore", playerData.winScore)
                    set("Players.${uuid}.failScore", playerData.failScore)
                }
            }
        }
    }

    fun createPlayerData(uuid: UUID) {
        playerDataMap[uuid] = PlayerData(uuid)
    }

    fun hasPlayerData(uuid: UUID) = playerDataMap.containsKey(uuid)

    fun getPlayerData(uuid: UUID) = playerDataMap[uuid]!!

    fun applyPlayerData(uuid: UUID, playerData: PlayerData) {
        playerDataMap[uuid] = playerData
    }
}