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

package fns.fossil.trail.types;

import fns.patchwork.particle.TrailType;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public final class StrobeTrail extends SimpleTrail
{
    private final Particle.DustTransition dustTransition;

    public StrobeTrail(final Player player, final Color from, final Color to)
    {
        super(player, TrailType.STROBE);
        this.dustTransition = new Particle.DustTransition(from, to, 3F);
    }

    @Override
    public void spawnParticle()
    {
        if (!getAssociatedPlayer().isOnline() || !isActive()) return;

        final Player player = (Player) getAssociatedPlayer();
        final Location location = player.getLocation()
                                        .clone()
                                        .subtract(0, 1, 0);
        location.getWorld()
                .spawnParticle(getTrailType().getType(), location, 1, 0.0, 0.5, 0.0, dustTransition);
    }
}
