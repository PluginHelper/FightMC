package cloud.acog.fightmc.library.bukkit;

import org.bukkit.Bukkit;

public class Player {
    public static Boolean hasPlayer(String name) {
        for (org.bukkit.entity.Player players : Bukkit.getOnlinePlayers()) {
            if(players.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
