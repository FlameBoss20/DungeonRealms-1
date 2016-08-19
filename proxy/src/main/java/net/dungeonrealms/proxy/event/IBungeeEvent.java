package net.dungeonrealms.proxy.event;

import java.util.logging.Level;

/**
 * Created by Giovanni on 10-8-2016.
 */
interface IBungeeEvent
{
    void setLogLevel(Level level);

    void setLogMessage(String arg1);
}
