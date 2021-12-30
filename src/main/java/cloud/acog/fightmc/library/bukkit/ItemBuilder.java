package cloud.acog.fightmc.library.bukkit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static cloud.acog.fightmc.library.bukkit.Message.colorize;

public class ItemBuilder {

    private final ItemStack item;

    public ItemBuilder(Material material, int amount) {
        this.item = new ItemStack(material, amount);
    }

    public ItemBuilder setDisplay(String display) {
        ItemMeta itemMeta = this.item.getItemMeta();
        itemMeta.setDisplayName(colorize(display));
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ArrayList<String> list = new ArrayList<String>();
        ItemMeta itemMeta = this.item.getItemMeta();

        for (String lo : lore) {
            list.add(colorize(lo));
        }

        itemMeta.setLore(list);
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(Integer index, String value) {
        ItemMeta itemMeta = this.item.getItemMeta();
        List<String> lore = itemMeta.getLore();

        lore.set(index, value);
        itemMeta.setLore(lore);
        this.item.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build() {
        ItemMeta itemMeta = this.item.getItemMeta();
        for (ItemFlag itemFlag : itemMeta.getItemFlags()) {
            itemMeta.removeItemFlags(itemFlag);
        }

        this.item.setItemMeta(itemMeta);
        return this.item;
    }

}
