package cloud.acog.fightmc.manger;

import cloud.acog.fightmc.bukkit.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

import static cloud.acog.fightmc.bukkit.item.Item.item;

public class ManagerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, command, label, args);
        return false;
    }

    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp() || !(sender instanceof Player)) {
            Message.sendTo(sender, "&cCommand Error | retry Pleas");
        }
        Player player = (Player) sender;
        Inventory inventory = Bukkit.createInventory(null, 54, "FightMC Manager :: " + player.getName());

        for (Integer i : Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 53, 52, 51, 49, 48, 47, 46, 45, 44)) {
            inventory.setItem(i, item(
                    Material.BLACK_STAINED_GLASS, 1, "&f", Arrays.asList("&f", "&f")
            ));
        }
    }
}
