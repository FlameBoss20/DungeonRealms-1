package net.dungeonrealms.beta.menu;

import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Giovanni on 14-8-2016.
 */
public class TestGUI extends GUI
{
    public TestGUI()
    {
        super(ChatColor.LIGHT_PURPLE + "Test", 9);

        HashMap<Integer, ItemStack> test = Maps.newHashMap();
        test.put(0, new GUIStack("&clel", Material.CHEST, 5, Arrays.asList("&dThis", "&ais", "&ca", "&ltest")).build());
        test.put(4, new GUIStack("&cmhm", Material.DIRT, 32, Arrays.asList("&dThis", "&ais", "&ka", "&otest")).build());

        this.addItems(test);
    }
}
