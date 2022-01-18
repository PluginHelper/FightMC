package cloud.acog.fightmc.core.data;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public class FightData {

    private final UUID creator;
    private final String name;

    private final boolean state = false;

    private Location firstLocation = null;
    private Location secondLocation = null;

    private Location spawnLocation = null;
    private Location seeLocation = null;

    public FightData(UUID uuid, String name) {
        this.creator = uuid;
        this.name = name;
    }

}
