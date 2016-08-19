package net.dungeonrealms.beta.creature.type.skeleton;

import net.dungeonrealms.beta.base64.Base64Skull;
import net.dungeonrealms.beta.collection.WeightedCollection;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Giovanni on 16-8-2016.
 */
public enum SkeletonType
{
    DEFAULT("", new Base64Skull().getSkullByUrl("http://textures.minecraft.net/texture/5cd713c5f5e46da436a8f54b523d43af29f7ae8fb184792cca73b1717feaa61")),
    OVERLORD("Overlord", new Base64Skull().getSkullByUrl("http://textures.minecraft.net/texture/3449559b6f7e24920ecdf3bd2e425f434157c236334f482cf98a1349da168")),
    DEATHLORD("Deathlord", new Base64Skull().getSkullByUrl("http://textures.minecraft.net/texture/de8c12ef253d9c63d231fa6f3fbbf179da91a92f3fb4ccedc6c661da26c5a"));

    private ItemStack skull;
    private String tag;

    private static WeightedCollection<SkeletonType> typeCollection = new WeightedCollection<SkeletonType>();
    private static boolean buffered = false;

    SkeletonType(String tag, ItemStack skull)
    {
        this.skull = skull;
        this.tag = tag;
    }

    public String getTag()
    {
        return tag;
    }

    public ItemStack getSkull()
    {
        return skull;
    }


    public static SkeletonType random()
    {
        if (buffered)
        {
            return typeCollection.next();
        } else
        {
            generate();
            return typeCollection.next();
        }
    }

    private static void generate()
    {
        typeCollection.add(0.8, DEFAULT);
        typeCollection.add(0.2, OVERLORD);
        typeCollection.add(0.1, DEATHLORD);
        buffered = true;
    }
}
