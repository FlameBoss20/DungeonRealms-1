package net.dungeonrealms.proxy;

import com.google.common.collect.Maps;
import net.dungeonrealms.proxy.command.CommandAlert;
import net.dungeonrealms.proxy.connection.MasterConnectionHandler;
import net.dungeonrealms.proxy.connection.PlayerConnectionManager;
import net.dungeonrealms.proxy.handler.Handler;
import net.dungeonrealms.proxy.listener.ProxyChannelListener;
import net.dungeonrealms.proxy.packet.PacketHandler;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Giovanni on 10-8-2016.
 */
public class DungeonBungee extends Plugin
{
    private static final Logger logger = Logger.getLogger(DungeonBungee.class.getName());

    private static DungeonBungee instance;
    private PacketHandler packetHandler;
    private MasterConnectionHandler masterConnectionHandler;

    private List<ServerInfo> availableLobbies;
    private boolean buffered = false;

    private HashMap<String, Handler> handlers;

    public void onEnable()
    {
        logger.log(Level.INFO, "Enabling DungeonBungee [..]");
        instance = this;
        this.packetHandler = new PacketHandler();
        this.masterConnectionHandler = new MasterConnectionHandler();
        this.handlers = Maps.newHashMap();

        this.getProxy().getPluginManager().registerListener(this, new PlayerConnectionManager());
        this.getProxy().getPluginManager().registerListener(this, new ProxyChannelListener(this.getInstance()));
        this.getProxy().getPluginManager().registerCommand(this, new CommandAlert());

        handlers.put(packetHandler.getClass().getName(), packetHandler);
        handlers.put(masterConnectionHandler.getClass().getName(), masterConnectionHandler);

        for (Handler handler : handlers.values())
        {
            handler.onEnable();
            handler.getLogger().log(Level.INFO, "VBungeeHandler is enabling.");
        }
    }

    public void onDisable()
    {
        for (Handler handler : handlers.values())
        {
            handler.getLogger().log(Level.INFO, "VBungeeHandler is disabling.");
        }
    }

    public static DungeonBungee getInstance()
    {
        return instance;
    }

    public HashMap<String, Handler> getHandlers()
    {
        return handlers;
    }

    public MasterConnectionHandler getMasterHandler()
    {
        return masterConnectionHandler;
    }

    public PacketHandler getPacketHandler()
    {
        return packetHandler;
    }

    public Handler getHandlerByName(String name)
    {
        for (String keys : handlers.keySet())
        {
            if (keys.equalsIgnoreCase(name))
            {
                return handlers.get(name);
            }
        }
        return null;
    }

    public static Logger getBungeeLogger()
    {
        return logger;
    }


    public List<ServerInfo> getAvailableLobbies()
    {
        if (!buffered)
        {
            availableLobbies = getProxy().getServers().values().stream().filter(server -> server.getName().contains("Lobby")).collect(Collectors.toList());
            Collections.sort(availableLobbies, (o1, o2) -> o1.getPlayers().size() - o2.getPlayers().size());
            this.buffered = true;
            logger.log(Level.INFO, "Shards buffered.");
            return availableLobbies;
        } else
        {
            return availableLobbies;
        }
    }

    public boolean rebufferLobbies()
    {
        availableLobbies = getProxy().getServers().values().stream().filter(server -> server.getName().contains("Lobby")).collect(Collectors.toList());
        Collections.sort(availableLobbies, (o1, o2) -> o1.getPlayers().size() - o2.getPlayers().size());
        this.buffered = true;
        logger.log(Level.INFO, "Shards buffered.");
        return buffered;
    }
}
