package net.dungeonrealms.proxy;

import net.dungeonrealms.proxy.handler.Handler;

import java.util.logging.Logger;

/**
 * Created by Giovanni on 11-8-2016.
 */
public class PlayerHandler implements Handler
{
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public boolean onEnable()
    {
        return false;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }
}
