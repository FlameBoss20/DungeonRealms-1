package net.dungeonrealms.proxy.connection;

import net.dungeonrealms.common.network.ShardInfo;
import net.dungeonrealms.network.GameClient;
import net.dungeonrealms.proxy.DungeonBungee;
import net.dungeonrealms.proxy.handler.Handler;
import net.dungeonrealms.proxy.listener.NetworkClientListener;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 11-8-2016.
 */
public class MasterConnectionHandler implements Handler
{
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private GameClient gameClient;

    @Override
    public boolean onEnable()
    {
        this.gameClient = new GameClient();

        try
        {
            this.gameClient.connect();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        new NetworkClientListener().startInitialization(this.gameClient);
        bufferShards();

        return false;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }

    public GameClient getGameClient()
    {
        return gameClient;
    }

    private void bufferShards()
    {
        Arrays.stream(ShardInfo.values()).forEach(info -> {
                    System.out.println("Register: " + info.getAddress().getAddress() + ":" + info.getAddress().getPort());
                    ServerInfo serverInfo = ProxyServer.getInstance().constructServerInfo(info.getPseudoName(),
                            new InetSocketAddress(info.getAddress().getAddress(), info.getAddress().getPort()), "", false);
                    ProxyServer.getInstance().getServers().put(info.getPseudoName(), serverInfo);
                }
        );
    }
}
