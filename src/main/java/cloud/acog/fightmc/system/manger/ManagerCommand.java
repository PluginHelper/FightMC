package cloud.acog.fightmc.system.manger;

import cloud.acog.fightmc.core.data.FightData;
import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.library.bukkit.ItemBuilder;
import cloud.acog.fightmc.library.bukkit.Message;
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
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, colorize("&fFightMC Manager"));
        inventory.setItem(45, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&f이전 페이지").build());
        inventory.setItem(53, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&f다음 페이지").build());

        inventory.setItem(50, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&c아이템을 클릭시 해당 정보를 삭제합니다.").build());
        inventory.setItem(49, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&e대전 매니저").setLore("&f아이템을 우클릭시 대전정보를 생성합니다.").build());
        inventory.setItem(48, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&3아이템을 우클릭시 해당 정보를 수정합니다.").build());

        for (Map.Entry<String, FightData> entry : fightManager.getFightDataMap().entrySet()) {
            ItemStack item = new ItemBuilder(Material.PAPER, 1).setDisplay("&e- &f" + entry.getKey()).setLore(
                    "&f아이템을 쉬프트 우클릭시 대전장 정보를 &c삭제&f합니다.",
                    "&f자세한 정보를 확인하실려면 좌클릭 해주세요."
            ).build();
            inventory.addItem(item);
        }

        player.openInventory(inventory);
    }
}
