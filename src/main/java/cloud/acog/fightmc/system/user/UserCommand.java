package cloud.acog.fightmc.system.user;

import cloud.acog.fightmc.library.bukkit.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class UserCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, command, label, args);
        return false;
    }

    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp() || !(sender instanceof Player)) {
            Message.sendTo(sender, "&cCommand Error | retry Pleas");
        }
        Inventory inventory = Bukkit.createInventory(null, 9, "대전");
        Player player = (Player) sender;


    }
}
