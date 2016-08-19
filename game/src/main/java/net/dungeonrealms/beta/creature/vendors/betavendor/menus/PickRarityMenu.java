package net.dungeonrealms.beta.creature.vendors.betavendor.menus;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.item.data.ItemRarity;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class PickRarityMenu extends GUI {

    public PickRarityMenu() {
        super(MenuConstants.BETA_VENDOR_RARITY_NAME, MenuConstants.BETA_VENDOR_RARITY_SLOTS);

        GUIStack common = new GUIStack("&f" + ItemRarity.COMMON.name(), Material.COAL, null);
        GUIStack uncommon = new GUIStack("&a" + ItemRarity.UNCOMMON.name(), Material.EMERALD, null);
        GUIStack rare = new GUIStack("&b" + ItemRarity.RARE.name(), Material.DIAMOND, null);
        GUIStack unique = new GUIStack("&e" + ItemRarity.UNIQUE.name(), Material.GOLD_INGOT, null);
        GUIStack back = new GUIStack("&cBack", Material.BARRIER, null);

        addItem(common.build(), 0);
        addItem(uncommon.build(), 1);
        addItem(rare.build(), 2);
        addItem(unique.build(), 3);
        addItem(back.build(), 8);
    }
}
