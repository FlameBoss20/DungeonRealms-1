package net.dungeonrealms.beta.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Giovanni on 15-8-2016.
 */
public class WorldConstants
{
    /**
     * This does not contain regions, this only contains spawn-points.
     *
     * Like the spawn point for tutorial island and such.
     */
    public static final Location CYERNNICA_MAIN_SPAWN = new Location(Bukkit.getWorld("cyrennica"), 0, 0, 0);
    //TODO someone fix this ^

    public static final int CREATURE_SPAWN_INTERVAL_DEFAULT = 5;
}
