package cloud.acog.fightmc.system;

import cloud.acog.fightmc.core.data.FightData;
import cloud.acog.fightmc.core.manager.FightManager;
import cloud.acog.fightmc.library.bukkit.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

import static cloud.acog.fightmc.library.bukkit.Message.colorize;

public class Gui {

    public static Inventory openFightDataGui(FightData fightData) {
        Inventory inventory = Bukkit.createInventory(null, 9, colorize("&f&e-&f " + fightData.getName()));

        inventory.setItem(0,
                new ItemBuilder(Material.BOOK, 1).setDisplay("&e정보").setLore(
                        "&e생성자&f : " + Bukkit.getPlayer(fightData.getCreator()).getName(),
                        "&e대전시간&f : " + fightData.getFightTime() + "초",
                        "&e대전장 관리상태&f : " + fightData.getManageState(),
                        "&e대전장 상태&f : " + fightData.getState(),
                        "&f",
                        "&e플레이어1&f : " + fightData.getFirstPlayer().toString(),
                        "&e플레이어2&f : " + fightData.getSecondPlayer().toString(),
                        "&f",
                        String.format("&e대전좌표1&f : %d, %d %d", fightData.getFirstLocation().getX(), fightData.getFirstLocation().getY(), fightData.getFirstLocation().getZ()),
                        String.format("&e대전좌표2&f : %d, %d %d", fightData.getSecondLocation().getX(), fightData.getSecondLocation().getY(), fightData.getSecondLocation().getZ()),
                        String.format("&e관전좌표&f : %d, %d %d", fightData.getSeeLocation().getX(), fightData.getSeeLocation().getY(), fightData.getSeeLocation().getZ()),
                        String.format("&e스폰좌표&f : &d, &d, &d", fightData.getSpawnLocation().getX(), fightData.getSpawnLocation().getY(), fightData.getSpawnLocation().getZ()),
                        "&f",
                        "&f아이템을 쉬프트 우클릭시 대전장 정보를 &c삭제&f합니다."
                ).build()
        );
        inventory.setItem(3,
                new ItemBuilder(Material.PAPER, 1).setDisplay("&f대전관리").setLore(
                        "&f아이템을 클릭시 대전장의 상태를 변경합니다  &e-&f " + fightData.getManageState().toString(),
                        "&f아이템을 우클릭시 대전시간을 수정합니다. &e-&f " + fightData.getFightTime() + "초"
                ).build()
        );
        inventory.setItem(4,
                new ItemBuilder(Material.PAPER, 1).setDisplay("&f대전좌표 : 1").setLore(
                        "&f아이템을 클릭시 대전장소의 좌표를 현재 좌표로 설정합니다.",
                        String.format("현재좌표 : %d, %d, %d", fightData.getFirstLocation().getX(), fightData.getFirstLocation().getY(), fightData.getFirstLocation().getZ())
                ).build()
        );
        inventory.setItem(5,
                new ItemBuilder(Material.PAPER, 1).setDisplay("&f대전좌표 : 2").setLore(
                        "&f아이템을 클릭시 대전장소의 좌표를 현재 좌표로 설정합니다.",
                        String.format("현재좌표 : %d, %d, %d", fightData.getSecondLocation().getX(), fightData.getSecondLocation().getY(), fightData.getSecondLocation().getZ())
                ).build()
        );
        inventory.setItem(6,
                new ItemBuilder(Material.PAPER, 1).setDisplay("&f관전좌표").setLore(
                        "&f아이템을 클릭시 대전시간을 수정합니다.",
                        String.format("현재좌표 : %d, %d, %d", fightData.getSeeLocation().getX(), fightData.getSeeLocation().getY(), fightData.getSeeLocation().getZ())
                ).build()
        );
        inventory.setItem(7,
                new ItemBuilder(Material.PAPER, 1).setDisplay("&f스폰좌표").setLore(
                        "&f아이템을 클릭시 대전시간을 수정합니다.",
                        String.format("현재좌표 : %d, %d, %d", fightData.getSpawnLocation().getX(), fightData.getSpawnLocation().getY(), fightData.getSpawnLocation().getZ())
                ).build()
        );
        return inventory;
    }


    public static Inventory getFightManagerGui(FightManager fightManager) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, colorize("&fFightMC Manager : 1"));

        inventory.setItem(50, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&c제작자 : Acog").build());
        inventory.setItem(49, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&e대전 매니저").setLore("&f아이템을 우클릭시 대전정보를 생성합니다.").build());
        if(fightManager.getFightPluginState()) {
            inventory.setItem(48, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&f대전 &c비활성화").setLore(
                    "아이템을 클릭시 플러그인을 &c비활성화&f 합니다."
            ).build());
        } else {
            inventory.setItem(48, new ItemBuilder(Material.STONE_BUTTON, 1).setDisplay("&f대전 &a활성화").setLore(
                    "아이템을 클릭시 플러그인을 &a활성화&f 합니다."
            ).build());
        }

        for (Map.Entry<String, FightData> entry : fightManager.getFightDataMap().entrySet()) {
            ItemStack item = new ItemBuilder(Material.PAPER, 1).setDisplay("&e- &f" + entry.getKey()).setLore(
                    "&f아이템을 쉬프트 우클릭시 대전장 정보를 &c삭제&f합니다.",
                    "&f자세한 정보를 확인하실려면 좌클릭 해주세요."
            ).build();
            inventory.addItem(item);
        }
        return inventory;
    }

    public static Inventory getUserManagerGui(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, colorize("&fFightMC Manager : 1"));
        return inventory;
    }
}
