package net.dungeonrealms.beta.handler;

import org.bukkit.event.Listener;

import java.util.logging.Logger;

/**
 * Created by Giovanni on 12-8-2016.
 */
public interface Handler
{
    boolean supered();

    boolean onEnable();

    Logger getLogger();

    interface SuperHandler extends Handler
    {
        boolean onDisable();

        boolean vawke();
    }

    interface ListeningHandler extends Listener, Handler
    {
    }

    interface TaskedHandler extends Runnable, Handler
    {
        void onImmediateStop();
    }
}
