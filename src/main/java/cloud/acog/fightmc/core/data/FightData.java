package cloud.acog.fightmc.core.data;

import org.bukkit.entity.Player;

public class FightData {

    private final Player player;
    public final String name;

    public FightData(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getName() {
        return this.name;
    }

}
