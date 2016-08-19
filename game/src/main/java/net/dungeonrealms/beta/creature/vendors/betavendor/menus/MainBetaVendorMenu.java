package net.dungeonrealms.beta.creature.vendors.betavendor.menus;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class MainBetaVendorMenu extends GUI {


    public MainBetaVendorMenu() {
        super(MenuConstants.BETA_VENDOR_MAIN_NAME, MenuConstants.BETA_VENDOR_MAIN_SLOTS);

        GUIStack spawnGear = new GUIStack("&aSpawn Gear", Material.DIAMOND_CHESTPLATE, null);
        GUIStack spawnScrolls = new GUIStack("&aSpawn Scrolls", Material.EMPTY_MAP, null);
        GUIStack spawnFood = new GUIStack("&aSpawn Food", Material.GOLDEN_CARROT, null);
        GUIStack spawnScrap = new GUIStack("&aSpawn Scrap", Material.IRON_FENCE, null);
        GUIStack spawnPotions = new GUIStack("&aSpawn Potions", Material.POTION, null);
        GUIStack lvl = new GUIStack("&aSet Level", Material.EXP_BOTTLE, null);
        GUIStack gems = new GUIStack("&aCreate Gems", Material.EMERALD, null);
        GUIStack profession = new GUIStack("&aProfessions", Material.IRON_PICKAXE, null);
        GUIStack tps = new GUIStack("&aTP Books", Material.BOOK, null);
        GUIStack exit = new GUIStack("&cExit", Material.BARRIER, null);

        addItem(spawnGear.build(), 0);
        addItem(spawnScrolls.build(), 1);
        addItem(spawnFood.build(), 2);
        addItem(spawnScrap.build(), 3);
        addItem(spawnPotions.build(), 4);
        addItem(lvl.build(), 5);
        addItem(gems.build(), 6);
        addItem(profession.build(), 7);
        addItem(tps.build(), 8);

        addItem(exit.build(), 17);
    }
}
