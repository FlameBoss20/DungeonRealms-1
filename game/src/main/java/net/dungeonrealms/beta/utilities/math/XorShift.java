package net.dungeonrealms.beta.utilities.math;

import java.util.Random;

/**
 * Created by Dr. Nick Doran on 8/15/2016.
 */
public class XorShift extends Random {

    private long seed;

    public XorShift(long seed) {
        this.seed = seed;
    }

    protected int next(int nbits) {
        long x = seed;
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);
        seed = x;
        x &= ((1L << nbits) - 1);
        return (int) x;
    }
}