package net.dungeonrealms.beta.creature;

import net.dungeonrealms.beta.item.data.ItemTier;

/**
 * Created by Giovanni on 15-8-2016.
 */
public enum CreatureTier
{
    T1(1, 15, 5, 15, 15, 60, ItemTier.T1), T2(15, 30, 30, 45, 120, 400, ItemTier.T2),
    T3(30, 45, 60, 75, 400, 800, ItemTier.T3), T4(45, 60, 90, 105, 800, 1500, ItemTier.T4),
    T5(60, 80, 120, 135, 1500, 2500, ItemTier.T5);

    private int minLevel;
    private int maxLevel;

    private int minDmg;
    private int maxDmg;

    private int minHealth;
    private int maxHealth;

    private ItemTier itemTier;

    CreatureTier(int minLevel, int maxLevel, int minDmg, int maxDmg, int minHealth, int maxHealth, ItemTier itemTier)
    {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.minDmg = minDmg;
        this.maxDmg = maxDmg;
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
        this.itemTier = itemTier;
    }

    public int getMinLevel()
    {
        return minLevel;
    }

    public int getMaxLevel()
    {
        return maxLevel;
    }

    public int getMinDmg()
    {
        return minDmg;
    }

    public int getMaxDmg()
    {
        return maxDmg;
    }

    public int getMinHealth()
    {
        return minHealth;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public ItemTier getItemTier()
    {
        return itemTier;
    }
}
