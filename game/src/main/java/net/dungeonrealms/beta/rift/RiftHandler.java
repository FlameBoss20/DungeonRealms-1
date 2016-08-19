package net.dungeonrealms.beta.rift;

import com.google.common.collect.Maps;
import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.constant.BetaConstants;
import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.rift.creature.ElementalEffect;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class RiftHandler implements Handler.TaskedHandler
{
    private static final Logger logger = Logger.getLogger(RiftHandler.class.getName());

    private Map<UUID, Rift> enabledRifts;

    @Override
    public boolean onEnable()
    {
        this.enabledRifts = Maps.newHashMap();
        BetaHandler.getDungeonRealms().getServer().getScheduler().scheduleAsyncRepeatingTask(BetaHandler.getDungeonRealms(), this, 0, 20 * BetaConstants.RIFT_SPAWN_TIME);
        BetaHandler.getDungeonRealms().getServer().getScheduler().scheduleAsyncRepeatingTask(BetaHandler.getDungeonRealms(), new ElementalEffect(), 0, 20 * BetaConstants.ENTITY_ELEMENT_PACKET);
        return false;
    }

    @Override
    public void onImmediateStop()
    {
        enabledRifts.values().forEach(Rift::stop);
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

    @Override
    public void run()
    {
        if (enabledRifts.size() < 2)
        {
            Rift rift = new Rift();
            enabledRifts.put(rift.getRiftId(), rift);
            rift.start();
        } else
        {
            logger.log(Level.INFO, "A Rift has been prevented from starting due to the fact that there are already 2 open.");
        }
    }

    public Map<UUID, Rift> getEnabledRifts()
    {
        return enabledRifts;
    }

    public Rift getByUUID(UUID uuid)
    {
        return enabledRifts.get(uuid);
    }
}
