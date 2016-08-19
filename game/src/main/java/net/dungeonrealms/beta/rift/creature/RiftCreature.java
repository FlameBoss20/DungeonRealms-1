package net.dungeonrealms.beta.rift.creature;

import net.dungeonrealms.beta.rift.Rift;

/**
 * Created by Giovanni on 12-8-2016.
 */
public interface RiftCreature
{
    Rift getParentRift();

    int getExpDrop();

    boolean isElemental();
}
