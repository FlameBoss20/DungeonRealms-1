package net.dungeonrealms.beta.addon;

import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.handler.Handler;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.logging.Logger;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class ItemDropHandler implements Handler.ListeningHandler
{
    private static final Logger logger = Logger.getLogger(ItemDropHandler.class.getPackage().getName().toUpperCase() + "HANDLER");

    @Override
    public boolean onEnable()
    {
        BetaHandler.getDungeonRealms().getServer().getPluginManager().registerEvents(this, BetaHandler.getDungeonRealms());
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
    public void playerDropItem(PlayerDropItemEvent event)
    {
        /*
        if (BetaHandler.getInstance().getSpawnedItems().containsValue(event.getItemDrop().getItemStack()))
        {
            event.getItemDrop().remove();
            event.getPlayer().sendMessage(ChatColor.GRAY.toString() + ChatColor.ITALIC + "This item has been despawned..");
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1f, 1f);
        } */
    }
}
