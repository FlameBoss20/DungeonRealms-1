package net.dungeonrealms.beta.item;

import net.dungeonrealms.beta.item.data.ItemRarity;
import net.dungeonrealms.beta.item.data.ItemTier;
import net.dungeonrealms.beta.item.data.ItemType;

import java.util.UUID;

/**
 * Created by Giovanni on 12-8-2016.
 */
public interface IGameItem
{
    ItemType getItemType();

    ItemTier getItemTier();

    ItemRarity getItemRarity();

    UUID getGameID();
}
