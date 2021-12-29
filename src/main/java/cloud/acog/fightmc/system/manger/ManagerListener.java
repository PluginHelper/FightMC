package cloud.acog.fightmc.system.manger;

import cloud.acog.fightmc.core.data.FightData;
import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.system.SystemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static cloud.acog.fightmc.library.bukkit.Message.sendTo;


public class ManagerListener implements Listener {

    private final SystemManager systemManager;
    private final FightManager fightManager;

    public ManagerListener(SystemManager systemManager, FightManager fightManager) {
        this.systemManager = systemManager;
        this.fightManager = fightManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().contains("&fFightMC Manager")) {
            return;
        }

        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        if(item.getItemMeta().getDisplayName() == null) {
            return;
        }

        if(event.getRawSlot() == 49 && event.getClick().isRightClick()) { // 대전생성 클릭시
            player.closeInventory();

            if(systemManager.hasPlayerData(player)) {
                return;
            }

            systemManager.putPlayerData(player, true);
            sendTo(player, "&f원하시는 대전장의 이름을 입력해주세요.");
        }

        for (Map.Entry<String, FightData> entry : fightManager.getFightDataMap().entrySet()) {

        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(!systemManager.hasPlayerData(event.getPlayer())) {
            return;
        }

        Player player = event.getPlayer();
        String name = event.getMessage();

        if(name.length() > 16) {
            sendTo(player, "&c대전장의 이름은 16글자 이상이 될수 없습니다!");
            return;
        }
        if(fightManager.hasFightData(name)) {
            sendTo(player, "&c이미 존재하는 대전장의 이름입니다.");
            return;
        }

        fightManager.createFightData(player, name);
        systemManager.remPlayerData(player);
        sendTo(player, "&f대전장을 새로 생성했습니다. &e: " + name);
    }

}
