package cloud.acog.fightmc.system.manger;

import cloud.acog.fightmc.core.data.FightData;
import cloud.acog.fightmc.core.data.SystemEnum;
import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.core.manager.UserManager;
import cloud.acog.fightmc.core.manager.SystemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import static cloud.acog.fightmc.library.bukkit.Message.*;
import static cloud.acog.fightmc.system.Gui.openFightDataGui;


public class ManagerListener implements Listener {

    private final SystemManager systemManager;
    private final FightManager fightManager;
    private final UserManager userManager;

    public ManagerListener(SystemManager systemManager, FightManager fightManager, UserManager userManager) {
        this.systemManager = systemManager;
        this.fightManager = fightManager;
        this.userManager = userManager;
    }

    /**
     * MineCraft FightManager Command Events
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack item = event.getCurrentItem();
        if(event.getView().getTitle().contains("&f&e-&f ")) { // 여기도 만들어야 함
            event.setCancelled(true);
            if(item == null || item.getItemMeta().getDisplayName() == null) {
                return;
            }
            String name = event.getView().getTitle().split(" ")[1];
            Player player = (Player) event.getWhoClicked();
            FightData fightData = fightManager.getFightData(name);

            if(fightData == null || name == null ) {
                return;
            }

            if(event.getClick().isRightClick() && event.isShiftClick() && event.getRawSlot() == 1) {
                player.closeInventory();
                fightManager.remFightData(name);
                sendTo(player, String.format("\"%d\" 대전장 데이터를 &c삭제&f했습니다.", name));
            }
            switch (event.getRawSlot()) {
                case 3:
                    player.closeInventory();

                    if(systemManager.hasPlayerData(player.getUniqueId(), SystemEnum.FIGHT_TIME_EDIT)) {
                        sendTo(player, "&c이미 대전장의 대전시간을 설정하고 있습니다.");
                        return;
                    }

                    systemManager.putPlayerData(player.getUniqueId(), SystemEnum.FIGHT_TIME_EDIT, "true");
                    sendTo(player, "&f원하시는 대전장 시간을 입력해주세요.");
                    return;
                case 4:
                    fightData.setFirstLocation(player.getLocation());
                    sendTo(player, String.format("&e%d&f 대전장의 첫번째 대전장소를 설정했습니다 : %d, %d, %d", fightData.getName(), fightData.getFirstLocation().getX(), fightData.getFirstLocation().getY(), fightData.getFirstLocation().getZ()));
                    return;
                case 5:
                    fightData.setSecondLocation(player.getLocation());
                    sendTo(player, String.format("&e%d&f 대전장의 두번째 대전장소를 설정했습니다 : %d, %d, %d", fightData.getName(), fightData.getFirstLocation().getX(), fightData.getFirstLocation().getY(), fightData.getFirstLocation().getZ()));
                    return;
                case 6:
                    fightData.setSeeLocation(player.getLocation());
                    sendTo(player, String.format("&e%d&f 대전장의 관전장소를 설정했습니다 : %d, %d, %d", fightData.getName(), fightData.getFirstLocation().getX(), fightData.getFirstLocation().getY(), fightData.getFirstLocation().getZ()));
                    return;
                case 7:
                    fightData.setSpawnLocation(player.getLocation());
                    sendTo(player, String.format("&e%d&f 대전장의 스폰장소를 설정했습니다 : %d, %d, %d", fightData.getName(), fightData.getFirstLocation().getX(), fightData.getFirstLocation().getY(), fightData.getFirstLocation().getZ()));
                    return;
            }

        }
        if(!event.getView().getTitle().contains("&fFightMC Manager")) {
            return;
        }

        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        if(item == null || item.getItemMeta().getDisplayName() == null) {
            return;
        }

        if(event.getRawSlot() == 49 && event.getClick().isRightClick()) {
            player.closeInventory();

            if(systemManager.hasPlayerData(player.getUniqueId(), SystemEnum.CREATE_FIGHT_DATA)) {
                sendTo(player, "&c이미 대전장 생성중 입니다.");
                return;
            }

            systemManager.putPlayerData(player.getUniqueId(), SystemEnum.CREATE_FIGHT_DATA, "true");
            sendTo(player, "&f원하시는 대전장의 이름을 입력해주세요.");
            return;
        }

        if(event.getRawSlot() == 50 && event.getClick().isLeftClick()) {
            player.closeInventory();

            fightManager.setFightPluginState(!fightManager.getFightPluginState());

            if(fightManager.getFightPluginState()) {
                broadCast(colorize("&f대전 - 플러그인이 &a활성화 &f되었습니다."));
            } else {
                broadCast(colorize("&f대전 - 플러그인이 &c비활성화 &f되었습니다."));
            }
            return;
        }

        if(item.getItemMeta().getDisplayName().contains("&e- &f")) {
            if(!fightManager.hasFightData(item.getItemMeta().getDisplayName().substring(5))) {
                player.closeInventory();
                sendTo(player, "&c존재하지 않는 대전장 데이터 입니다.");
            }

            String name = item.getItemMeta().getDisplayName().substring(5);
            FightData fightData = fightManager.getFightData(name);

            if(event.getClick().isRightClick() && event.isShiftClick() ) {
                player.closeInventory();
                fightManager.remFightData(name);
                sendTo(player, String.format("\"%d\" 대전장 데이터를 &c삭제&f했습니다.", name));
            }
            if(fightData == null) return;

            player.closeInventory();
            player.openInventory(openFightDataGui(fightData));
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(!systemManager.hasPlayerData(event.getPlayer().getUniqueId())) {
            return;
        }

        Player player = event.getPlayer();
        String name = event.getMessage();
        event.setCancelled(true);

        if(systemManager.getPlayerDataType(player.getUniqueId()).equals(SystemEnum.FIGHT_TIME_EDIT)) {
            try {
                int index = Integer.parseInt(name);

                if(index > 1000) {
                    sendTo(player, "&c대전장의 대전시간을 최대 1000초 이상으로 설정하실수 없습니다.");
                    return;
                }
                if(index < 0) {
                    sendTo(player, "&c정수만 입력 가능하십니다.");
                    return;
                }

                FightData fightData = fightManager.getFightData(systemManager.getPlayerData(player.getUniqueId()));
                fightData.setFightTime(index);
                systemManager.remPlayerData(player.getUniqueId());
                sendTo(player, String.format("&e%d&f 대전장의 대전시간을 &b%d&f 로 설정했습니다.", fightData.getName(), index));

            } catch (NumberFormatException e) {
                sendTo(player, "&c정수만 입력 가능하십니다.");
            }
            return;
        }

        if(name.length() > 16) {
            sendTo(player, "&c대전장의 이름은 16글자 이상이 될수 없습니다!");
            return;
        }
        if(fightManager.hasFightData(name)) {
            sendTo(player, "&c이미 존재하는 대전장의 이름입니다.");
            return;
        }

        fightManager.createFightData(player, name);
        systemManager.remPlayerData(player.getUniqueId());
        sendTo(player, "&f대전장을 새로 생성했습니다. :&e " + name);
    }

    /**
     * MineCraft FightManager Events
     */

}
