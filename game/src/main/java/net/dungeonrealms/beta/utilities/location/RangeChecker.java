package net.dungeonrealms.beta.utilities.location;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giovanni / Vawke on 12-7-2016
 */
public class RangeChecker
{
    /**
     * Get the entities in range of another entity.
     * @param type   the entity you want to get the nearby entities from
     * @param base   the location the check starts
     * @param range  the range it checks in
     * @param <T>    return
     * @return entity
     */
    public static <T extends Entity> Entity[] getEntitiesInRange(Class<T> type, Location base, int range)
    {
        List<Entity> ent = new ArrayList<>();
        base.getWorld().getNearbyEntities(base, range, range, range).stream().filter(type::isInstance).forEach(ent::add);
        Entity[] entityArray = new Entity[ent.size()];
        for (int i = 0; i < entityArray.length; i++) entityArray[i] = ent.get(i);
        return entityArray;
    }
}
