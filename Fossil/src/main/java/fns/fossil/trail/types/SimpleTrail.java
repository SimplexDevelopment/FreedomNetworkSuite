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

import fns.patchwork.particle.Trail;
import fns.patchwork.particle.TrailType;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class SimpleTrail implements Trail
{
    private final UUID associatedPlayerUUID;
    private final TrailType trailType;

    private Color staticColor = null;
    private Set<Color> gradientColor = null;
    private boolean active = false;

    protected SimpleTrail(final Player player, final TrailType trailType)
    {
        this.associatedPlayerUUID = player.getUniqueId();
        this.trailType = trailType;
    }

    @Override
    public @NotNull UUID getAssociatedPlayerUUID()
    {
        return associatedPlayerUUID;
    }

    @Override
    public @NotNull OfflinePlayer getAssociatedPlayer()
    {
        return Bukkit.getOfflinePlayer(getAssociatedPlayerUUID());
    }

    @Override
    public @NotNull TrailType getTrailType()
    {
        return trailType;
    }

    @Override
    public @Nullable Color getColor()
    {
        return staticColor;
    }

    @Override
    public void setColor(@NotNull final Color color)
    {
        this.gradientColor = null;
        this.staticColor = color;
    }

    @Override
    public @Nullable Set<Color> getColors()
    {
        return this.gradientColor;
    }

    @Override
    public void setColors(@NotNull final Set<Color> colors)
    {
        this.staticColor = null;
        this.gradientColor = colors;
    }

    @Override
    public boolean isGradient()
    {
        return gradientColor != null;
    }

    @Override
    public boolean isActive()
    {
        return active;
    }

    @Override
    public void setActive(final boolean active)
    {
        this.active = active;
    }
}
