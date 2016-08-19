package net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class SpawnT3Menu extends GUI {

    public SpawnT3Menu() {
        super(MenuConstants.BETA_VENDOR_SPAWN_T3_NAME, MenuConstants.BETA_VENDOR_SPAWN_T3_SLOTS);

        GUIStack helm = new GUIStack("&bT3 Helmet", Material.IRON_HELMET, null);
        GUIStack chest = new GUIStack("&bT3 Chestplate", Material.IRON_CHESTPLATE, null);
        GUIStack legs = new GUIStack("&bT3 Leggings", Material.IRON_LEGGINGS, null);
        GUIStack boots = new GUIStack("&bT3 Boots", Material.IRON_BOOTS, null);
        GUIStack sword = new GUIStack("&bT3 Sword", Material.IRON_SWORD, null);
        GUIStack axe = new GUIStack("&bT3 Axe", Material.IRON_AXE, null);
        GUIStack bow = new GUIStack("&bT3 Bow", Material.BOW, null);
        GUIStack staff = new GUIStack("&bT3 Staff", Material.IRON_HOE, null);
        GUIStack polearm = new GUIStack("&bT3 Polearm", Material.IRON_SPADE, null);

        addItem(helm.build(), 0);
        addItem(chest.build(), 1);
        addItem(legs.build(), 2);
        addItem(boots.build(), 3);
        addItem(sword.build(), 4);
        addItem(axe.build(), 5);
        addItem(bow.build(), 6);
        addItem(staff.build(), 7);
        addItem(polearm.build(), 8);
    }
}
