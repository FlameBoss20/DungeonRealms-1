package net.dungeonrealms.beta.creature.type;

import com.mojang.authlib.GameProfile;
import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.creature.CreatureType;
import net.dungeonrealms.beta.creature.NPCType;
import net.dungeonrealms.beta.creature.type.data.INPC;
import net.minecraft.server.v1_9_R2.EntityPlayer;
import net.minecraft.server.v1_9_R2.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_9_R2.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_9_R2.PlayerInteractManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftServer;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;

import java.util.UUID;
import java.util.logging.Level;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class CreatureNPC implements INPC
{
    private NPCType npcType;

    private String name;
    private UUID uniqueId;
    private Location location;

    private UUID storageId;

    private EntityPlayer npcCreature;

    public CreatureNPC(NPCType npcType, String name, UUID uniqueId, Location location, boolean newNPC)
    {
        this.npcType = npcType;
        this.name = name;
        this.uniqueId = uniqueId;
        this.location = location;
        if (newNPC)
        {
            this.storageId = UUID.randomUUID();

            this.npcCreature = new EntityPlayer(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) location.getWorld()).getHandle(),
                    new GameProfile(uniqueId, name), new PlayerInteractManager(((CraftWorld) location.getWorld()).getHandle()));

            BetaHandler.getInstance().getCreatureHandler().getNPCConfiguration().set("data." + storageId + ".name", name);
            BetaHandler.getInstance().getCreatureHandler().getNPCConfiguration().set("data." + storageId + ".profile", uniqueId);
            BetaHandler.getInstance().getCreatureHandler().getNPCConfiguration().set("data." + storageId + ".type", npcType.name());
            BetaHandler.getInstance().getCreatureHandler().getNPCConfiguration().set("data." + storageId + ".world", location.getWorld());
            BetaHandler.getInstance().getCreatureHandler().getNPCConfiguration().set("data." + storageId + ".x", location.getX());
            BetaHandler.getInstance().getCreatureHandler().getNPCConfiguration().set("data." + storageId + ".y", location.getY());
            BetaHandler.getInstance().getCreatureHandler().getNPCConfiguration().set("data." + storageId + ".z", location.getZ());

            BetaHandler.getInstance().getLogger().log(Level.INFO, "A new NPC has been registered.");
            BetaHandler.getInstance().getLogger().log(Level.INFO, "Profile: " + uniqueId.toString());
            BetaHandler.getInstance().getLogger().log(Level.INFO, "StorageID: " + storageId);
        } else
        {
            BetaHandler.getInstance().getLogger().log(Level.INFO, "An existant NPC has been registered.");
            BetaHandler.getInstance().getLogger().log(Level.INFO, "Profile: " + uniqueId.toString());
        }
    }

    @Override
    public boolean isEquippable()
    {
        return false;
    }

    @Override
    public CreatureType getCreatureType()
    {
        return CreatureType.NPC;
    }

    @Override
    public NPCType getNPCType()
    {
        return npcType;
    }

    @Override
    public void spawn(Location location)
    {
        npcCreature.setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
        Bukkit.getOnlinePlayers().stream().filter(player -> player.getWorld() != null).forEach(player -> {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npcCreature));
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(npcCreature));
        });
    }

    public UUID getUniqueId()
    {
        return uniqueId;
    }
}
