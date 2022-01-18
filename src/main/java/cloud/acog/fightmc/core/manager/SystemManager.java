package cloud.acog.fightmc.core.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SystemManager {

    private final Map<Player, Boolean> playerData = new HashMap<>();
    private final Map<UUID, UUID> inviteData = new HashMap<>();


    /**
     * PlayerData
     */
    public boolean hasPlayerData(Player player) {
        return playerData.containsKey(player);
    }

    public void putPlayerData(Player player, Boolean value) {
        playerData.put(player, value);
    }

    public void remPlayerData(Player player) {
        playerData.remove(player);
    }

    /**
     * inviteData
     */
    public boolean hasInviteData(UUID uuid) { return inviteData.containsKey(uuid); }

    public void putInviteData(UUID uuid, UUID value) { inviteData.put(uuid, value); }

    public void remInviteData(UUID uuid) { inviteData.remove(uuid); }

    public UUID getInviteData(UUID uuid) { return inviteData.get(uuid); }

}
