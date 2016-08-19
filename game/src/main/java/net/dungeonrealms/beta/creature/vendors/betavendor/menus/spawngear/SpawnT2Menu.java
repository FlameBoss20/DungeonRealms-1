package net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class SpawnT2Menu extends GUI {

    public SpawnT2Menu() {
        super(MenuConstants.BETA_VENDOR_SPAWN_T2_NAME, MenuConstants.BETA_VENDOR_SPAWN_T2_SLOTS);

        GUIStack helm = new GUIStack("&aT2 Helmet", Material.CHAINMAIL_HELMET, null);
        GUIStack chest = new GUIStack("&aT2 Chestplate", Material.CHAINMAIL_CHESTPLATE, null);
        GUIStack legs = new GUIStack("&aT2 Leggings", Material.CHAINMAIL_LEGGINGS, null);
        GUIStack boots = new GUIStack("&aT2 Boots", Material.CHAINMAIL_BOOTS, null);
        GUIStack sword = new GUIStack("&aT2 Sword", Material.STONE_SWORD, null);
        GUIStack axe = new GUIStack("&aT2 Axe", Material.STONE_AXE, null);
        GUIStack bow = new GUIStack("&aT2 Bow", Material.BOW, null);
        GUIStack staff = new GUIStack("&aT2 Staff", Material.STONE_HOE, null);
        GUIStack polearm = new GUIStack("&aT2 Polearm", Material.STONE_SPADE, null);

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
