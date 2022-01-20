package cloud.acog.fightmc.system.system;

import cloud.acog.fightmc.core.data.UserData;
import cloud.acog.fightmc.core.manager.SystemManager;
import cloud.acog.fightmc.core.manager.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import static cloud.acog.fightmc.library.bukkit.Message.sendTo;

public class SystemListener implements Listener {

    private final SystemManager systemManager;
    private final UserManager userManager;

    public SystemListener(SystemManager systemManager, UserManager userManager) {
        this.systemManager = systemManager;
        this.userManager = userManager;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if(!systemManager.hasPlayerData(player.getUniqueId())) return;
        if(!event.getMessage().equals("/종료")) {
            sendTo(player, "&c현재 대전 시스템으로 인해 커맨드 사용이 불가능합니다, \"/종료\" 입력시 종료 됩니다.");
            return;
        }

        systemManager.remPlayerData(player.getUniqueId());
        sendTo(player, "&f3대전장 생성이 종료 되었습니다.");
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        if (userManager.hasUserData(event.getPlayer().getUniqueId())) {
            return;
        }

        UserData userData = new UserData(event.getPlayer().getUniqueId());
        userManager.putUserData(event.getPlayer().getUniqueId(), userData);
    }
}
