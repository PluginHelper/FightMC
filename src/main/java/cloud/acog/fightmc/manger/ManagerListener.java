package cloud.acog.fightmc.manger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ManagerListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().contains("FightMC Manager")) {
            return;
        }

        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();
        if(item.getItemMeta().getDisplayName() == null) {
            return;
        }

        System.out.println("sex");
    }
}
