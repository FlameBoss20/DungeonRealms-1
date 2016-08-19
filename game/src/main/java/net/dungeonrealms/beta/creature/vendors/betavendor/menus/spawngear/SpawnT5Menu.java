package net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class SpawnT5Menu extends GUI {

    public SpawnT5Menu() {
        super(MenuConstants.BETA_VENDOR_SPAWN_T5_NAME, MenuConstants.BETA_VENDOR_SPAWN_T5_SLOTS);

        GUIStack helm = new GUIStack("&eT5 Helmet", Material.GOLD_HELMET, null);
        GUIStack chest = new GUIStack("&eT5 Chestplate", Material.GOLD_CHESTPLATE, null);
        GUIStack legs = new GUIStack("&eT5 Leggings", Material.GOLD_LEGGINGS, null);
        GUIStack boots = new GUIStack("&eT5 Boots", Material.GOLD_BOOTS, null);
        GUIStack sword = new GUIStack("&eT5 Sword", Material.GOLD_SWORD, null);
        GUIStack axe = new GUIStack("&eT5 Axe", Material.GOLD_AXE, null);
        GUIStack bow = new GUIStack("&eT5 Bow", Material.BOW, null);
        GUIStack staff = new GUIStack("&eT5 Staff", Material.GOLD_HOE, null);
        GUIStack polearm = new GUIStack("&eT5 Polearm", Material.GOLD_SPADE, null);

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
