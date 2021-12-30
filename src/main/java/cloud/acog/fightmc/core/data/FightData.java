package cloud.acog.fightmc.core.data;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FightData {

    private final Player player;
    private final String name;

    private final boolean state = false;
    private Location firstLocation = null;
    private Location secondLocation = null;

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


    /**
     * setter
     */
    public void setFirstLocation(Location location) {
        this.firstLocation = location;
    }

    public void setSecondLocation(Location location) {
        this.secondLocation = location;
    }

    public Location getFirstLocation() {
        return this.firstLocation;
    }

    public Location getSecondLocation() {
        return this.secondLocation;
    }

}
