package net.dungeonrealms.proxy.connection;

import net.dungeonrealms.common.Constants;
import net.dungeonrealms.proxy.DungeonBungee;
import net.dungeonrealms.proxy.event.HandshakeAcceptedEvent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.Iterator;

/**
 * Created by Giovanni on 10-8-2016.
 */
public class PlayerConnectionManager implements Listener
{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onConnectionEstablish(ServerConnectEvent event)
    {
        if ((event.getPlayer().getServer() == null) || event.getTarget().getName().equals("Lobby"))
        {
            DungeonBungee.getInstance().getProxy().getPluginManager().callEvent(new HandshakeAcceptedEvent(event.getPlayer(), event.getTarget(), event));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onHandshakeAccept(HandshakeAcceptedEvent event)
    {
        if ((event.getPlayer().getServer() == null) || event.getTarget().getName().equals("Lobby"))
        {
            Iterator<ServerInfo> optimalLobbies = DungeonBungee.getInstance().getAvailableLobbies().iterator();

            while (optimalLobbies.hasNext())
            {

                ServerInfo target = optimalLobbies.next();

                if (!(event.getPlayer().getServer() != null && event.getPlayer().getServer().getInfo().equals(target)))
                {
                    try
                    {
                        event.getEvent().setTarget(target);
                    } catch (Exception e)
                    {
                        if (!optimalLobbies.hasNext())
                            event.getPlayer().disconnect(ChatColor.RED + "No lobbies available, please reconnect..");
                        else continue;
                    }

                    break;
                } else if (!optimalLobbies.hasNext())
                {
                    event.getPlayer().disconnect(ChatColor.RED + "No lobbies available, please reconnect..");
                    return;
                }

            }
        }
    }

    /**
     * Old events by the shitty dr devs
     * Will be fixed later.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTabComplete(TabCompleteEvent ev)
    {
        if (!(ev.getCursor().startsWith("/") || ev.getCursor().startsWith("@")))
            return;

        String partialPlayerName = ev.getCursor().toLowerCase();

        int lastSpaceIndex = partialPlayerName.lastIndexOf(' ');
        if (lastSpaceIndex >= 0) partialPlayerName = partialPlayerName.substring(lastSpaceIndex + 1);

        for (ProxiedPlayer p : DungeonBungee.getInstance().getProxy().getPlayers())
            if (p.getName().toLowerCase().startsWith(partialPlayerName))
                ev.getSuggestions().add(p.getName());
    }


    @EventHandler
    public void onProxyConnection(PreLoginEvent event)
    {

        // DUPE GLITCH FIX //
        DungeonBungee.getInstance().getProxy().getPlayers().stream().filter(p -> p.getUniqueId().equals(event.getConnection().getUniqueId())).forEach(p -> {
            if (p != null)
                p.disconnect(ChatColor.RED + "Another player with your account has logged into the server!");

            event.setCancelReason(ChatColor.RED + "Another player with your account has logged into the server!");
            event.setCancelled(true);
        });
    }


    @EventHandler
    public void onPing(ProxyPingEvent event)
    {
        ServerPing ping = event.getResponse();

        int players = ping.getPlayers().getOnline();
        ServerPing.PlayerInfo[] sample = ping.getPlayers().getSample();

        ping.setDescription(ChatColor.translateAlternateColorCodes('&', Constants.MOTD));
        ping.setPlayers(new ServerPing.Players(Constants.PLAYER_SLOTS, players, sample));
    }
}
