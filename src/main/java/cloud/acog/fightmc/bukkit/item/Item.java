package cloud.acog.fightmc.bukkit.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static cloud.acog.fightmc.bukkit.message.Message.colorize;

public class Item {

    public static ItemStack item(Material material, int amount, String display, List<String> lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        ArrayList<String> lo = new ArrayList<>();

        for (String s : lore) {
            lo.add(colorize(s));
        }

        assert meta != null;
        meta.setDisplayName(colorize(display));
        meta.setLore(lo);

        item.setItemMeta(meta);
        return item;
    }
}
