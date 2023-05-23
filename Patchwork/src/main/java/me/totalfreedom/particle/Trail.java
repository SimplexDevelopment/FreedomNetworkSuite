package me.totalfreedom.particle;

import org.bukkit.Color;
import org.jetbrains.annotations.Nullable;

public interface Trail
{
    TrailType getTrailType();

    @Nullable Color getColor();
}
