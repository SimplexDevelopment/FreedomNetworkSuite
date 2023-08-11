/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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

package fns.fossil.trail.types;

import fns.patchwork.particle.TrailType;
import fns.patchwork.utils.InterpolationUtils;
import java.util.Iterator;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public final class RainbowTrail extends SimpleTrail
{
    private Iterator<Color> currentColor;

    protected RainbowTrail(final Player player)
    {
        super(player, TrailType.DEFAULT);
        setColors(InterpolationUtils.rainbow(40 % 7));
        this.currentColor = getColors().iterator();
    }

    @Override
    public void spawnParticle()
    {
        // Exit immediately if either case is false.
        if (!isActive() || !getAssociatedPlayer().isOnline()) return;

        // Re-initialize the color iterator if the iterator has previously reached the end of its index.
        if (!currentColor.hasNext())
        {
            this.currentColor = getColors().iterator();
        }

        final Color color = currentColor.next();
        final Player player = (Player) getAssociatedPlayer();
        final Particle particle = getTrailType().getType();
        final Particle.DustOptions options = new Particle.DustOptions(color, 3F);
        final Location location = player.getLocation()
                                        .clone()
                                        .subtract(0, 1, 0);

        location.getWorld()
                .spawnParticle(particle, location, 1, 0.0, 0.5, 0.0, options);
    }
}