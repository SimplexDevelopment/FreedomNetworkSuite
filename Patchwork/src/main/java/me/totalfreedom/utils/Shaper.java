package me.totalfreedom.utils;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

public class Shaper
{
    private final double start;
    private final double end;
    private final World world;

    public Shaper(World world, double start, double end)
    {
        this.start = start;
        this.end = end;
        this.world = world;
    }

    public List<Location> generate(int count, DoubleUnaryOperator x, DoubleUnaryOperator y, DoubleUnaryOperator z)
    {
        double step = (start - end) / (count - 1);
        LinkedList<Location> lset = new LinkedList<>();

        for (int i = 0; i < count; i++)
        {
            double t = start + i * step;
            double xp = x.applyAsDouble(t);
            double yp = y.applyAsDouble(t);
            double zp = z.applyAsDouble(t);

            lset.add(new Location(world, xp, yp, zp));
        }

        return lset;
    }
}
