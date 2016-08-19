package net.dungeonrealms.beta.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Giovanni on 14-8-2016.
 */
public class GUIStack
{
    private ItemStack itemStack;

    public GUIStack(String name, Material material, List<String> lore)
    {
        this.itemStack = new ItemStack(material);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> fixedLore = new ArrayList<>();
        for (String string : lore)
        {
            String fixedString = ChatColor.translateAlternateColorCodes('&', string);
            fixedLore.add(fixedString);
        }
        itemMeta.setLore(fixedLore);
        this.itemStack.setItemMeta(itemMeta);
        lore.clear();
    }

    public GUIStack(String name, Material material, int amount, List<String> lore)
    {
        this.itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> fixedLore = new ArrayList<>();
        for (String string : lore)
        {
            String fixedString = ChatColor.translateAlternateColorCodes('&', string);
            fixedLore.add(fixedString);
        }
        itemMeta.setLore(fixedLore);
        this.itemStack.setItemMeta(itemMeta);
        lore.clear();
    }

    public GUIStack(String name, Material material, byte _byte, List<String> lore)
    {
        this.itemStack = new ItemStack(material, 0, _byte);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> fixedLore = new ArrayList<>();
        for (String string : lore)
        {
            String fixedString = ChatColor.translateAlternateColorCodes('&', string);
            fixedLore.add(fixedString);
        }
        itemMeta.setLore(fixedLore);
        this.itemStack.setItemMeta(itemMeta);
        lore.clear();
    }

    public GUIStack(String name, Material material, int amount, byte _byte, List<String> lore)
    {
        this.itemStack = new ItemStack(material, amount, _byte);
        ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> fixedLore = new ArrayList<>();
        for (String string : lore)
        {
            String fixedString = ChatColor.translateAlternateColorCodes('&', string);
            fixedLore.add(fixedString);
        }
        itemMeta.setLore(fixedLore);
        this.itemStack.setItemMeta(itemMeta);
        lore.clear();
    }

    public ItemStack build()
    {
        return itemStack;
    }
}
