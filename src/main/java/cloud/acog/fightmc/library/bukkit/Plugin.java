package cloud.acog.fightmc.library.bukkit;

import cloud.acog.fightmc.FightMC;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;

import java.util.Map;

public class Plugin {

    public static void registerCommands(Map<String, CommandExecutor> commands) {
        for (Map.Entry<String, CommandExecutor> entry : commands.entrySet()) {
            FightMC.plugin.getCommand(entry.getKey()).setExecutor(entry.getValue());
        }
    }

    public static void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            FightMC.plugin.getServer().getPluginManager().registerEvents(listener, FightMC.plugin);
        }
    }

}
