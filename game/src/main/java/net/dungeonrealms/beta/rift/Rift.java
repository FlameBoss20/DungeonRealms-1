package net.dungeonrealms.beta.rift;

import com.google.common.collect.Lists;
import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.constant.BetaConstants;
import net.dungeonrealms.beta.utilities.location.RangeChecker;
import net.dungeonrealms.beta.utilities.math.IntegerUtil;
import net.dungeonrealms.beta.prompt.PromptType;
import net.dungeonrealms.beta.prompt.VStringBuilder;
import net.dungeonrealms.beta.rift.creature.entity.RiftEntity;
import net.dungeonrealms.beta.rift.data.RiftTier;
import net.minecraft.server.v1_9_R2.Entity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class Rift implements IRift
{
    private RiftTier riftTier;
    private boolean started = false;

    private UUID riftId;

    private List<Entity> creatures;
    private List<Entity> aliveCreatures;
    private int spawnableCreatures;

    private Location location;

    public Rift()
    {
        this.riftTier = RiftTier.random();
        this.riftId = UUID.randomUUID();
        this.creatures = Lists.newArrayList();
        this.aliveCreatures = Lists.newArrayList();
        this.spawnableCreatures = IntegerUtil.randomInteger(riftTier.getMinCreatures(), riftTier.getMaxCreatures());
        /** test **/
        this.location = new Location(Bukkit.getWorld("DungeonRealms"), -616, 61, 544); //test
        System.out.println("Spawnables: " + spawnableCreatures);
    }

    public Rift(RiftTier riftTier)
    {
        this.riftTier = riftTier;
        this.riftId = UUID.randomUUID();
        this.creatures = Lists.newArrayList();
        this.aliveCreatures = Lists.newArrayList();
        this.spawnableCreatures = IntegerUtil.randomInteger(riftTier.getMinCreatures(), riftTier.getMaxCreatures());
    }

    @Override
    public RiftTier getRiftTier()
    {
        return riftTier;
    }

    public void start()
    {
        try
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 1f, 1f);
            }
        } catch (Exception e)
        {
            System.out.println("[RIFT] Player list is empty.");
        }

        startRift();
    }

    private void startRift()
    {
        started = true;
        BetaHandler.getInstance().broadcast(PromptType.RIFT,
                VStringBuilder.build("&7A tier &b" + riftTier.getId() + " &7rift has opened!", true));
        sound();
        creatures();
        handleStop();
    }

    private void sound()
    {
        if (started)
        {
            BetaHandler.getDungeonRealms().getServer().getScheduler().scheduleAsyncRepeatingTask(BetaHandler.getDungeonRealms(), () -> {
                location.getWorld().playSound(location, Sound.BLOCK_PORTAL_AMBIENT, 8f, 0.4f);
            }, 0, 20 * 4);
        }
    }

    private void creatures()
    {
        if (started)
        {
            BetaHandler.getDungeonRealms().getServer().getScheduler().scheduleSyncRepeatingTask(BetaHandler.getDungeonRealms(), () -> {
                if (location != null)
                {
                    org.bukkit.entity.Entity[] nearby = RangeChecker.getEntitiesInRange(Player.class, location, 10);
                    if (nearby.length != 0)
                    {
                        if (creatures.size() < spawnableCreatures)
                        {
                            spawnCreature();
                        } else
                        {
                            return;
                        }
                    } else
                    {
                        return;
                    }
                } else
                {
                    return;
                }
                //the ladder of lols..
            }, 0L, 20 * 10);
        }
    }

    private void spawnCreature()
    {
        RiftEntity riftEntity = new RiftEntity(((CraftWorld) location.getWorld()).getHandle(), this);
        Location spawnLocation = new Location(location.getWorld(), location.getX() + IntegerUtil.randomInteger(1, 3), location.getY(),
                location.getZ() + IntegerUtil.randomInteger(1, 3));
        riftEntity.spawn(spawnLocation);
        creatures.add(riftEntity);
        aliveCreatures.add(riftEntity);
    }

    //TODO check if entity is not too far away from rift.


    public void handleStop()
    {
        BetaHandler.getDungeonRealms().getServer().getScheduler().scheduleAsyncDelayedTask(BetaHandler.getDungeonRealms(),
                () -> stop(), 20 * BetaConstants.RIFT_DESPAWN_TIME);
    }

    protected void stop()
    {
        this.started = false;

        for (Entity entity : creatures)
        {
            entity.getBukkitEntity().remove();
        }

        creatures.clear();
        aliveCreatures.clear();

        BetaHandler.getInstance().broadcast(PromptType.RIFT,
                VStringBuilder.build("&cA rift has been closed..", true));
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 1f, 1f);
        }
    }

    public void dangerStop()
    {
        this.stop();
    }

    public boolean isStarted()
    {
        return started;
    }

    public int getSpawnableCreatures()
    {
        return spawnableCreatures;
    }

    public List<Entity> getAllCreatures()
    {
        return creatures;
    }

    public List<Entity> getLivingCreatures()
    {
        return aliveCreatures;
    }

    public UUID getRiftId()
    {
        return riftId;
    }

    public Location getLocation()
    {
        return location;
    }
}
