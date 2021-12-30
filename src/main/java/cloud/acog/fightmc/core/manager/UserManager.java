package cloud.acog.fightmc.core.manager;

import cloud.acog.fightmc.core.data.UserData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {

    private final Map<UUID, UserData> userDataMap = new HashMap<>();

    public void createUserData(UUID uuid) {
        userDataMap.put(uuid, new UserData(uuid));
    }

    public Map<UUID, UserData> getUserDataMap() {
        return this.userDataMap;
    }

    public boolean hasUserData(UUID uuid) {
        return this.userDataMap.containsKey(uuid);
    }

    public UserData getUserData(UUID uuid) {
        return userDataMap.get(uuid);
    }
}
