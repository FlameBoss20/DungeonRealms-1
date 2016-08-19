package net.dungeonrealms.common.game.motd;

import java.io.IOException;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class MOTDManager
{
    public static String asCentered(String text)
    {
        StringBuilder builder = new StringBuilder(text);
        char space = ' ';
        int distance = (45 - text.length()) / 2;
        for (int i = 0; i < distance; ++i)
        {
            builder.insert(0, space);
            builder.append(space);
        }
        return builder.toString();
    }

    private boolean tryResponse() throws IOException
    {
        return false;
    }
}
