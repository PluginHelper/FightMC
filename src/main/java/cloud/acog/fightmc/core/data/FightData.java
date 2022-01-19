package cloud.acog.fightmc.core.data;

import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public class FightData { // 완성

    private final UUID creator;
    private final String name;

    private Boolean state = true;
    private Boolean manageState = true;

    private Integer fightTime = 300;

    private Location firstLocation = null;
    private Location secondLocation = null;

    private Location spawnLocation = null;
    private Location seeLocation = null;

    private UUID firstPlayer = null;
    private UUID secondPlayer = null;

    public FightData(UUID uuid, String name) {
        this.creator = uuid;
        this.name = name;
    }

}
