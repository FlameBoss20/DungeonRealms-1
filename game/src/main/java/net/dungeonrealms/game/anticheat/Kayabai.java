package net.dungeonrealms.game.anticheat;

import me.konsolas.aac.api.HackType;
import me.konsolas.aac.api.PlayerViolationEvent;
import net.dungeonrealms.DungeonRealms;
import net.dungeonrealms.game.mechanic.generic.EnumPriority;
import net.dungeonrealms.game.mechanic.generic.GenericMechanic;
import net.dungeonrealms.game.world.entity.util.EntityAPI;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Horse;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Kayaba on 8/12/2016.
 */
public class Kayabai implements GenericMechanic, Listener {

    static Kayabai instance = null;

    public static Kayabai getInstance() {
        if (instance == null) {
            instance = new Kayabai();
        }
        return instance;
    }

    @Override
    public EnumPriority startPriority() {
        return EnumPriority.CATHOLICS;
    }

    @Override
    public void startInitialization() {
        Bukkit.getServer().getPluginManager().registerEvents(this, DungeonRealms.getInstance());
    }

    @Override
    public void stopInvocation() {

    }

    @EventHandler
    public void onPlayerViolation(PlayerViolationEvent e) {

        // TEMPORARY DEBUGS

        System.out.println("== START OF VIOLATION ==");
        System.out.println("Hack Type: " + e.getHackType());
        System.out.println("Player: "+ e.getPlayer().getName());
        System.out.println("Message: "+ e.getMessage());
        System.out.println("Violations in the last minute" + e.getViolations());

        if(e.getHackType() == HackType.SPEED || e.getHackType() == HackType.NORMALMOVEMENTS)
        {
            if(EntityAPI.hasMountOut(e.getPlayer().getUniqueId()))
            {
                Horse horse = (Horse)EntityAPI.getPlayerMount(e.getPlayer().getUniqueId()).getBukkitEntity();
                AttributeInstance speedAttribute = horse.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                double speed = speedAttribute.getBaseValue();
                System.out.println("Expected player speed: " + speed);
                e.setCancelled(true); // Ignore speed hackers for now only if on mount.
            }
        }
        if(e.getHackType() == HackType.GLIDE)
        {
            if(EntityAPI.hasMountOut(e.getPlayer().getUniqueId()))
            {
                e.setCancelled(true);
            }
        }
        System.out.println("== END OF VIOLATION ==");

    }


}
