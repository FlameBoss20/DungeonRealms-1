package net.dungeonrealms.beta.item.data;

import com.google.common.collect.Maps;
import net.dungeonrealms.beta.collection.WeightedCollection;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Map;

/**
 * Created by Giovanni on 12-8-2016.
 */
public enum ItemTier
{
    T1(0, 1, 10, ChatColor.WHITE), T2(1, 10, 30, ChatColor.GREEN),
    T3(2, 30, 50, ChatColor.AQUA), T4(3, 60, 100, ChatColor.LIGHT_PURPLE), T5(4, 100, 200, ChatColor.YELLOW);

    private static WeightedCollection<ItemTier> tierCollection = new WeightedCollection<ItemTier>();
    private static boolean buffered = false;

    private int ladder;
    private int minDmg;
    private int maxDmg;
    private ChatColor chatColor;

    private Map<ItemType, Material> materialMap;

    ItemTier(int ladder, int minDmg, int maxDmg, ChatColor chatColor)
    {
        this.ladder = ladder;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.chatColor = chatColor;
        this.materialMap = Maps.newHashMap();
        this.materialMap.put(ItemType.BOW, Material.BOW);
        this.materialMap.put(ItemType.SHIELD, Material.SHIELD);

        switch (ladder)
        {
            case 0:
                materialMap.put(ItemType.AXE, Material.WOOD_AXE);
                materialMap.put(ItemType.SWORD, Material.WOOD_SWORD);
                materialMap.put(ItemType.POLEARM, Material.WOOD_SPADE);
                materialMap.put(ItemType.STAFF, Material.WOOD_HOE);
                materialMap.put(ItemType.HELMET, Material.LEATHER_HELMET);
                materialMap.put(ItemType.CHESTPLATE, Material.LEATHER_CHESTPLATE);
                materialMap.put(ItemType.LEGGINGS, Material.LEATHER_LEGGINGS);
                materialMap.put(ItemType.BOOTS, Material.LEATHER_BOOTS);
                break;
            case 1:
                materialMap.put(ItemType.AXE, Material.STONE_AXE);
                materialMap.put(ItemType.SWORD, Material.STONE_SWORD);
                materialMap.put(ItemType.POLEARM, Material.STONE_SPADE);
                materialMap.put(ItemType.STAFF, Material.STONE_HOE);
                materialMap.put(ItemType.HELMET, Material.CHAINMAIL_HELMET);
                materialMap.put(ItemType.CHESTPLATE, Material.CHAINMAIL_CHESTPLATE);
                materialMap.put(ItemType.LEGGINGS, Material.CHAINMAIL_LEGGINGS);
                materialMap.put(ItemType.BOOTS, Material.CHAINMAIL_BOOTS);
                break;
            case 2:
                materialMap.put(ItemType.AXE, Material.IRON_AXE);
                materialMap.put(ItemType.SWORD, Material.IRON_SWORD);
                materialMap.put(ItemType.POLEARM, Material.IRON_SPADE);
                materialMap.put(ItemType.STAFF, Material.IRON_HOE);
                materialMap.put(ItemType.HELMET, Material.IRON_HELMET);
                materialMap.put(ItemType.CHESTPLATE, Material.IRON_CHESTPLATE);
                materialMap.put(ItemType.LEGGINGS, Material.IRON_LEGGINGS);
                materialMap.put(ItemType.BOOTS, Material.IRON_BOOTS);
                break;
            case 3:
                materialMap.put(ItemType.AXE, Material.DIAMOND_AXE);
                materialMap.put(ItemType.SWORD, Material.DIAMOND_SWORD);
                materialMap.put(ItemType.POLEARM, Material.DIAMOND_SPADE);
                materialMap.put(ItemType.STAFF, Material.DIAMOND_HOE);
                materialMap.put(ItemType.HELMET, Material.DIAMOND_HELMET);
                materialMap.put(ItemType.CHESTPLATE, Material.DIAMOND_CHESTPLATE);
                materialMap.put(ItemType.LEGGINGS, Material.DIAMOND_LEGGINGS);
                materialMap.put(ItemType.BOOTS, Material.DIAMOND_BOOTS);
                break;
            case 4:
                materialMap.put(ItemType.AXE, Material.GOLD_AXE);
                materialMap.put(ItemType.SWORD, Material.GOLD_SWORD);
                materialMap.put(ItemType.POLEARM, Material.GOLD_SPADE);
                materialMap.put(ItemType.STAFF, Material.GOLD_HOE);
                materialMap.put(ItemType.HELMET, Material.GOLD_HELMET);
                materialMap.put(ItemType.CHESTPLATE, Material.GOLD_CHESTPLATE);
                materialMap.put(ItemType.LEGGINGS, Material.GOLD_LEGGINGS);
                materialMap.put(ItemType.BOOTS, Material.GOLD_BOOTS);
                break;
            default:
                break;
        }
    }

    public int getLadder()
    {
        return ladder;
    }

    public int getMinDmg()
    {
        return minDmg;
    }

    public int getMaxDmg()
    {
        return maxDmg;
    }

    public ChatColor getColor()
    {
        return chatColor;
    }

    public Map<ItemType, Material> getMaterialMap()
    {
        return materialMap;
    }

    public Material getMaterialByType(ItemType itemType)
    {
        return materialMap.get(itemType);
    }

    public static ItemTier random()
    {
        if (buffered)
        {
            return tierCollection.next();
        } else
        {
            generate();
            return tierCollection.next();
        }
    }

    private static void generate()
    {
        tierCollection.add(0.6, T1);
        tierCollection.add(0.4, T2);
        tierCollection.add(0.2, T3);
        tierCollection.add(0.1, T4);
        tierCollection.add(0.05, T4);
        buffered = true;
    }
}
