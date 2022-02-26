package cloud.acog.library.plugin

import cloud.acog.library.command.CommandCreate
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.registerListener(vararg listener: Listener) = listener.forEach {
        event -> server.pluginManager.registerEvents(event, this)
}

fun JavaPlugin.registerCommand(vararg command: CommandCreate) = command.forEach {
        cmd -> getCommand(cmd.command)!!.setExecutor(cmd)
}