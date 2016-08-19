package net.dungeonrealms.beta.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Created by Giovanni on 14-8-2016.
 */
public class GUI implements IGUI
{
    private Inventory inventory;

    public GUI(String name, int slots)
    {
        this.inventory = Bukkit.createInventory(null, slots, name);
    }

    public GUI(InventoryHolder inventoryHolder, String name, int slots)
    {
        this.inventory = Bukkit.createInventory(inventoryHolder, slots, name);
    }

    public void addItem(ItemStack itemStack, int slot)
    {
        this.inventory.setItem(slot, itemStack);
    }

    public void addItems(Map<Integer, ItemStack> itemStacks)
    {
        for (int i : itemStacks.keySet())
        {
            this.inventory.setItem(i, itemStacks.get(i));
        }
    }

    @Override
    public Inventory openInventory()
    {
        return inventory;
    }
}
