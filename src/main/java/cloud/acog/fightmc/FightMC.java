package cloud.acog.fightmc;

import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.core.manager.UserManager;
import cloud.acog.fightmc.system.system.SystemListener;
import cloud.acog.fightmc.core.manager.SystemManager;
import cloud.acog.fightmc.system.manger.ManagerCommand;
import cloud.acog.fightmc.system.manger.ManagerListener;
import cloud.acog.fightmc.system.user.UserCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;

import static cloud.acog.fightmc.library.bukkit.Plugin.registerCommands;
import static cloud.acog.fightmc.library.bukkit.Plugin.registerListener;

public class FightMC extends JavaPlugin {

    public static FightMC plugin = null;

    private final SystemManager systemManager = new SystemManager();
    private final FightManager fightManager = new FightManager();
    private final UserManager userManager = new UserManager();

    @Override
    public void onEnable() {
        plugin = this;
        load();
        Arrays.asList(
                "MineCraft Fight Plugin",
                "- Version : 1.14.4",
                "- Plugin creator is Acog"
        ).forEach(getLogger()::info);

        registerCommands(
               new HashMap<String, CommandExecutor>() {{
                   put("FightManager", new ManagerCommand(fightManager));
                   put("Fight", new UserCommand(fightManager, userManager, systemManager));
               }}
        );
        registerListener(
                new ManagerListener(systemManager, fightManager, userManager),
                new SystemListener(systemManager, userManager)
        );

    }

    @Override
    public void onDisable() {
        save();
        Arrays.asList(
                "FightMC Plugin System Down",
                "- Plugin creator is Acog"
        ).forEach(getLogger()::info);
    }

    public void load() {
        fightManager.load();
        userManager.load();
    }

    public void save() {
        fightManager.save();
        userManager.save();
    }
}
