package net.dungeonrealms.beta.rift;

import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.rift.creature.entity.RiftEntity;
import net.minecraft.server.v1_9_R2.EntitySkeleton;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftSkeleton;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class RiftListener implements Handler.ListeningHandler
{
    private static final Logger logger = Logger.getLogger(RiftListener.class.getName());

    @Override
    public boolean onEnable()
    {
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

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event)
    {
        if (!(event.getEntity() instanceof Player))
        {
            Entity entity = event.getEntity();
            if (entity instanceof Skeleton)
            {
                if (((CraftSkeleton) event.getEntity()).getHandle() instanceof RiftEntity)
                {
                    EntitySkeleton fakeEntity = ((CraftSkeleton) event.getEntity()).getHandle();
                    if (((RiftEntity) fakeEntity).getParentRift().getLivingCreatures().contains(fakeEntity))
                    {
                        ((RiftEntity) fakeEntity).getParentRift().getLivingCreatures().remove(fakeEntity);
                        if (event.getEntity().getKiller() instanceof Player)
                        {
                            event.getEntity().getKiller().sendMessage("test, check console for debug messages.");
                            System.out.println("RIFTENITY KILLED"); //debug
                            System.out.println("Rift: " + ((RiftEntity) fakeEntity).getParentRift().getRiftId());
                            System.out.println("Tier: " + ((RiftEntity) fakeEntity).getParentRift().getRiftTier().name());
                        }
                    } else
                    {
                        logger.log(Level.WARNING, "An entity error occurred, creature is non existant?");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            //TODO
        }
    }
}
