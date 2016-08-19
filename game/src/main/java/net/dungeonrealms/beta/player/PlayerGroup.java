package net.dungeonrealms.beta.player;

import org.bukkit.ChatColor;

/**
 * Created by Giovanni on 15-8-2016.
 */
public enum PlayerGroup
{
    DEFAULT("", 0),
    SUB(ChatColor.GREEN.toString() + ChatColor.BOLD +  "S ", 2),
    SUB_1(ChatColor.YELLOW.toString()  + ChatColor.BOLD + "S+ ", 4),
    SUB_2(ChatColor.GOLD.toString() + ChatColor.BOLD + "S++ ", 6),
    PMOD(ChatColor.WHITE.toString() + ChatColor.BOLD + "PMOD", 10),
    GM(ChatColor.AQUA.toString() + ChatColor.BOLD + "GM", 100),
    DEV(ChatColor.GOLD.toString() + ChatColor.BOLD + "DEV", 1000),
    FOUNDER(ChatColor.RED.toString() + ChatColor.BOLD + "FOUNDER", 1000); //Availer?

    private String name;
    private int ladder;

    PlayerGroup(String name, int ladder)
    {
        this.ladder = ladder;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public int getLadder()
    {
        return ladder;
    }

    public boolean isRank(PlayerGroup rank)
    {
        return this.ladder >= rank.getLadder();
    }

    public String getPrefix(boolean caps)
    {
        if (caps)
            return name.toUpperCase();
        return name;
    }
}
