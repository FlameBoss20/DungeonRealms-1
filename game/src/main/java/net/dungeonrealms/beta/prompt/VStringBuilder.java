package net.dungeonrealms.beta.prompt;

import net.dungeonrealms.beta.prompt.enumeration.DefaultFontInfo;
import org.bukkit.ChatColor;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class VStringBuilder
{
    public static String build(String message, boolean centered)
    {
        if (centered)
        {
            return getCenteredMessage(message);
        } else
        {
            return ChatColor.translateAlternateColorCodes('&', message);
        }
    }

    /**
     * @apiNote https://www.spigotmc.org/threads/free-code-sending-perfectly-centered-chat-message.95872/
     */
    private static String getCenteredMessage(String par)
    {
        if (par == null || par.equals("")) return "";
        par = ChatColor.translateAlternateColorCodes('&', par);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : par.toCharArray())
        {
            if (c == '§')
            {
                previousCode = true;
            } else if (previousCode)
            {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else
            {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = DefaultFontInfo.getCenterPX() - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate)
        {
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb.toString() + par;
    }
}
