package net.dungeonrealms.beta.item;

import net.dungeonrealms.beta.item.data.ItemType;
import net.dungeonrealms.beta.item.type.ItemWeapon;

/**
 * Created by Giovanni on 14-8-2016.
 */
public class ItemBuilder
{
    public ItemWeapon buildWeapon(ItemType itemType, String name, int minDmg, int maxDmg)
    {
        return new ItemWeapon(itemType);
    }
}
