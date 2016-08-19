package net.dungeonrealms.beta.player.menus;

import net.dungeonrealms.GameAPI;
import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.menu.GUI;
import net.dungeonrealms.beta.utilities.string.StringUtils;
import net.dungeonrealms.game.mastery.GamePlayer;
import net.dungeonrealms.game.miscellaneous.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew on 8/14/2016.
 */

public class GMToggleGUI extends GUI {

    public GMToggleGUI(Player player) {
        super(MenuConstants.GM_MENU_NAME, MenuConstants.GM_MENU_SLOTS);

        GamePlayer gp = GameAPI.getGamePlayer(player);
        if (gp == null) return;
        boolean toggleInvis = GameAPI._hiddenPlayers.contains(player);

        ItemBuilder invis = new ItemBuilder();
        invis.setItem(new ItemStack(Material.INK_SACK, 1, (short) (toggleInvis ? 10 : 8)));
        invis.setName(StringUtils.colorCodes((toggleInvis ? StringUtils.colorCodes("&a") : StringUtils.colorCodes("&c") + "GM Invisible")));
        invis.addLore(StringUtils.colorCodes("&7Toggling this will make you invisible to players and mobs."));
        invis.addLore(StringUtils.colorCodes("&7Display Item"));

        boolean toggleDMG = !gp.isInvulnerable() && gp.isTargettable();
        ItemBuilder dmg = new ItemBuilder();
        dmg.setItem(new ItemStack(Material.INK_SACK, 1, (short) (toggleDMG ? 10 : 8)));
        dmg.addLore(ChatColor.GRAY + "Toggling this will make you vulnerable");
        dmg.addLore(ChatColor.GRAY + "to attacks but also allow outgoing damage.");
        dmg.addLore(ChatColor.GRAY + "Display Item");
        dmg.setName((toggleDMG ? ChatColor.GREEN : ChatColor.RED) + "Allow Combat");

        boolean flight = ((!gp.isInvulnerable()) && (gp.isTargettable()));
        ItemBuilder fly = new ItemBuilder();
        fly.setItem(new ItemStack(Material.INK_SACK, 1, (short) (flight ? 10 : 8)));
        fly.addLore(ChatColor.GRAY + "Toggling this will put you in flight mode.");
        fly.addLore(ChatColor.GRAY + "Display Item");
        fly.setName((flight ? ChatColor.GREEN : ChatColor.RED) + "Flight");

        addItem(invis.build(), 0);
        addItem(dmg.build(), 1);
        addItem(fly.build(), 3);
    }

}
