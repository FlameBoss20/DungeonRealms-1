package net.dungeonrealms.proxy.packet;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.dungeonrealms.proxy.DungeonBungee;
import net.dungeonrealms.proxy.handler.Handler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Giovanni on 11-8-2016.
 */
public class PacketHandler implements Handler
{
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    //security reasons
    private boolean buffered;

    @Override
    public boolean onEnable()
    {
        buffered = true;
        return false;
    }

    @Override
    public Logger getLogger()
    {
        return logger;
    }

    public void sendGlobalPacket(String task, String... contents)
    {
        if (buffered)
        {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF(task);

            for (String s : contents)
                out.writeUTF(s);

            DungeonBungee.getInstance().getMasterHandler().getGameClient().sendTCP(out.toByteArray());
        } else
        {
            logger.log(Level.WARNING, "Global packet not send, a buffer error occurred.");
            logger.log(Level.WARNING, "Buffering? BOOL: " + buffered);
        }
    }
}
