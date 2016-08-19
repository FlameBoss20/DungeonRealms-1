package net.dungeonrealms.beta.player;

import net.dungeonrealms.beta.prompt.VStringBuilder;
import net.dungeonrealms.beta.world.WorldConstants;
import net.dungeonrealms.common.Constants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Giovanni on 15-8-2016.
 */
public class GamePlayer implements IProxiedPlayer
{
    //proxied stuff
    private UUID uuid;
    private int gems;
    private int level;
    private int exp;

    //default
    private PlayerGroup playerGroup;
    private boolean finishedTutorial;

    private int health;
    private int maxHealth;

    //world
    private Location location;

    //existant database player
    public GamePlayer(UUID uuid, PlayerGroup playerGroup, Location location)
    {
        this.uuid = uuid;
        this.playerGroup = playerGroup;
        this.location = location;
    }

    //brand new player
    public GamePlayer(UUID uuid)
    {
        this(uuid, PlayerGroup.DEFAULT, WorldConstants.CYERNNICA_MAIN_SPAWN);
        this.gems = 0;
        this.maxHealth = 50;
        this.health = 50;
        this.level = 1;
        this.exp = 0;
        this.finishedTutorial = false;

    }

    @Override
    public UUID getUniqueId()
    {
        return uuid;
    }

    @Override
    public int getGems()
    {
        return gems;
    }

    /**
     * TODO remove/set/add gems
     */

    @Override
    public int getHealth()
    {
        return health;
    }

    public void setHealth(int par1)
    {
        this.health = par1;
    }

    public void addHealth(int par1)
    {
        this.health += par1;
    }

    public void removeHealth(int par1)
    {
        this.health -= par1;
    }

    @Override
    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(int par1)
    {
        this.maxHealth = par1;
    }

    public void addMaxHealth(int par1)
    {
        this.maxHealth += par1;
    }

    public void removeMaxHealth(int par1)
    {
        this.maxHealth -= par1;
    }

    @Override
    public int getExp()
    {
        return exp;
    }

    public void setExp(int par1)
    {
        this.exp = par1;
    }

    public void addExp(int par1)
    {
        this.exp += par1;
    }

    public void removeExp(int par1)
    {
        this.exp -= par1;
    }

    @Override
    public int getLevel()
    {
        return level;
    }

    public void setLevel(int par1)
    {
        this.level = par1;
    }

    public void addLevels(int par1)
    {
        this.level += par1;
    }

    public void removeLevels(int par1)
    {
        this.level -= par1;
    }

    public PlayerGroup getPlayerGroup()
    {
        return playerGroup;
    }

    public Player asBukkitPlayer()
    {
        return Bukkit.getPlayer(getUniqueId());
    }

    public Location getBukkitLocation()
    {
        return asBukkitPlayer().getLocation();
    }

    public Location spawn()
    {
        return location;
    }

    public boolean finishedTutorial()
    {
        return finishedTutorial;
    }

    public void setTutorialFinished(boolean par1)
    {
        this.finishedTutorial = par1;
    }

    @Override
    public void updateHealth()
    {
        this.asBukkitPlayer().setMaxHealth(maxHealth);
        this.asBukkitPlayer().setHealthScale(20);
        if(health > maxHealth)
        {
            this.health = maxHealth;
            this.asBukkitPlayer().setHealth(health);
        } else
        {
            this.asBukkitPlayer().setHealth(health);
        }
    }

    public void promptWelcome()
    {
        asBukkitPlayer().playSound(getBukkitLocation(), Sound.ENTITY_WITHER_SHOOT, 1f, 1f);
        if(finishedTutorial)
        {
            asBukkitPlayer().sendMessage(VStringBuilder.build("&6&lDUNGEON REALMS", true));
            asBukkitPlayer().sendMessage(VStringBuilder.build("&cRunning patch BETA " + Constants.BUILD_VERSION, true));
            asBukkitPlayer().sendMessage("");
            asBukkitPlayer().sendMessage(VStringBuilder.build("&nwww.dungeonrealms.org", true));
        } else
        {
            asBukkitPlayer().sendMessage(VStringBuilder.build("&6&lDUNGEON REALMS", true));
            asBukkitPlayer().sendMessage(VStringBuilder.build("&cRunning patch BETA " + Constants.BUILD_VERSION, true));
            asBukkitPlayer().sendMessage("");
            asBukkitPlayer().sendMessage(VStringBuilder.build("&e** Tutorial Island **", true));
            asBukkitPlayer().sendMessage("");
            asBukkitPlayer().sendMessage(VStringBuilder.build("&nwww.dungeonrealms.org", true));
        }
    }
}
