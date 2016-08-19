package net.dungeonrealms.beta.rift.data;

/**
 * Created by Giovanni on 13-8-2016.
 */
public enum RiftCreatureTier
{
    T1(1000, 1500), T2(1400, 2100), T3(2100, 2800), T4(2800, 3400), T5(3300, 4000);

    private int minHealth;
    private int maxHealth;

    RiftCreatureTier(int minHealth, int maxHealth)
    {
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public int getMinHealth()
    {
        return minHealth;
    }
}
