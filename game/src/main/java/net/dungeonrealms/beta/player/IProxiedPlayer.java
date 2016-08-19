package net.dungeonrealms.beta.player;

import java.util.UUID;

/**
 * Created by Giovanni on 15-8-2016.
 */
public interface IProxiedPlayer
{
    UUID getUniqueId();

    int getGems();

    int getMaxHealth();

    int getHealth();

    void updateHealth();

    int getExp();

    int getLevel();
}
