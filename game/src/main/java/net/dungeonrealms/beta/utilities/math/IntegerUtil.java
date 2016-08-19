package net.dungeonrealms.beta.utilities.math;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class IntegerUtil
{
    public static int randomInteger(int min, int max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static int fixInteger(int target, int maxDecimals)
    {
        NumberFormat integer = NumberFormat.getInstance();
        integer.setRoundingMode(RoundingMode.DOWN);
        integer.setMaximumFractionDigits(maxDecimals);
        return Integer.valueOf(integer.format(target));
    }
}
