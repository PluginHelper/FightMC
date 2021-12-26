package cloud.acog.fightmc.library.bukkit.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static cloud.acog.fightmc.library.bukkit.message.Message.colorize;

public class Item {

    public static ItemStack item(Material material, int amount, String display, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;

        if(lore != null) {
            ArrayList<String> lo = new ArrayList<>();
            for (String s : lore) {
                lo.add(colorize(s));
            }
            meta.setLore(lo);
        }
        meta.setDisplayName(colorize(display));

        item.setItemMeta(meta);
        return item;
    }
}
