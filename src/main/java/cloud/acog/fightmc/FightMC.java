package cloud.acog.fightmc;

import cloud.acog.fightmc.manger.ManagerCommand;
import cloud.acog.fightmc.manger.ManagerListener;
import javafx.util.Pair;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FightMC extends JavaPlugin {

    FightMC plugin = null;

    @Override
    public void onEnable() {
        this.plugin = this;
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

    public final void registerCommands(Map<String, CommandExecutor> commands) {
        for (Map.Entry<String, CommandExecutor> entry : commands.entrySet()) {
            getCommand(entry.getKey()).setExecutor(entry.getValue());
        }
    }

    public void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
