package net.dungeonrealms.beta.professions;

import net.dungeonrealms.beta.utilities.math.IntegerUtil;
import net.dungeonrealms.beta.utilities.string.StringUtils;
import net.dungeonrealms.common.game.database.DatabaseAPI;
import net.dungeonrealms.common.game.database.data.EnumData;
import net.dungeonrealms.game.miscellaneous.ItemBuilder;
import net.dungeonrealms.game.miscellaneous.RandomHelper;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Matthew on 8/14/2016.
 */

public class Mining extends Profession {

    public enum Enchants {
        GEM_FIND(1, "GEM FIND", 1, 14),
        DOUBLE_ORE(2, "DOUBLE ORE", 1, 17),
        TRIPLE_ORE(3, "TRIPLE ORE", 1, 5),
        DURABILITY(4, "DURABILITY", 1, 20),
        MINING_SUCCESS(5, "MINING SUCCESS", 1, 15);

        int id;
        String name;
        int minLevel;
        int maxLevel;

        Enchants(int id, String name, int minLevel, int maxLevel) {
            this.id = id;
            this.name = name;
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
        }

        public int getId() { return this.id; }

        public String getName() { return this.name; }

        public int getMinLevel() { return this.minLevel; }

        public int getMaxLevel() { return this.maxLevel; }

        public int decideLevel() {
            return IntegerUtil.randomInteger(this.minLevel, this.maxLevel);
        }

        public static Enchants getRandomEnchant() {
            return RandomHelper.getRandomElementFromList(getEnchantsList());
        }

        public static List<Enchants> getEnchantsList() {
            List<Enchants> ench = new ArrayList<Enchants>();
            for (Enchants enchants : values()) {
                ench.add(enchants);
            }
            return ench;
        }
    }

    public enum Pickaxes {
        T1_PICK(1, Material.WOOD_PICKAXE, ToolType.PICKAXE, "&fNovice Pickaxe", "A pickaxe made of wood."),
        T2_PICK(2, Material.STONE_PICKAXE, ToolType.PICKAXE, "&aApprentice Pickaxe", "A pickaxe made of stone."),
        T3_PICK(3, Material.IRON_PICKAXE, ToolType.PICKAXE, "&bExpert Pickaxe", "A pickaxe made out of iron."),
        T4_PICK(4, Material.DIAMOND_PICKAXE, ToolType.PICKAXE, "&dSupreme Pickaxe", "A pickaxe made out of diamonds."),
        T5_PICK(5, Material.GOLD_PICKAXE, ToolType.PICKAXE, "&eMaster Pickaxe", "A pickaxe made out of gold."),
        T5_MASTER_PICK(5, Material.GOLD_PICKAXE, ToolType.PICKAXE, "&eGrand Master Pickaxe", "A shinny pickaxe made out of gold.");

        int tier;
        Material type;
        ToolType toolType;
        String name;
        String lore;

        Pickaxes(int tier, Material type, ToolType toolType, String name, String lore) {
            this.tier = tier;
            this.type = type;
            this.toolType = toolType;
            this.name = name;
            this.lore = lore;
        }

        public int getTier() {
            return this.tier;
        }

        public String getName() {
            return this.name;
        }

        public ToolType getToolType() {
            return this.toolType;
        }

        public String getLore() {
            return this.lore;
        }

        public Material getType() {
            return this.type;
        }

        public ItemStack generatePickaxe() {
            Mining mining = new Mining();
            ItemBuilder pick = new ItemBuilder();
            int needExp = mining.getNeedEXP(mining.getLevel(this.tier));
            pick.setItem(new ItemStack(this.type));
            pick.setName(this.name);
            pick.addLore("&7Level: " + StringUtils.getTierColor(this.tier) + mining.getLevel(this.tier));
            pick.addLore("&70" + " / " + needExp);
            pick.addLore("&7EXP: " + mining.generateEXPBar(0, needExp, 50));
            pick.addLore("&7&o" + this.lore);
            return pick.build();
        }
    }

