package cloud.acog.fightmc.manger;

import cloud.acog.fightmc.library.bukkit.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

import static cloud.acog.fightmc.library.bukkit.item.Item.item;

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
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "fightMC Manager");

        inventory.setItem(45, item(Material.STONE_BUTTON, 1, "&f이전 페이지", null));
        inventory.setItem(53, item(Material.STONE_BUTTON, 1, "&f다음 페이지", null));

        inventory.setItem(50, item(Material.PAINTING, 1, "&c아이템을 클릭시 해당 정보를 삭제합니다.", null));
        inventory.setItem(49, item(Material.SUNFLOWER, 1, "&e대전 매니저", null));
        inventory.setItem(48, item(Material.PAINTING, 1, "&3아이템을 우클릭시 해당 정보를 수정합니다.", null));
        player.openInventory(inventory);
    }
}
