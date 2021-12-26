package cloud.acog.fightmc;

import javafx.util.Pair;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

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

        );
    }

    @Override
    public void onDisable() {
        Arrays.asList(
                "FightMC Plugin System Down",
                "- Plugin creator is Acog"
        ).forEach(getLogger()::info);
    }

    public void registerCommands(Pair<String, CommandExecutor>... commands) {
        for (Pair<String, CommandExecutor> entry : commands) {
            getCommand(entry.getKey()).setExecutor(entry.getValue());
        }
    }
}
