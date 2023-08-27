/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.patchwork.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import org.bukkit.Location;
import org.bukkit.World;

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
