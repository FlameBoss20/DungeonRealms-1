package net.dungeonrealms.beta.world.spawner;

import com.google.common.collect.Maps;
import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.creature.CreatureTier;
import net.dungeonrealms.beta.creature.CreatureType;
import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.world.WorldConstants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 15-8-2016.
 */
public class SpawnerHandler implements Handler.TaskedHandler
{
    private static final Logger logger = Logger.getLogger(SpawnerHandler.class.getName());

    private YamlConfiguration spawnerData;

    private Map<UUID, SpawnerObject> spawners;

    @Override
    public boolean onEnable()
    {
        setupSpawnerStorage();
        this.spawners = Maps.newHashMap();
        createSpawners();
        BetaHandler.getDungeonRealms().getServer().getScheduler().scheduleAsyncRepeatingTask(BetaHandler.getDungeonRealms(),
                this, 0, 20 * WorldConstants.CREATURE_SPAWN_INTERVAL_DEFAULT);
        return false;
    }

    @Override
    public boolean supered()
    {
        return false;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }

    @Override
    public void run()
    {
        for(SpawnerObject spawnerObject : spawners.values())
        {
            spawnerObject.spawn();
        }
    }

    @Override
    public void onImmediateStop()
    {
        storeSpawners();
    }

    private void setupSpawnerStorage()
    {
        File file = new File(BetaHandler.getDungeonRealms().getDataFolder(), "spawner_data.yml");
        spawnerData = YamlConfiguration.loadConfiguration(file);
        if (!file.exists())
        {
            spawnerData.createSection("spawners");
            spawnerData.addDefault("spawners.test.creature", CreatureType.BANDIT.name());
            spawnerData.addDefault("spawners.test.id", UUID.randomUUID().toString());
            spawnerData.addDefault("spawners.test.radius", 5);
            spawnerData.addDefault("spawners.test.max", 10);
            spawnerData.addDefault("spawners.test.tier", CreatureTier.T3.name());
            spawnerData.addDefault("spawners.test.world", "DungeonRealms");
            spawnerData.addDefault("spawners.test.x", 0);
            spawnerData.addDefault("spawners.test.y", 0);
            spawnerData.addDefault("spawners.test.z", 0);
            spawnerData.options().copyDefaults(true);
        }
    }

    private void createSpawners()
    {
        ConfigurationSection configurationSection = spawnerData.getConfigurationSection("spawners");
        for (String keys : configurationSection.getKeys(false))
        {
            CreatureType creatureType = CreatureType.valueOf(spawnerData.getString("spawners." + keys + ".creature"));
            CreatureTier creatureTier = CreatureTier.valueOf(spawnerData.getString("spawners." + keys + ".tier"));
            UUID uuid = UUID.fromString(spawnerData.getString("spawners." + keys + ".id"));
            int radius = spawnerData.getInt("spawners." + keys + ".radius");
            int maxEntities = spawnerData.getInt("spawners." + keys + ".max");
            World world = Bukkit.getWorld(spawnerData.getString("spawners." + keys + ".world"));
            Location location = new Location(world, spawnerData.getInt("spawners." + keys + ".x"),
                    spawnerData.getInt("spawners." + keys + ".y"),
                    spawnerData.getInt("spawners." + keys + ".z"));
            spawners.put(uuid, new SpawnerObject(uuid, location, creatureType, creatureTier, maxEntities, radius));
        }
    }

    private void storeSpawners()
    {
        for(SpawnerObject spawnerObject : spawners.values())
        {
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".creature", spawnerObject.getSpawnType().name());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".tier", spawnerObject.getCreatureTier().name());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".id", spawnerObject.getGameId().toString());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".radius", spawnerObject.getRadius());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".max", spawnerObject.getMaxSpawnable());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".world", spawnerObject.getLocation().getWorld());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".x", spawnerObject.getLocation().getX());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".y", spawnerObject.getLocation().getY());
            spawnerData.set("spawners." + spawnerObject.getGameId() + ".z", spawnerObject.getLocation().getZ());
        }
    }
}
