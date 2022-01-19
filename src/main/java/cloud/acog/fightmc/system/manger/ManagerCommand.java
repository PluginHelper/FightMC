package cloud.acog.fightmc.system.manger;

import cloud.acog.fightmc.core.data.FightData;
import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.library.bukkit.ItemBuilder;
import cloud.acog.fightmc.library.bukkit.Message;
import cloud.acog.fightmc.system.Gui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static cloud.acog.fightmc.library.bukkit.Message.colorize;

public class ManagerCommand implements CommandExecutor {

    private final FightManager fightManager;

    public ManagerCommand(FightManager fightManager) {
        this.fightManager = fightManager;
    }

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

        player.openInventory(Gui.getFightManagerGui(fightManager));
    }
}
