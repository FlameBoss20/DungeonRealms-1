package net.dungeonrealms.beta.item.data;

import net.dungeonrealms.beta.collection.WeightedCollection;
import org.bukkit.ChatColor;

/**
 * Created by Giovanni on 12-8-2016.
 */
public enum ItemRarity
{
    COMMON(ChatColor.GRAY + "Common", 0, 5, 9), UNCOMMON(ChatColor.GREEN + "Uncommon", 1, 7, 15),
    RARE(ChatColor.AQUA + "Rare", 2, 12, 20), UNIQUE(ChatColor.YELLOW + "Unique", 3, 16, 26), LEGENDARY(ChatColor.LIGHT_PURPLE + "Legendary", 4, 31, 42); //new rarity :o

    private static WeightedCollection<ItemRarity> rarityCollection = new WeightedCollection<ItemRarity>();
    private static boolean buffered = false;

    private String prefix;
    private int ladder;
    private int minDmg;
    private int maxDmg;

    ItemRarity(String prefix, int ladder, int minDmg, int maxDmg)
    {
        this.prefix = prefix;
        this.ladder = ladder;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
    }

    public int getLadder()
    {
        return ladder;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public int getMinDmg()
    {
        return minDmg;
    }

    public int getMaxDmg()
    {
        return maxDmg;
    }

    public static ItemRarity random()
    {
        if (buffered)
        {
            return rarityCollection.next();
        } else
        {
            generate();
            return rarityCollection.next();
        }
    }

    private static void generate()
    {
        rarityCollection.add(0.6, COMMON);
        rarityCollection.add(0.4, UNCOMMON);
        rarityCollection.add(0.2, RARE);
        rarityCollection.add(0.1, UNIQUE);
        rarityCollection.add(0.05, LEGENDARY);
        buffered = true;
    }
}
