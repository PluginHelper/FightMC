package cloud.acog.fightmc.system.manger;

import cloud.acog.fightmc.core.data.FightData;
import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.library.bukkit.ItemBuilder;
import cloud.acog.fightmc.system.SystemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
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
    ItemStack item = event.getCurrentItem();
        if(event.getView().getTitle().contains("&f&f&f&e- &f")) {
            if(item.getItemMeta().getDisplayName() == null) {
                return;
            }

            String name = event.getView().getTitle().substring(11);
            FightData fightData = fightManager.getFightData(name);

            System.out.println(fightData.getName());
        }
        if(!event.getView().getTitle().contains("&fFightMC Manager")) {
            return;
        }

        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        if(item.getItemMeta().getDisplayName() == null) {
            return;
        }

        if(event.getRawSlot() == 49 && event.getClick().isRightClick()) { // 대전생성 클릭시
            player.closeInventory();

            if(systemManager.hasPlayerData(player)) {
                sendTo(player, "&c이미 대전장 생성중 입니다.");
                return;
            }

            systemManager.putPlayerData(player, true);
            sendTo(player, "&f원하시는 대전장의 이름을 입력해주세요.");
            return;
        }

        if(item.getItemMeta().getDisplayName().contains("&e- &f")) {
            if(!fightManager.hasFightData(item.getItemMeta().getDisplayName().substring(5))) {
                player.closeInventory();
                sendTo(player, "&c존재하지 않는 대전장 데이터 입니다.");
            }

            String name = item.getItemMeta().getDisplayName().substring(5);
            Inventory inventory = Bukkit.createInventory(null, 9, "&f&f&f&e- &f" + name);
            FightData data = fightManager.getFightData(name);

            if(data == null) return;

            inventory.setItem(1, new ItemBuilder(Material.BLACK_STAINED_GLASS, 1).build());

            player.closeInventory();
            player.openInventory(inventory);
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
