package net.dungeonrealms.beta.item.data;

/**
 * Created by Giovanni on 12-8-2016.
 */
public enum ItemType
{
    SWORD("Sword"), AXE("Axe"), BOW("Bow"), POLEARM("Polearm"), STAFF("Staff"), SHIELD("Shield"),
    HELMET("Helmet"), CHESTPLATE("Chestplate"), LEGGINGS("Leggings"), BOOTS("Boots");

    private String identifier;

    ItemType(String identifier)
    {
        this.identifier = identifier;
    }

    public String getIdentifier()
    {
        return identifier;
    }
}
