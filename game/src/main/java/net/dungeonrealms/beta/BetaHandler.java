package net.dungeonrealms.beta;

import com.google.common.collect.Maps;
import net.dungeonrealms.DungeonRealms;
import net.dungeonrealms.beta.addon.ItemDropHandler;
import net.dungeonrealms.beta.creature.CreatureHandler;
import net.dungeonrealms.beta.creature.listenening.EntityListeningHandler;
import net.dungeonrealms.beta.creature.vendors.betavendor.BetaVendorClickHandler;
import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.item.ItemHandler;
import net.dungeonrealms.beta.player.commands.GMCommand;
import net.dungeonrealms.beta.prompt.PromptType;
import net.dungeonrealms.beta.rift.RiftHandler;
import net.dungeonrealms.beta.world.spawner.SpawnerHandler;
import net.dungeonrealms.common.game.commands.CommandManager;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class BetaHandler implements Handler.SuperHandler
{
    /**
     * TODO REWRITE REGIONS.
     */
    private static final Logger logger = Logger.getLogger(BetaHandler.class.getName());

    private static BetaHandler instance;

    private Map<String, Handler> handlers;
    private Map<String, ListeningHandler> listeningHandlers;
    private Map<String, TaskedHandler> taskedHandlers;

    private CreatureHandler creatureHandler;
    private RiftHandler riftHandler;
    private PlayerHandler playerHandler;
    private ItemHandler itemHandler;
    private SpawnerHandler spawnerHandler;

    private ItemRegistry itemRegistry;

    @Override
    public boolean onEnable()
    {

        instance = this;  //lmfao I forgot this.

        this.handlers = Maps.newHashMap();
        this.listeningHandlers = Maps.newHashMap();
        this.taskedHandlers = Maps.newHashMap();

        this.creatureHandler = new CreatureHandler();
        this.riftHandler = new RiftHandler();
        this.playerHandler = new PlayerHandler();
        this.itemHandler = new ItemHandler();
        this.spawnerHandler = new SpawnerHandler();
        this.itemRegistry = new ItemRegistry();

        handlers.put(creatureHandler.getClass().getName(), creatureHandler);
        handlers.put(playerHandler.getClass().getName(), playerHandler);
        handlers.put(itemRegistry.getClass().getName(), itemRegistry); //handler that caches stuff
        handlers.put(spawnerHandler.getClass().getName(), spawnerHandler);

        listeningHandlers.put(ItemDropHandler.class.getName(), new ItemDropHandler());
        listeningHandlers.put(EntityListeningHandler.class.getName(), new EntityListeningHandler());
        listeningHandlers.put(BetaVendorClickHandler.class.getName(), new BetaVendorClickHandler());

        taskedHandlers.put(riftHandler.getClass().getName(), riftHandler);

        for (Handler handler : handlers.values())
        {
            handler.getLogger().log(Level.INFO, " -> (BETA) > Enabling..");
            handler.onEnable();
        }

        for (ListeningHandler handler : listeningHandlers.values())
        {
            handler.getLogger().log(Level.INFO, " -> (BETA) > Enabling..");
            handler.onEnable();
        }

        for (TaskedHandler handler : taskedHandlers.values())
        {
            handler.getLogger().log(Level.INFO, " -> (BETA) > Enabling..");
            handler.onEnable();
        }
        CommandManager cm = new CommandManager();
        cm.registerCommand(new GMCommand("/gm", "/<command>", "GM Toggles", Arrays.asList("gamemaster", "gmtoggles", "gamemastertoggles")));
        return true;
    }

    @Override
    public boolean onDisable()
    {
        for (TaskedHandler handler : taskedHandlers.values())
        {
            handler.getLogger().log(Level.INFO, " -> (BETA) > Enabling..");
            handler.onImmediateStop();
        }
        return true;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }

    @Override
    public boolean vawke()
    {
        return true; //set this to true, this is the superhandler.
    }

    @Override
    public boolean supered()
    {
        return vawke();
    }

    public static BetaHandler getInstance()
    {
        return instance;
    }

    public static DungeonRealms getDungeonRealms()
    {
        return DungeonRealms.getInstance();
    }

    public Map<String, Handler> getHandlers()
    {
        return handlers;
    }

    public CreatureHandler getCreatureHandler()
    {
        return creatureHandler;
    }

    public PlayerHandler getPlayerHandler()
    {
        return playerHandler;
    }

    public RiftHandler getRiftHandler()
    {
        return riftHandler;
    }

    public SpawnerHandler getSpawnerHandler()
    {
        return spawnerHandler;
    }

    public ItemHandler getItemHandler()
    {
        return itemHandler;
    }

    public ItemRegistry getItemRegistry()
    {
        return itemRegistry;
    }

    public Map<String, TaskedHandler> getTaskedHandlers()
    {
        return taskedHandlers;
    }

    public void broadcast(PromptType promptType, String message)
    {
        Bukkit.broadcastMessage(promptType.getHeader());
        Bukkit.broadcastMessage(message);
    }
}