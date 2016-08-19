package net.dungeonrealms.beta.creature.type.element;

import net.dungeonrealms.beta.collection.WeightedCollection;
import org.bukkit.ChatColor;
import org.bukkit.Effect;

/**
 * Created by Giovanni on 16-8-2016.
 */
public enum CreatureElement
{
    EMPTY(ChatColor.RED, "", null), HOLY(ChatColor.GOLD, "Holy", Effect.WITCH_MAGIC),
    POISON(ChatColor.DARK_GREEN, "Poisonous", Effect.INSTANT_SPELL), ICE(ChatColor.AQUA, "Frozen", Effect.SNOWBALL_BREAK),
    FIRE(ChatColor.YELLOW, "Furious", Effect.MOBSPAWNER_FLAMES);

    private static WeightedCollection<CreatureElement> weightedCollection = new WeightedCollection<CreatureElement>();
    private static boolean buffered = false;

    private ChatColor chatColor;
    private String prefix;
    private Effect effects;

    CreatureElement(ChatColor chatColor, String prefix, Effect effects)
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

    public static CreatureElement random()
    {
        if (buffered)
        {
            return weightedCollection.next();
        } else
        {
            return weightedCollection.next();
        }
    }

    private static void generate()
    {
        weightedCollection.add(0.7, EMPTY);
        weightedCollection.add(0.4, HOLY);
        weightedCollection.add(0.4, POISON);
        weightedCollection.add(0.4, ICE);
        weightedCollection.add(0.4, FIRE);
        buffered = true;
    }
}