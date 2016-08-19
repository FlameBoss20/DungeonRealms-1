package net.dungeonrealms.beta.creature.listenening;

import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.creature.type.data.IAggressive;
import net.dungeonrealms.beta.handler.Handler;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.logging.Logger;

/**
 * Created by Giovanni on 17-8-2016.
 */
public class EntityListeningHandler implements Handler.ListeningHandler
{
    private static final Logger logger = Logger.getLogger(EntityListeningHandler.class.getName());

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
    public Logger getLogger()
    {
        return logger;
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent event)
    {
        if (((CraftEntity) event.getEntity()).getHandle() instanceof IAggressive)
        {

        }
    }

    //who thought it could be so simple?! :o
    @EventHandler
    public void onDeath(EntityDeathEvent event)
    {
        event.setDroppedExp(0);
        event.getDrops().clear();
        if (event.getEntity().getKiller() instanceof Player)
        {
            if (((CraftEntity) event.getEntity()).getHandle() instanceof IAggressive)
            {
                Player player = event.getEntity().getKiller();
                BetaHandler.getInstance().getPlayerHandler().getGamePlayerByUniqueId(
                        player.getUniqueId()).addExp(((IAggressive) event.getEntity()).getExpDrop());
            }
        }
    }
}
