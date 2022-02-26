package cloud.acog.library.command

import cloud.acog.library.exception.ArgsGetNullException
import cloud.acog.library.string.colorize
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.NumberFormatException


abstract class CommandCreate : CommandExecutor {

    abstract var command: String

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        runCatching {
            execute(sender, command, label, args)
        }.recoverCatching {
            when(it) {
                is ArgsGetNullException -> sender.sendMessage(it.error.colorize)
                is NumberFormatException -> sender.sendMessage("잘못된 값을 입력하셨습니다.")
            }
        }
        return false
    }

    abstract fun execute(sender: CommandSender, command: Command, label: String, args: Array<out String>)

    val CommandSender.player : Player? get() = if(this is Player) this else null
}