    public enum Ores {
        T1_ORE(1, Material.COAL_ORE, 80, 115),
        T2_ORE(2, Material.EMERALD_ORE, 200, 325),
        T3_ORE(3, Material.IRON_ORE, 450, 550),
        T4_ORE(4, Material.DIAMOND_ORE, 750, 850),
        T5_ORE(5, Material.GOLD_ORE, 960, 1115);

        int tier;
        Material type;
        int minExp;
        int maxExp;

        Ores(int tier, Material type, int minExp, int maxExp) {
            this.tier = tier;
            this.type = type;
            this.minExp = minExp;
            this.maxExp = maxExp;
        }

        public int getTier() {
            return this.tier;
        }

        public int getMinExp() {
            return this.minExp;
        }

        public int getMaxExp() {
            return this.maxExp;
        }

        public Material getType() {
            return this.type;
        }

        public int getEXPGained() {
            return IntegerUtil.randomInteger(this.minExp, this.maxExp);
        }
    }

    @Override
    public void levelUp(Player player, ItemStack tool) {
        int level = getLevel(tool);
        int newLevel = level++;
        Pickaxes pickaxe = Pickaxes.T1_PICK;
        switch (newLevel) {
            case 20:
                pickaxe = Pickaxes.T2_PICK;
                break;
            case 40:
                pickaxe = Pickaxes.T3_PICK;
                break;
            case 60:
                pickaxe = Pickaxes.T4_PICK;
                break;
            case 80:
                pickaxe = Pickaxes.T5_PICK;
                break;
            case 100:
                pickaxe = Pickaxes.T5_MASTER_PICK;
                break;
            default:
                break;
        }
        ItemBuilder pick = new ItemBuilder();
        int needExp = getNeedEXP(newLevel);
        pick.setItem(new ItemStack(pickaxe.getType()));
        pick.setName(pickaxe.getName());
        pick.addLore("&7Level: " + StringUtils.getTierColor(pickaxe.getTier()) + newLevel);
        pick.addLore("&70" + " / " + needExp);
        pick.addLore("&7EXP: " + generateEXPBar(0, needExp, 50));
        decideEnchants(tool, getEnchants(tool));
        pick.addLore("&7&o" + pickaxe.getLore());
        if ((boolean) DatabaseAPI.getInstance().getData(EnumData.TOGGLE_DEBUG, player.getUniqueId())) {
            player.sendMessage(StringUtils.colorCodes("&e&lLEVEL UP Pickaxe " + level + " -> " + newLevel));
        }
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        player.setItemInHand(pick.build());
    }

    public void decideEnchants(ItemStack tool, List<String> enchants) {


    }

    @Override
    public List<String> getEnchants() {
        return Enchants.getEnchantsList().stream().map(Enchants::getName).collect(Collectors.toList());
    }

    @Override
    public ProfessionTypes getProfessionTypes() {
        return ProfessionTypes.MINING;
    }

    @Override
    public List<String> getEnchants(ItemStack tool) {
        List<String> enchants = new ArrayList<>();
        if ((tool.hasItemMeta()) && (tool.getItemMeta().hasLore())) {
            for (String lore : tool.getItemMeta().getLore()) {
                if (lore.contains("GEM FIND")) {
                    enchants.add(Enchants.GEM_FIND.getName());
                }
                if (lore.contains("DOUBLE ORE")) {
                    enchants.add(Enchants.DOUBLE_ORE.getName());
                }
                if (lore.contains("DURABILITY")) {
                    enchants.add(Enchants.DURABILITY.getName());
                }
                if (lore.contains("MINING SUCCESS")) {
                    enchants.add(Enchants.MINING_SUCCESS.getName());
                }
                if (lore.contains("TRIPLE ORE")) {
                    enchants.add(Enchants.TRIPLE_ORE.getName());
                }
            }
        }
        return enchants;
    }
}
