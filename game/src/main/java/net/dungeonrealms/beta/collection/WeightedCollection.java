package net.dungeonrealms.beta.collection;


import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class WeightedCollection<E>
{
    private final NavigableMap<Double, E> map = new TreeMap<>();
    private final Random random;
    private double total = 0;

    public WeightedCollection()
    {
        this(new Random());
    }

    public WeightedCollection(Random random)
    {
        this.random = random;
    }

    public void add(double weight, E result)
    {
        if (weight <= 0) return;
        total += weight;
        map.put(total, result);
    }

    public E next()
    {
        double value = random.nextDouble() * total;
        return map.ceilingEntry(value).getValue();
    }

}
