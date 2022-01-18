package cloud.acog.fightmc.core.manager;

import cloud.acog.fightmc.core.data.FightData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FightManager {

    private Boolean fightPluginState = true;
    private final Map<String, FightData> fightDataMap = new HashMap<>();

    public void createFightData(Player player, String name) {
        FightData fightData = new FightData(
                player.getUniqueId(),
                name
        );
        this.fightDataMap.put(name, fightData);
    }

    /**
     * FightDataMap
     */
    public Map<String, FightData> getFightDataMap() {return this.fightDataMap; }

    public boolean hasFightData(String name) {return this.fightDataMap.containsKey(name); }

    public FightData getFightData(String name) {return fightDataMap.get(name); }

    /**
     * FightPluginState
     */
    public boolean getFightPluginState() { return this.fightPluginState; }

    public void setFightPluginState(boolean state) { this.fightPluginState = state; }


    /**
     * sava & load
     */
    public void load() {

    }

    public void save() {
        // 마지막에 작업
    }
}
