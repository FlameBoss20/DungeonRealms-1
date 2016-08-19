package net.dungeonrealms.beta.creature.type.data;

import net.dungeonrealms.beta.creature.CreatureType;
import org.bukkit.Location;

/**
 * Created by Giovanni on 12-8-2016.
 */
public interface Entity
{
    boolean isEquippable();

    CreatureType getCreatureType();

    void spawn(Location location);
}
