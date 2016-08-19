package net.dungeonrealms.beta.item;

import net.dungeonrealms.beta.handler.Handler;

import java.util.logging.Logger;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class ItemHandler implements Handler
{
    private static Logger logger = Logger.getLogger(ItemHandler.class.getName());

    @Override
    public boolean onEnable()
    {
        return false;
    }

    @Override
    public boolean supered()
    {
        return false;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }
}
