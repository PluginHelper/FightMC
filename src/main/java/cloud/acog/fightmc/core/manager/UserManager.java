package cloud.acog.fightmc.core.manager;

import cloud.acog.fightmc.core.data.UserData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private final Map<UUID, UserData> userDataMap = new HashMap<>();

    /**
     * UserDataMap
     */
    public Map<UUID, UserData> getUserDataMap() { return this.userDataMap; }

    public boolean hasUserData(UUID uuid) { return this.userDataMap.containsKey(uuid); }

    public void putUserData(UUID uuid, UserData data) { this.userDataMap.put(uuid, data); }

    public UserData getUserData(UUID uuid) { return this.userDataMap.get(uuid); }

    /**
     * sava & load
     */
    public void load() {

    }

    public void save() {
        for (Map.Entry<UUID, UserData> entry : getUserDataMap().entrySet()) {
            //
        }
        // 마지막에 작업
    }
}
