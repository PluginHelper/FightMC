package cloud.acog.fightmc.system.user;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class UserListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();

    }
}
