package cloud.acog.fightmc.system;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static cloud.acog.fightmc.library.bukkit.Message.sendTo;

public class SystemListener implements Listener {

    private final SystemManager systemManager;

    public SystemListener(SystemManager systemManager) {
        this.systemManager = systemManager;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if(!systemManager.hasPlayerData(player)) return;
        if(!event.getMessage().equals("/생성종료")) {
            sendTo(player, "&c현재 커맨드 사용이 불가능합니다, \"/생성종료\" 입력시 종료 됩니다.");
            return;
        }

        systemManager.remPlayerData(player);
        sendTo(player, "&f3대전장 생성이 종료 되었습니다.");
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

    }
}
