package cloud.acog.fightmc;

import cloud.acog.fightmc.manger.ManagerCommand;
import cloud.acog.fightmc.manger.ManagerListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

import static cloud.acog.fightmc.library.bukkit.Plugin.registerCommands;
import static cloud.acog.fightmc.library.bukkit.Plugin.registerListener;

public class FightMC extends JavaPlugin {

    public static FightMC plugin = null;

    @Override
    public void onEnable() {
        plugin = this;
        Arrays.asList(
                "MineCraft Fight Plugin",
                "- Version : 1.14.4",
                "- Plugin creator is Acog"
        ).forEach(getLogger()::info);

        registerCommands(
               new HashMap<String, CommandExecutor>() {{
                   put("FightManager", new ManagerCommand());
               }}
        );
        registerListener(
                new ManagerListener()
        );

    }

    @Override
    public void onDisable() {
        Arrays.asList(
                "FightMC Plugin System Down",
                "- Plugin creator is Acog"
        ).forEach(getLogger()::info);
    }
}
