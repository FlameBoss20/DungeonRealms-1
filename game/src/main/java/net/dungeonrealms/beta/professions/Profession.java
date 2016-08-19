package net.dungeonrealms.beta.professions;

import net.dungeonrealms.beta.utilities.string.StringUtils;
import net.dungeonrealms.common.game.database.DatabaseAPI;
import net.dungeonrealms.common.game.database.data.EnumData;
import net.dungeonrealms.game.miscellaneous.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Matthew on 8/14/2016.
 */

public abstract class Profession {

    public enum ToolType {
        PICKAXE(1, "Pickaxe"), HOE(2, "Hoe"), SHOVEL(3, "Spade"), FISHING_ROD(4, "Fishing Rod"), AXE(5, "Axe"), SHEARS(6, "Shears"), NULL(0, null);

        int id;
        String name;

        ToolType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public static ToolType getToolType(Material type) {
            if (type.toString().contains("PICKAXE")) {
                return PICKAXE;
            } else if (type.toString().contains("SPADE")) {
                return SHOVEL;
            } else if (type.toString().contains("HOE")) {
                return HOE;
            } else if (type.toString().contains("FISHING")) {
                return FISHING_ROD;
            } else if (type.toString().contains("Axe")) {
                return AXE;
            } else if (type.toString().contains("Shears")) {
                return SHEARS;
            }
            return NULL;
        }

        public static ToolType getToolType(ItemStack tool) {
            return getToolType(tool.getType());
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

    }

    public enum ProfessionTypes {
        MINING(1, "Mining", ToolType.PICKAXE),
        FISHING(2, "Fishing", ToolType.FISHING_ROD);

        int id;
        String name;
        ToolType toolType;

        ProfessionTypes(int id, String name, ToolType toolType) {
            this.id = id;
            this.name = name;
            this.toolType = toolType;
        }

        public int getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public ToolType getToolType() {
            return this.toolType;
        }
    }

    public double getCurrentEXP(ItemStack tool) {
        return Integer.parseInt(ChatColor.stripColor(tool.getItemMeta().getLore().get(1).split(" / ")[0].trim()));
    }

    public void addEXP(Player player, ItemStack tool, int exp) {
        ItemBuilder pick = new ItemBuilder();
        double oldExp = getCurrentEXP(tool);
        double newExp = (oldExp + exp);
        double needExp = getNeedEXP(tool);
        if ((boolean) DatabaseAPI.getInstance().getData(EnumData.TOGGLE_DEBUG, player.getUniqueId())) {
            player.sendMessage(StringUtils.colorCodes("&e&l        +&e" + exp + " &lEXP &7[" + newExp + "/" + needExp + "]"));
        }
        if (needExp >= needExp) {
            levelUp(player, tool);
            return;
        }
        pick.setItem(new ItemStack(tool.getType()));
        pick.setName(tool.getItemMeta().getDisplayName());
        pick.addLore("&7Level: " + getLevel(tool));
        pick.addLore("&7" + newExp + "" + " / " + needExp);
        pick.addLore("&7EXP: " + generateEXPBar(getCurrentEXP(tool), needExp, 50));
        pick.addLore("&7&o" + tool.getItemMeta().getLore().get(tool.getItemMeta().getLore().size() - 1));
        player.setItemInHand(pick.build());
        return;
    }

    public int getLevel(ItemStack tool) {
        return Integer.parseInt(ChatColor.stripColor(tool.getItemMeta().getLore().get(0).split("Level: ")[1].trim()));
    }

    public void setLevel(ItemStack tool, int level) {}

    public abstract void levelUp(Player player, ItemStack tool);

    public void setMinExp(ItemStack tool, int exp) {}

    public void setExpNeeded(ItemStack tool, int exp) {}

    public abstract List<String> getEnchants();

    public abstract ProfessionTypes getProfessionTypes();

    public int getLevel(int tier) {
        switch (tier) {
            case 1:
                return 1;
            case 2:
                return 20;
            case 3:
                return 40;
            case 4:
                return 60;
            case 5:
                return 80;
            case 6:
                return 100;
            default:
                return 1;
        }
    }

    public int getNeedEXP(int level) {
        switch (level) {
            case 1:
                return 176;
            case 100:
                return 0;
            default:
                int previous_level = level--;
                return (int) (Math.pow((previous_level), 2) + ((previous_level) * 20) + 150 + ((previous_level) * 4) + getNeedEXP((previous_level)));
        }
    }

    public int getNeedEXP(ItemStack tool) {
        return Integer.parseInt(ChatColor.stripColor(tool.getItemMeta().getLore().get(1).split(" / ")[1].trim()));
    }

    public int getTier(int level) {
        if ((level > 0) && (level < 20)) {
            return 1;
        } else if ((level > 19) && (level < 40)) {
            return 2;
        } else if ((level > 39) && (level < 60)) {
            return 3;
        } else if ((level > 59) && (level < 80)) {
            return 4;
        } else if ((level > 79) && (level < 101)) {
            return 5;
        } else {
            return 1;
        }
    }

    public abstract List<String> getEnchants(ItemStack tool);

    public String generateEXPBar(double minExp, double needExp, int times) {

        int i = 0;
        String bar = "" ;
        while (times < i) {
            bar += "|" ;
            i++;
        }
        if ((minExp == 0) || needExp == 0) {
            return "&c" + bar;
        }
        double percent = ((minExp * 100) / needExp);
        double per = ((percent / 100) * 50);
        int display = (int) per;
        if (display <= 0) {
            display = 1;
        }
        if (display > 50) {
            display = 50;
        }
        String newBar = "&7" + bar.substring(0, display) + "&c" + bar.substring(display, bar.length());
        return newBar;
    }
}