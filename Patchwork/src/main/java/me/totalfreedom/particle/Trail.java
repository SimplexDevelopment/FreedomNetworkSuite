package me.totalfreedom.particle;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface Trail
{
    @NotNull
    UUID getAssocPlayerUUID();

    // Nullable because the player may not be online and trail selections should be persistent whether they are
    // active or not.
    @Nullable
    Player getAssocPlayer();

    @NotNull
    TrailType getTrailType();

    @NotNull
    Color getColor();

    void setColor(@NotNull Color color);

    boolean isActive();

    void setActive(boolean active);
}
