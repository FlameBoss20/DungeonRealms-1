package net.dungeonrealms.beta.world.spawner;

import com.google.common.collect.Lists;
import net.dungeonrealms.beta.creature.CreatureTier;
import net.dungeonrealms.beta.creature.CreatureType;
import net.dungeonrealms.beta.creature.type.CreatureBandit;
import net.dungeonrealms.beta.creature.type.data.IAggressive;
import net.dungeonrealms.beta.utilities.location.RangeChecker;
import net.dungeonrealms.beta.utilities.math.IntegerUtil;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Created by Giovanni on 15-8-2016.
 */
public class SpawnerObject implements ISpawner
{
    private CreatureType creatureType;
    private int max;
    private int radius;

    private List<IAggressive> entities;
    private CreatureTier creatureTier;

    private UUID uuid;
    private Location location;

    public SpawnerObject(UUID uuid, Location location, CreatureType creatureType, CreatureTier creatureTier, int maxSpawn, int radius)
    {
        this.creatureType = creatureType;
        this.creatureTier = creatureTier;
        this.max = maxSpawn;
        this.radius = radius;
        this.uuid = uuid;
        this.location = location;
        this.entities = Lists.newArrayList();
    }

    @Override
    public CreatureType getSpawnType()
    {
        return creatureType;
    }

    @Override
    public int getMaxSpawnable()
    {
        return max;
    }

    @Override
    public UUID getGameId()
    {
        return uuid;
    }

    @Override
    public Location getLocation()
    {
        return location;
    }

    @Override
    public void spawn()
    {
        //TODO making this better, this was for a test.
        org.bukkit.entity.Entity[] nearby = RangeChecker.getEntitiesInRange(Player.class, location, 10);
        if (nearby.length != 0) //if there are players in a radius of 10x10x10 near our spawner.
        {
            if (entities.size() < max)
            {
                if (creatureType == CreatureType.BANDIT)
                {
                    Location actualLocation = new Location(location.getWorld(), location.getX() + IntegerUtil.randomInteger(1, radius), location.getY(),
                            location.getZ() + IntegerUtil.randomInteger(1, radius));
                    CreatureBandit creatureBandit = new CreatureBandit(((CraftWorld) location.getWorld()).getHandle(), creatureTier, this);
                    creatureBandit.spawn(actualLocation);
                    entities.add(creatureBandit);
                }
            }
        }
    }

    public List<IAggressive> getEntities()
    {
        return entities;
    }

    public CreatureTier getCreatureTier()
    {
        return creatureTier;
    }

    public int getRadius()
    {
        return radius;
    }
}
