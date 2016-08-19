package net.dungeonrealms.beta.event;

import net.dungeonrealms.beta.player.GamePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Giovanni on 16-8-2016.
 */
public class ValidPlayerJoinEvent extends Event
{
    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private GamePlayer gamePlayer;

    public ValidPlayerJoinEvent(Player player, GamePlayer gamePlayer)
    {
        this.player = player;
        this.gamePlayer = gamePlayer;
    }

    public Player getPlayer()
    {
        return player;
    }

    public GamePlayer getGamePlayer()
    {
        return gamePlayer;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
