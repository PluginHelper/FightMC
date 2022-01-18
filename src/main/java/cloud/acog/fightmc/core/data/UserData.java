package cloud.acog.fightmc.core.data;

import lombok.Data;

import java.util.UUID;

@Data
public class UserData {

    private final UUID uuid;

    private Integer win = 0;
    private Integer fail = 0;

    public UserData(UUID uuid) {
        this.uuid = uuid;
    }

}
