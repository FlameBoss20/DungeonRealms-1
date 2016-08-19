package net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class SpawnT4Menu extends GUI {

    public SpawnT4Menu() {
        super(MenuConstants.BETA_VENDOR_SPAWN_T4_NAME, MenuConstants.BETA_VENDOR_SPAWN_T4_SLOTS);

        GUIStack helm = new GUIStack("&dT4 Helmet", Material.DIAMOND_HELMET, null);
        GUIStack chest = new GUIStack("&dT4 Chestplate", Material.DIAMOND_CHESTPLATE, null);
        GUIStack legs = new GUIStack("&dT4 Leggings", Material.DIAMOND_LEGGINGS, null);
        GUIStack boots = new GUIStack("&dT4 Boots", Material.DIAMOND_BOOTS, null);
        GUIStack sword = new GUIStack("&dT4 Sword", Material.DIAMOND_SWORD, null);
        GUIStack axe = new GUIStack("&dT4 Axe", Material.DIAMOND_AXE, null);
        GUIStack bow = new GUIStack("&dT4 Bow", Material.BOW, null);
        GUIStack staff = new GUIStack("&dT4 Staff", Material.DIAMOND_HOE, null);
        GUIStack polearm = new GUIStack("&dT4 Polearm", Material.DIAMOND_SPADE, null);

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
