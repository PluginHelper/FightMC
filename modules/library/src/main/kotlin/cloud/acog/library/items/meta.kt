package cloud.acog.library.items

import cloud.acog.library.string.colorize
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun ItemStack.meta(c: ItemMeta.() -> Unit = {}) = setItemMeta(itemMeta!!.apply(c))

fun ItemMeta.setName(name: String) = setDisplayName(name.colorize)

fun ItemStack.setName(name: String) = meta { setName(name) }

fun ItemMeta.setLore(vararg lore: String) = setLore(lore.map { it.colorize })

fun ItemStack.setLore(vararg line: String) = meta { setLore(*line) }

fun ItemMeta.addLore(vararg lores: String) = lores.forEach(lore!!::add)

fun ItemStack.addLore(vararg lores: String) = meta { lores.forEach(lore!!::add) }

inline fun ItemMeta.lore(configure: MutableList<String>.() -> Unit) {
    val newLore = (lore ?: mutableListOf()).apply(configure).map { it.colorize }
    lore = newLore
}