package me.totalfreedom.fossil.trail.types;

import me.totalfreedom.particle.Trail;
import me.totalfreedom.particle.TrailType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public abstract class SimpleTrail implements Trail
{
    private final UUID associatedPlayerUUID;
    private final TrailType trailType;

    private Color staticColor = null;
    private Set<Color> gradientColor = null;
    private boolean active = false;

    protected SimpleTrail(final Player player, final TrailType trailType) {
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
