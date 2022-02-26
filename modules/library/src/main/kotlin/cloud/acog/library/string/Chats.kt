package cloud.acog.library.string

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.text.DecimalFormat

fun Player.message(message: String) = this.sendMessage(message.colorize)

fun CommandSender.message(message: String) = this.sendMessage(message.colorize)

val String.colorize get() = this.replace("&", "§")

val String.comma: String get() = toString().format(DecimalFormat("#,###"))