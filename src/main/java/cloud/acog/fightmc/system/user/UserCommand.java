package cloud.acog.fightmc.system.user;

import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.core.manager.SystemManager;
import cloud.acog.fightmc.core.manager.UserManager;
import cloud.acog.fightmc.system.Gui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static cloud.acog.fightmc.library.bukkit.Message.colorize;
import static cloud.acog.fightmc.library.bukkit.Message.sendTo;

public class UserCommand implements CommandExecutor {

    private final FightManager fightManager;
    private final UserManager userManager;
    private final SystemManager systemManager;

    public UserCommand(FightManager fightManager, UserManager userManager, SystemManager systemManager) {
        this.fightManager = fightManager;
        this.userManager = userManager;
        this.systemManager = systemManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, command, label, args);
        return false;
    }

    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.isOp() || !(sender instanceof Player)) {
            sendTo(sender, "&cCommand Error | retry Pleas");
        }

        Player player = (Player) sender;
        if(!fightManager.getFightPluginState()) {
            sendTo(player, colorize("&c현재 대전 시스템이 비활성화 상태입니다."));
        }

        player.openInventory(Gui.getUserManagerGui(player));
    }
}
