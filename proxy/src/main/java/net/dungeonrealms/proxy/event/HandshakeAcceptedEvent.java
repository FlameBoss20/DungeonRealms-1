package net.dungeonrealms.proxy.event;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Event;

import java.util.logging.Level;

/**
 * Created by Giovanni on 10-8-2016.
 */
public class HandshakeAcceptedEvent extends Event implements IBungeeEvent
{
    private Level logLevel;
    private String logMessage;

    private final ProxiedPlayer proxiedPlayer;
    private ServerInfo serverInfo;

    private ServerConnectEvent event;

    public HandshakeAcceptedEvent(ProxiedPlayer proxiedPlayer, ServerInfo serverInfo, ServerConnectEvent event)
    {
        this.proxiedPlayer = proxiedPlayer;
    }

    @Override
    public void setLogLevel(Level logLevel)
    {
        this.logLevel = logLevel;
    }

    @Override
    public void setLogMessage(String logMessage)
    {
        this.logMessage = logMessage;
    }

    public ProxiedPlayer getPlayer()
    {
        return proxiedPlayer;
    }

    public ServerInfo getTarget()
    {
        return serverInfo;
    }

    public ServerConnectEvent getEvent()
    {
        return event;
    }
}
