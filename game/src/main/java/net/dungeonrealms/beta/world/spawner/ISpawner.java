package net.dungeonrealms.beta.world.spawner;

import net.dungeonrealms.beta.creature.CreatureType;
import org.bukkit.Location;

import java.util.UUID;

/**
 * Created by Giovanni on 15-8-2016.
 */
public interface ISpawner
{
    CreatureType getSpawnType();

    int getMaxSpawnable();

    UUID getGameId();

    Location getLocation();

    void spawn();
}
