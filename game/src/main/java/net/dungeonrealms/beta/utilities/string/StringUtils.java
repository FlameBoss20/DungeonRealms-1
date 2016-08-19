package net.dungeonrealms.beta.utilities.string;

import org.bukkit.ChatColor;

/**
 * Created by Matthew on 8/14/2016.
 */

public class StringUtils {

    public static String colorCodes(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ChatColor getTierColor(int tier) {
        switch (tier) {
            case 1:
                return ChatColor.WHITE;
            case 2:
                return ChatColor.GREEN;
            case 3:
                return ChatColor.AQUA;
            case 4:
                return ChatColor.LIGHT_PURPLE;
            case 5:
                return ChatColor.YELLOW;
            default:
                return ChatColor.WHITE;
        }
    }
}
