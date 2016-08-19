package net.dungeonrealms.beta.prompt;

import org.bukkit.ChatColor;

/**
 * Created by Giovanni on 12-8-2016.
 */
public enum PromptType
{
    RIFT(VStringBuilder.build("&d&lRIFT OPENED", true));

    private String header;

    PromptType(String header)
    {
        this.header = header;
    }

    public String getHeader()
    {
        return header;
    }
}
