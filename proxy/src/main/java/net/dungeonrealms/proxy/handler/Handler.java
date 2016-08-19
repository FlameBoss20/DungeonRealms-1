package net.dungeonrealms.proxy.handler;

import java.util.logging.Logger;

/**
 * Created by Giovanni on 11-8-2016.
 */
public interface Handler
{
    boolean onEnable();

    Logger getLogger();
}
