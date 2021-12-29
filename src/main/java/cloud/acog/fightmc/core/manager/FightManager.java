package cloud.acog.fightmc.core.manager;

import cloud.acog.fightmc.core.data.FightData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FightManager {

    private final Map<String, FightData> fightDataMap = new HashMap<>();

    public void createFightData(Player player, String name) {
        FightData fightData = new FightData(
                player,
                name
        );
        this.fightDataMap.put(name, fightData);
    }

    public Map<String, FightData> getFightDataMap() {
        return this.fightDataMap;
    }

    public boolean hasFightData(String name) {
        return this.fightDataMap.containsKey(name);
    }
}
