package net.dungeonrealms.beta;

import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.item.type.ItemWeapon;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 16-8-2016.
 */
public class ItemRegistry implements Handler
{
    /**
     * This registry prevents items from being duped.
     *
     * Upon enable, get stored database items and put them in the map they belong to.
     * Upon disable, store all items into the database.
     *
     * Can easily hold up to 40k items, tested on my computer using MySQL.
     */
    private static final Logger logger = Logger.getLogger(ItemRegistry.class.getName());

    private static ConcurrentHashMap<ItemStack, ItemWeapon> weaponRegistry;

    @Override
    public boolean onEnable()
    {
        weaponRegistry = new ConcurrentHashMap<>();
        return false;
    }

    @Override
    public boolean supered()
    {
        return false;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }

    public static ConcurrentHashMap<ItemStack, ItemWeapon> getWeaponRegistry()
    {
        return weaponRegistry;
    }
}
