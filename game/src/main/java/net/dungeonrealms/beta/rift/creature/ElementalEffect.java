package net.dungeonrealms.beta.rift.creature;

import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.rift.Rift;
import net.dungeonrealms.beta.rift.creature.entity.RiftEntity;
import net.minecraft.server.v1_9_R2.Entity;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftEntity;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 14-8-2016.
 */
public class ElementalEffect implements Handler.TaskedHandler
{
    private static final Logger logger = Logger.getLogger(ElementalEffect.class.getName());

    @Override
    public boolean onEnable()
    {
        return false;
    }

    @Override
    public boolean supered()
    {
        return false;
    }

    @Override
    public void onImmediateStop()
    {

    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }

    @Override
    public void run()
    {
        for (Rift rift : BetaHandler.getInstance().getRiftHandler().getEnabledRifts().values())
        {
            for (Entity entity : rift.getLivingCreatures())
            {
                if (entity instanceof RiftEntity)
                {
                    if (((RiftEntity) entity).isElemental())
                    {
                        if (((RiftEntity) entity).getElement() != RiftCreatureElement.EMPTY)
                        {
                            CraftEntity craftEntity = entity.getBukkitEntity();
                            craftEntity.getWorld().spigot().playEffect(craftEntity.getLocation(), ((RiftEntity) entity).getElement().getEffect(), 0, 0, 0.1f, 1f, 0.1f, 0.1f, 50, 0);
                        }
                    }
                } else
                {
                    logger.log(Level.WARNING, "A [riftedEntity] does not seem to be an actual RiftEntity!");
                }
            }
        }
    }
}
