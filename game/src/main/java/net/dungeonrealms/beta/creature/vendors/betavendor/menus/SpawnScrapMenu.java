package net.dungeonrealms.beta.creature.vendors.betavendor.menus;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

/**
 * Created by Matthew on 8/14/2016.
 */

public class SpawnScrapMenu extends GUI {

    public SpawnScrapMenu() {
        super(MenuConstants.BETA_VENDOR_SPAWN_SCRAP_NAME, MenuConstants.BETA_VENDOR_SPAWN_SCRAP_SLOTS);
        GUIStack t1 = new GUIStack("&fTier 1", Material.LEATHER, null);
        GUIStack t2 = new GUIStack("&aTier 2", Material.IRON_FENCE, null);
        GUIStack t3 = new GUIStack("&bTier 3", Material.INK_SACK, 7, null);
        GUIStack t4 = new GUIStack("&dTier 4", Material.INK_SACK, 12, null);
        GUIStack t5 = new GUIStack("&eTier 5", Material.INK_SACK, 11, null);
        GUIStack back = new GUIStack("&cBack", Material.BARRIER, null);

        addItem(t1.build(), 0);
        addItem(t2.build(), 1);
        addItem(t3.build(), 2);
        addItem(t4.build(), 3);
        addItem(t5.build(), 4);
        addItem(back.build(), 8);
    }
}
