package net.dungeonrealms.beta.creature.type.data;

import net.dungeonrealms.beta.creature.CreatureTier;
import net.dungeonrealms.beta.creature.type.element.CreatureElement;
import net.dungeonrealms.beta.world.spawner.SpawnerObject;

/**
 * Created by Giovanni on 15-8-2016.
 */
public interface IAggressive extends Entity
{
    CreatureTier getCreatureTier();

    CreatureElement getCreatureElement();

    boolean isElemental();

    int getExpDrop();

    String getName();

    SpawnerObject getSpawnerObject();
}
