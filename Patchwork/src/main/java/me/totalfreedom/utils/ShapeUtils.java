package me.totalfreedom.utils;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

public class ShapeUtils
{
    private final double start;
    private final double end;
    private final World world;

    public ShapeUtils(final World world, final double start, final double end)
    {
        this.start = start;
        this.end = end;
        this.world = world;
    }

    public List<Location> generate(final int count, final DoubleUnaryOperator x, final DoubleUnaryOperator y,
        final DoubleUnaryOperator z)
    {
        final double step = (start - end) / (count - 1);
        final LinkedList<Location> lset = new LinkedList<>();

        for (int i = 0; i < count; i++)
        {
            final double t = start + i * step;
            final double xp = x.applyAsDouble(t);
            final double yp = y.applyAsDouble(t);
            final double zp = z.applyAsDouble(t);

            lset.add(new Location(world, xp, yp, zp));
        }

        return lset;
    }
}
