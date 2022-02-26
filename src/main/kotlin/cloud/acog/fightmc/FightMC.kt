package cloud.acog.fightmc

import org.bukkit.plugin.java.JavaPlugin

class FightMC : JavaPlugin() {

    override fun onEnable() {
        listOf(
            "MineCraft Fight Plugin",
            "- Version : 1.14.4",
            "- Plugin creator is Acog"
        ).forEach(logger::info)
    }

    override fun onDisable() {
        listOf(
            "FightMC Plugin System Down",
            "- Plugin creator is Acog"
        ).forEach(logger::info)
    }

}