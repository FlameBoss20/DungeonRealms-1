package net.dungeonrealms.beta.creature.vendors.betavendor.menus;

import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.menu.GUIStack;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matthew on 8/14/2016.
 */

public class SpawnTeleportMenu extends GUI {

    public SpawnTeleportMenu() {
        super(MenuConstants.BETA_VENDOR_SPAWN_TELEPORT_NAME, MenuConstants.BETA_VENDOR_SPAWN_TELEPORT_SLOTS);

        List<String> teleports = new ArrayList<String>(Arrays.asList("Cyrennica", "Harrison_Field", "Dark_Oak", "Trollsbane", "Tripoli", "Gloomy_Hollows", "Crestguard", "Deadpeaks"));
        List<GUIStack> tps = new ArrayList<GUIStack>();
        for (String tp : teleports) {
            tps.add(new GUIStack(("&f" + tp.replaceAll("_", " ")), Material.BOOK, null));
        }
        addItem(tps.get(0).build(), 0);
        addItem(tps.get(1).build(), 1);
        addItem(tps.get(2).build(), 2);
        addItem(tps.get(3).build(), 3);
        addItem(tps.get(4).build(), 4);
        addItem(tps.get(5).build(), 5);
        addItem(tps.get(6).build(), 6);
        addItem(tps.get(7).build(), 7);
        addItem(tps.get(8).build(), 8);


    }
}
