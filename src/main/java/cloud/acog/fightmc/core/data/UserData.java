package cloud.acog.fightmc.core.data;

import java.util.UUID;

public class UserData {

    private final UUID id;

    public UserData(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }

}
