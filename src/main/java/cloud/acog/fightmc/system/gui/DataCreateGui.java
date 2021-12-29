package cloud.acog.fightmc.system.gui;

import cloud.acog.fightmc.library.bukkit.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class DataCreateGui implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!(event.getInventory().getType() == InventoryType.ANVIL) || !(event.getView().getTitle().contains("&f대전장의"))) {
            return;
        }

    }

    public static Inventory openCreateGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.ANVIL, "&f대전장의 이름을 입력해주세요");

        inventory.setItem(0, new ItemBuilder(Material.PAPER, 1).setDisplay("&f이름을 입력해주세요.").build());
        inventory.setItem(1, new ItemBuilder(Material.BARRIER, 1).setDisplay("&c클릭시 모든 작업을 중단합니다.").build());

        return inventory;
    }
}
