package cloud.acog.fightmc.library.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

    public static void sendTo(Player player, String message) {
        player.sendMessage(colorize(message));
    }

    public static void sendTo(CommandSender sender, String message) {
        sender.sendMessage(colorize( message));
    }

    public static String colorize(String value) {
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    public static void broadCast(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTo(player, colorize(message));
        }
    }
}
