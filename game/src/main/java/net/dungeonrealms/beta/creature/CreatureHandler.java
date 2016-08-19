package net.dungeonrealms.beta.creature;

import com.google.common.collect.Maps;
import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.creature.type.*;
import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.reflection.VReflection;
import net.dungeonrealms.beta.rift.creature.entity.RiftEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class CreatureHandler implements Handler
{
    private static final Logger logger = Logger.getLogger(CreatureHandler.class.getName());

    private final String path = "data.";

    private YamlConfiguration npcConfiguration;

    private Map<UUID, CreatureNPC> npcCreatures;

    @Override
    public boolean onEnable()
    {
        //this.npcCreatures = Maps.newHashMap();
        //setupNPCStorage();
        //createNPCs();

        //zombie
        this.registerEntity(CreatureBandit.class, "Bandit", 54);
        this.registerEntity(CreatureGoblin.class, "Goblin", 54);
        this.registerEntity(CreatureTroll.class, "Troll", 54);
        this.registerEntity(CreatureNaga.class, "Naga", 54);

        //skeleton
        this.registerEntity(CreatureSkeleton.class, "Skeleton", 51);
        this.registerEntity(RiftEntity.class, "Corrupted Entity", 51);

        //golem
        this.registerEntity(CreatureGolem.class, "Golem[iron]", 99);

        return false;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }

    @Override
    public boolean supered()
    {
        return false;
    }

    private void registerEntity(Class clazz, String name, int id)
    {
        ((Map) VReflection.getPrivateField("c", net.minecraft.server.v1_9_R2.EntityTypes.class, null)).put(name, clazz);
        ((Map) VReflection.getPrivateField("d", net.minecraft.server.v1_9_R2.EntityTypes.class, null)).put(clazz, name);
        ((Map) VReflection.getPrivateField("f", net.minecraft.server.v1_9_R2.EntityTypes.class, null)).put(clazz, Integer.valueOf(id));
        System.out.println("Creature registration for: [" + name + "]" + " (...)");
    }

    private void setupNPCStorage()
    {
        File file = new File(BetaHandler.getDungeonRealms().getDataFolder(), "npc_data.yml");
        npcConfiguration = YamlConfiguration.loadConfiguration(file);
        if (!file.exists())
        {
            //this is for testing
            npcConfiguration.createSection("data");
            npcConfiguration.addDefault("data.bnpc_1.name", "&a&lBeta NPC");
            npcConfiguration.addDefault("data.bnpc_1.profile", UUID.randomUUID().toString());
            npcConfiguration.addDefault("data.bnpc_1.type", NPCType.VENDOR.name());
            npcConfiguration.addDefault("data.bnpc_1.world", "cyrennica");
            npcConfiguration.addDefault("data.bnpc_1.x", 0);
            npcConfiguration.addDefault("data.bnpc_1.y", 0);
            npcConfiguration.addDefault("data.bnpc_1.z", 0);
            npcConfiguration.options().copyDefaults(true);
        }
    }

    private void createNPCs()
    {
        logger.log(Level.INFO, " -> NPC creation started, this may take a while.");
        ConfigurationSection configurationSection = npcConfiguration.getConfigurationSection("data");
        for (String key : configurationSection.getKeys(false))
        {
            try
            {
                String name = npcConfiguration.getString("data." + key + ".name");
                UUID profile = UUID.fromString(npcConfiguration.getString("data." + key + ".profile"));
                NPCType npcType = NPCType.valueOf(npcConfiguration.getString("data." + key + ".type"));
                World world = Bukkit.getWorld(npcConfiguration.getString("data." + key + ".world"));
                Location location = new Location(world, npcConfiguration.getInt("data." + ".x"),
                        npcConfiguration.getInt("data." + ".y"), npcConfiguration.getInt("data." + ".z"));
                npcCreatures.put(profile, new CreatureNPC(npcType, name, profile, location, false));
                for (CreatureNPC creatureNPC : npcCreatures.values())
                {
                    creatureNPC.spawn(location);
                }
            } catch (Exception e)
            {
                getLogger().log(Level.WARNING, "There was an error whilst creating existant NPC creatures.");
                e.printStackTrace();
            }
        }
    }

    public Map<UUID, CreatureNPC> getNPCCreatures()
    {
        return npcCreatures;
    }

    public YamlConfiguration getNPCConfiguration()
    {
        return npcConfiguration;
    }

    public String getConfigurationPath()
    {
        return path;
    }
}
