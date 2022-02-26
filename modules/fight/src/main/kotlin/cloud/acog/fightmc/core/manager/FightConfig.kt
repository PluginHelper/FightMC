package cloud.acog.fightmc.core.manager

import cloud.acog.fightmc.FightMC
import cloud.acog.fightmc.core.data.AreaData
import cloud.acog.library.items.item
import cloud.acog.library.items.meta
import cloud.acog.library.items.setName
import cloud.acog.library.string.colorize
import cloud.acog.library.string.message
import io.typecraft.bukkit.view.ClickEvent
import io.typecraft.bukkit.view.ViewAction
import io.typecraft.bukkit.view.ViewItem
import io.typecraft.bukkit.view.page.PageContext
import io.typecraft.bukkit.view.page.PageViewAction
import io.typecraft.bukkit.view.page.PageViewAction.SetPage
import io.typecraft.bukkit.view.page.PageViewControl
import io.typecraft.bukkit.view.page.PageViewLayout
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.util.*
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collectors
import java.util.stream.IntStream

class FightConfig(val players: HashSet<UUID>, val fightAreaMap: MutableMap<String, AreaData>) {

    companion object {
        fun loadFightConfig(yaml: YamlConfiguration) : FightConfig {
            val map: MutableMap<String, AreaData> = mutableMapOf()
            val section = yaml.getConfigurationSection("AreaList") ?: return FightConfig(hashSetOf(), map)
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
            return FightConfig(hashSetOf(), map)
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

    /**
     * GUI
     */

    fun getFightAreaListInventory(player: Player) : PageViewLayout {
        val pagingContents: MutableList<Supplier<ViewItem>> = mutableListOf()
        fightAreaMap.forEach { (areaName, areaData) ->
            pagingContents.add(Supplier<ViewItem> {
                ViewItem(item(Material.ENCHANTED_BOOK) {
                    meta {
                        setName(FightMC.infoItemDisplay)
                        lore = FightMC.infoItemLore
                    }
                }) { event ->
                    val player: Player = event.player
                    player.closeInventory()

                    player.message("대충 오픈하라는 메세지")
                    ViewAction.NOTHING
                }
            })
        }

        val cSize: Int = (6 - 1) * 9
        val slots = IntStream.range(0, cSize).boxed().collect(Collectors.toList())
        val controls: MutableMap<Int, Function<PageContext, PageViewControl>> = HashMap()
        controls[cSize] = Function { ctx: PageContext ->
            PageViewControl(item(Material.STONE_BUTTON, ctx.page) {
                    meta {
                        setDisplayName("§e이전페이지 [ ${ctx.page} / ${ctx.maxPage} ]")
                        lore = listOf("", "&f클릭시 다음페이지로 이동합니다.".colorize, "")
                    }}
            ) { SetPage(ctx.page + 1) }
        }
        controls[cSize + 3] = Function { ctx: PageContext ->
            PageViewControl(item(Material.EMERALD, 1) {
                meta { setDisplayName("§c대전장 삭제 페이지") }
            }) { e ->
                val player: Player = e.player
                PageViewAction.NOTHING
            }
        }
        controls[cSize + 4] = Function { ctx: PageContext ->
            PageViewControl(item(Material.ANVIL, 1) {
                meta { setDisplayName("§a대전장 생성") }
            }) { e ->
                val player: Player = e.player
                PageViewAction.NOTHING
            }
        }
        controls[cSize + 5] = Function { ctx: PageContext ->
            PageViewControl(item(Material.EMERALD, 1) {
                meta { setDisplayName("§a대전장 수정") }
            }) { e ->
                val player: Player = e.player
                PageViewAction.NOTHING
            }
        }
        controls[cSize + 8] = Function { ctx: PageContext ->
            PageViewControl(item(Material.STONE_BUTTON, ctx.page) {
                    meta {
                        setDisplayName("§e다음페이지 [ ${ctx.page} / ${ctx.maxPage} ]")
                        lore = listOf("", "&f클릭시 다음페이지로 이동합니다.".colorize, "")
                    }}
            ) { SetPage(ctx.page + 1) }
        }
        return PageViewLayout("대전장 관리 :: ${player.name}", 6, pagingContents, slots, controls)
    }
}