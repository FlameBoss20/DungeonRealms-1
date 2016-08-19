package net.dungeonrealms.beta.rift.creature;

import net.dungeonrealms.beta.collection.WeightedCollection;
import org.bukkit.ChatColor;
import org.bukkit.Effect;

/**
 * Created by Giovanni on 12-8-2016.
 */
public enum RiftCreatureElement
{
    EMPTY(ChatColor.RED, "", null), HOLY(ChatColor.GOLD, "Holy", Effect.WITCH_MAGIC), UNIDENTIFIED(ChatColor.MAGIC, "?????", Effect.VILLAGER_THUNDERCLOUD),
    POISON(ChatColor.DARK_GREEN, "Poisonous", Effect.INSTANT_SPELL), ICE(ChatColor.AQUA, "Frozen", Effect.SNOWBALL_BREAK),
    FIRE(ChatColor.YELLOW, "Furious", Effect.MOBSPAWNER_FLAMES);

    private static WeightedCollection<RiftCreatureElement> weightedCollection = new WeightedCollection<RiftCreatureElement>();
    private static boolean buffered = false;

    private ChatColor chatColor;
    private String prefix;
    private Effect effects;

    RiftCreatureElement(ChatColor chatColor, String prefix, Effect effects)
    {
        this.chatColor = chatColor;
        this.prefix = prefix;
        this.effects = effects;
    }

    public ChatColor getChatColor()
    {
        return chatColor;
    }

    public String getPrefix()
    {
        return prefix;
    }

    public Effect getEffect()
    {
        return effects;
    }

    public static RiftCreatureElement random()
    {
        if(buffered)
        {
            return weightedCollection.next();
        } else
        {
            generate();
            return weightedCollection.next();
        }
    }

    private static void generate()
    {
        weightedCollection.add(0.7, EMPTY);
        weightedCollection.add(0.2, HOLY);
        weightedCollection.add(0.2, POISON);
        weightedCollection.add(0.2, ICE);
        weightedCollection.add(0.2, FIRE);
        weightedCollection.add(0.1, UNIDENTIFIED);
        buffered = true;
    }
}
