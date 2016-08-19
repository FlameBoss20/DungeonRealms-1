package net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class SpawnT1Menu extends GUI {

    public SpawnT1Menu() {
        super(MenuConstants.BETA_VENDOR_SPAWN_T1_NAME, MenuConstants.BETA_VENDOR_SPAWN_T1_SLOTS);

        GUIStack helm = new GUIStack("&fT1 Helmet", Material.LEATHER_HELMET, null);
        GUIStack chest = new GUIStack("&fT1 Chestplate", Material.LEATHER_CHESTPLATE, null);
        GUIStack legs = new GUIStack("&fT1 Leggings", Material.LEATHER_LEGGINGS, null);
        GUIStack boots = new GUIStack("&fT1 Boots", Material.LEATHER_BOOTS, null);
        GUIStack sword = new GUIStack("&fT1 Sword", Material.WOOD_SWORD, null);
        GUIStack axe = new GUIStack("&fT1 Axe", Material.WOOD_AXE, null);
        GUIStack bow = new GUIStack("&fT1 Bow", Material.BOW, null);
        GUIStack staff = new GUIStack("&fT1 Staff", Material.WOOD_HOE, null);
        GUIStack polearm = new GUIStack("&fT1 Polearm", Material.WOOD_SPADE, null);

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
