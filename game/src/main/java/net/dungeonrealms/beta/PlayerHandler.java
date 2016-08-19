package net.dungeonrealms.beta;

import net.dungeonrealms.beta.event.ValidPlayerJoinEvent;
import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.player.GamePlayer;
import net.dungeonrealms.beta.prompt.VStringBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 15-8-2016.
 */
public class PlayerHandler implements Handler.ListeningHandler
{
    private static final Logger logger = Logger.getLogger(PlayerHandler.class.getName());

    private ConcurrentHashMap<UUID, GamePlayer> playerCache;

    @Override
    public boolean onEnable()
    {
        this.playerCache = new ConcurrentHashMap<>();
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
    public void asyncPre(AsyncPlayerPreLoginEvent event)
    {
        //TODO get player data out of mongo, make a new model for him and add him to the cache.
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage(null);
        if (!playerCache.containsKey(event.getPlayer().getUniqueId()))
        {
            event.getPlayer().kickPlayer(VStringBuilder.build("&c&lDATA ERROR \n&7Non existant model! \n\n&7Visit &fwww.dungeonrealms.org",
                    false));
        } else
        {
            BetaHandler.getDungeonRealms().getServer().getPluginManager().callEvent(new ValidPlayerJoinEvent(event.getPlayer(),
                    playerCache.get(event.getPlayer().getUniqueId())));
        }
    }

    @EventHandler
    public void validJoin(ValidPlayerJoinEvent event)
    {
        event.getGamePlayer().promptWelcome();
        event.getGamePlayer().updateHealth();
        event.getPlayer().teleport(event.getGamePlayer().spawn());
    }


    public ConcurrentHashMap<UUID, GamePlayer> getPlayerCache()
    {
        return playerCache;
    }

    public GamePlayer getGamePlayerByUniqueId(UUID uuid)
    {
        try
        {
            return playerCache.get(uuid);
        } catch (Exception e)
        {
            logger.log(Level.WARNING, "Failed to get a GamePlayer model for: " + uuid.toString());
            e.printStackTrace();
        }
        return null;
    }
}
