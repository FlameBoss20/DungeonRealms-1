package net.dungeonrealms.beta.item.type;

import net.dungeonrealms.beta.utilities.math.IntegerUtil;
import net.dungeonrealms.beta.item.IGameItem;
import net.dungeonrealms.beta.item.attributes.AttributeType;
import net.dungeonrealms.beta.item.attributes.ElementalAttribute;
import net.dungeonrealms.beta.item.data.ItemRarity;
import net.dungeonrealms.beta.item.data.ItemTier;
import net.dungeonrealms.beta.item.data.ItemType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class ItemWeapon implements IGameItem
{
    private ItemRarity itemRarity;
    private ItemTier itemTier;
    private ItemType itemType;

    private AttributeType attributeType;
    private ElementalAttribute elementalAttribute;
    private int attributeCount;

    private int minDmg;
    private int maxDmg;

    private ItemStack itemStack;
    private Material material;

    private UUID gameId;

    /**
     * Random name based upon attributes, type, etc.
     */
    private String name;
    private List<String> defaultTags = Arrays.asList("", "Deadly", "Vampyric", "Magical", "Magic", "Ancient", "Great");

    //new, random weapon
    public ItemWeapon(ItemType itemType)
    {
        this.itemRarity = ItemRarity.random();
        this.itemTier = ItemTier.random();
        this.attributeCount = IntegerUtil.randomInteger(0, 4);
        this.itemType = itemType;
        this.gameId = UUID.randomUUID();
        this.material = this.itemTier.getMaterialByType(itemType);

        this.minDmg = IntegerUtil.randomInteger(this.itemTier.getMinDmg(), this.itemRarity.getMinDmg());
        this.maxDmg = IntegerUtil.randomInteger(this.itemRarity.getMaxDmg(), this.itemTier.getMaxDmg());

        this.itemStack = new ItemStack(material);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(ChatColor.RED + "DMG: " + minDmg + " - " + maxDmg));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (elementalAttribute != ElementalAttribute.EMPTY)
        {
            if (attributeCount > 1)
            {
                itemMeta.setDisplayName(itemTier.getColor() + defaultTags.get(new Random().nextInt(defaultTags.size())) + " "
                        + itemType.getIdentifier() + " Of " + elementalAttribute.getIdentifier() + " and ");
            }
        } else
        {
            itemMeta.setDisplayName(itemTier.getColor() + defaultTags.get(new Random().nextInt(defaultTags.size())) + " "
                    + itemType.getIdentifier());
        }

        this.itemStack.setItemMeta(itemMeta);
    }

    //custom generation
    public ItemWeapon(String name, Material material, int maxDmg, int minDmg, ItemType itemType, ItemTier itemTier, ItemRarity itemRarity, int attributeCount)
    {
        this.gameId = UUID.randomUUID();
        this.material = material;
        this.maxDmg = maxDmg;
        this.minDmg = minDmg;
        this.itemType = itemType;
        this.itemTier = itemTier;
        this.itemRarity = itemRarity;
        this.attributeCount = attributeCount;

        this.itemStack = new ItemStack(material);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(ChatColor.RED + "DMG: " + minDmg + " - " + maxDmg));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (elementalAttribute != ElementalAttribute.EMPTY)
        {
            if (attributeCount > 1)
            {
                itemMeta.setDisplayName(itemTier.getColor() + defaultTags.get(new Random().nextInt(defaultTags.size())) + " "
                        + itemType.getIdentifier() + " Of " + elementalAttribute.getIdentifier() + " and ");
            }
        } else
        {
            itemMeta.setDisplayName(itemTier.getColor() + defaultTags.get(new Random().nextInt(defaultTags.size())) + " "
                    + itemType.getIdentifier());
        }

        this.itemStack.setItemMeta(itemMeta);
    }

    //out of database
    public ItemWeapon(UUID gameId, String name, Material material, int maxDmg, int minDmg, ItemType itemType, ItemTier itemTier,
                      ItemRarity itemRarity, int attributeCount, ElementalAttribute... elementalAttributes)
    {
        this.gameId = gameId;
        this.material = material;
        this.maxDmg = maxDmg;
        this.minDmg = minDmg;
        this.itemType = itemType;
        this.itemTier = itemTier;
        this.itemRarity = itemRarity;
        this.attributeCount = attributeCount;

        this.itemStack = new ItemStack(material);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(ChatColor.RED + "DMG: " + minDmg + " - " + maxDmg));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        if (elementalAttribute != ElementalAttribute.EMPTY)
        {
            if (attributeCount > 1)
            {
                itemMeta.setDisplayName(itemTier.getColor() + defaultTags.get(new Random().nextInt(defaultTags.size())) + " "
                        + itemType.getIdentifier() + " Of " + elementalAttribute.getIdentifier() + " and ");
            }
        } else
        {
            itemMeta.setDisplayName(itemTier.getColor() + defaultTags.get(new Random().nextInt(defaultTags.size())) + " "
                    + itemType.getIdentifier());
        }

        this.itemStack.setItemMeta(itemMeta);
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    public ElementalAttribute getElementalAttribute()
    {
        return elementalAttribute;
    }

    public int getMinDmg()
    {
        return minDmg;
    }

    public int getMaxDmg()
    {
        return maxDmg;
    }

    @Override
    public ItemRarity getItemRarity()
    {
        return itemRarity;
    }

    @Override
    public ItemTier getItemTier()
    {
        return itemTier;
    }

    @Override
    public ItemType getItemType()
    {
        return itemType;
    }

    @Override
    public UUID getGameID()
    {
        return gameId;
    }
}
