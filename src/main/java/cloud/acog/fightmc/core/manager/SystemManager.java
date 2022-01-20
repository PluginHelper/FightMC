package cloud.acog.fightmc.core.manager;


import cloud.acog.fightmc.core.data.SystemEnum;
import cloud.acog.fightmc.library.bukkit.Pair;
import org.bukkit.entity.Player;
import org.bukkit.entity.PufferFish;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SystemManager {

    private final Map<UUID, Pair<SystemEnum, String>> playerData = new HashMap<>();
    private final Map<UUID, UUID> inviteData = new HashMap<>();


    /**
     * PlayerData
     */
    public boolean hasPlayerData(UUID uuid) { return this.playerData.containsKey(uuid); }

    public boolean hasPlayerData(UUID uuid, SystemEnum type) {
        if(!this.playerData.containsKey(uuid)) {
            return false;
        }
        for (Map.Entry<UUID, Pair<SystemEnum, String>> entry : this.playerData.entrySet()) {
            if(type.getClass() == entry.getValue().getX().getClass()) {
                return true;
            }
        }
        return false;
    }

    public void putPlayerData(UUID uuid, SystemEnum type, String value) {
        this.playerData.put(uuid, new Pair<>(type, value));
    }

    public String getPlayerData(UUID uuid) {
        return this.playerData.get(uuid).getY();
    }

    public void remPlayerData(UUID uuid) {
        this.playerData.remove(uuid);
    }

    public SystemEnum getPlayerDataType(UUID uuid) { return this.playerData.get(uuid).getX(); }
    /**
     * inviteData
     */
    public boolean hasInviteData(UUID uuid) { return inviteData.containsKey(uuid); }

    public void putInviteData(UUID uuid, UUID value) { inviteData.put(uuid, value); }

    public void remInviteData(UUID uuid) { inviteData.remove(uuid); }

    public UUID getInviteData(UUID uuid) { return inviteData.get(uuid); }

}
