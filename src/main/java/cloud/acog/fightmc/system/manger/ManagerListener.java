package cloud.acog.fightmc.system.manger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static cloud.acog.fightmc.system.gui.DataCreateGui.openCreateGUI;

public class ManagerListener implements Listener {

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

        if(event.getRawSlot() == 49 && event.getClick().isRightClick()) {
            System.out.println("Create Page Open");
            player.closeInventory();
            player.openInventory(openCreateGUI(player));
        }

    }
}
