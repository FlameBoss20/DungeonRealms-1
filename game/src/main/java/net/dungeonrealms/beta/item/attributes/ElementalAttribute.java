package net.dungeonrealms.beta.item.attributes;

import net.dungeonrealms.beta.collection.WeightedCollection;

/**
 * Created by Giovanni on 14-8-2016.
 */
public enum ElementalAttribute
{
    EMPTY(""), FIRE("Fire"), ICE("Ice"), POISON("Poison"); //I decided to add poison as element..

    private static WeightedCollection<ElementalAttribute> elementalCollection = new WeightedCollection<ElementalAttribute>();
    private static boolean buffered = false;

    private String identifier;

    ElementalAttribute(String identifier)
    {
        this.identifier = identifier;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public static ElementalAttribute random()
    {
        if (buffered)
        {
            return elementalCollection.next();
        } else
        {
            generate();
            return elementalCollection.next();
        }
    }

    private static void generate()
    {
        elementalCollection.add(0.6, EMPTY);
        elementalCollection.add(0.4, FIRE);
        elementalCollection.add(0.4, ICE);
        elementalCollection.add(0.4, POISON);
        buffered = true;
    }
}
