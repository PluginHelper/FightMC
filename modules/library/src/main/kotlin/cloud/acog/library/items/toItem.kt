package cloud.acog.library.items

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

fun Material.toItem(amount: Int = 1): ItemStack = ItemStack(this, amount)

fun item(material: Material, amount: Int = 1, c: ItemStack.() -> Unit = {}) = material.toItem(amount).apply(c)

inline fun item(item: ItemStack, amount: Int = item.amount, configure: ItemStack.() -> Unit = {}) =
    ItemStack(item).apply {
        setAmount(amount)
        configure()
    }

fun ItemStack.name() = itemMeta?.displayName ?: type.name