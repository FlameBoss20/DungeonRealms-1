package net.dungeonrealms.beta.rift.data;

import net.dungeonrealms.beta.collection.WeightedCollection;
import net.dungeonrealms.beta.item.data.ItemTier;

/**
 * Created by Giovanni on 12-8-2016.
 */
public enum RiftTier
{
    T1(ItemTier.T1, RiftCreatureTier.T1, 10, 25, 50, 80, 1), T2(ItemTier.T2, RiftCreatureTier.T2, 25, 35, 80, 120, 2),
    T3(ItemTier.T3, RiftCreatureTier.T3, 35, 50, 120, 140, 3),
    T4(ItemTier.T4, RiftCreatureTier.T4, 50, 70, 140, 170, 4), T5(ItemTier.T5, RiftCreatureTier.T5, 70, 100, 170, 200, 5);

    private static WeightedCollection<RiftTier> tierRarity = new WeightedCollection<RiftTier>();
    private static boolean buffered = false;

    private ItemTier itemTier;
    private RiftCreatureTier riftCreatureTier;
    private int minCreatures;
    private int maxCreatures;
    private int minLevel;
    private int maxLevel;
    private int id;

    RiftTier(ItemTier itemTier, RiftCreatureTier riftCreatureTier, int minCreatures, int maxCreatures, int minLevel, int maxLevel, int id)
    {
        this.itemTier = itemTier;
        this.riftCreatureTier = riftCreatureTier;
        this.minCreatures = minCreatures;
        this.maxCreatures = maxCreatures;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.id = id;
    }

    public ItemTier getItemTier()
    {
        return itemTier;
    }

    public RiftCreatureTier getRiftCreatureTier()
    {
        return riftCreatureTier;
    }

    public int getMaxCreatures()
    {
        return maxCreatures;
    }

    public int getMinCreatures()
    {
        return minCreatures;
    }

    public int getId()
    {
        return id;
    }

    public int getMaxLevel()
    {
        return maxLevel;
    }

    public int getMinLevel()
    {
        return minLevel;
    }

    public static RiftTier random()
    {
        if (!buffered) //to make sure we don't keep adding tier weights.
        {
            generate();
            return tierRarity.next();
        } else
        {
            return tierRarity.next();
        }
    }

    private static void generate()
    {
        tierRarity.add(0.5, T1);
        tierRarity.add(0.5, T2);
        tierRarity.add(0.3, T3);
        tierRarity.add(0.2, T4);
        tierRarity.add(0.1, T5);
        buffered = true;
    }
}
