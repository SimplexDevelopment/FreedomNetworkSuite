package me.totalfreedom.particle;

import me.totalfreedom.api.Interpolator;
import me.totalfreedom.utils.InterpolationUtils;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

/**
 * Represents a Trail instance for a specific player.
 */
public interface Trail
{
    /**
     * Returns the UUID of the player associated with the trail. This is for usage with our persistant storage
     * container so that we can safely send and retrieve the trails without having to directly reference a player
     * object.
     * <br>
     * TL;DR Memory optimization!
     *
     * @return The UUID of the player associated with this trail.
     */
    @NotNull
    UUID getAssociatedPlayerUUID();

    /**
     * Returns the player associated with this trail. Trails are user specific, and should be persistent across all
     * usages. This is also used when displaying the particles, as they will be relative to the player's back, which
     * is an inverse offset of the player's eye location. We use OfflinePlayer as we can make a simple check and cast
     * to determine if the player is online when spawning trails.
     *
     * @return The player associated with this Trail.
     */
    @NotNull
    OfflinePlayer getAssociatedPlayer();

    /**
     * Gets the Trail Type of this trail. This is used to determine what type of trail this is, and what
     * {@link Particle} it should use.
     *
     * @return The Trail Type of this trail.
     * @see TrailType
     */
    @NotNull
    TrailType getTrailType();

    /**
     * This method is nullable because if the value of {@link #isGradient()} is true, then
     * {@link #getColors()} should be used instead, as that will contain the color data for our trail.
     * <br>
     * However, this method will also be null if the particle type is not colorable.
     *
     * @return The color of the trail, or null if the trail is a gradient or non-colorable.
     * @see Particle
     * @see #getColors();
     */
    @Nullable
    Color getColor();

    /**
     * Sets the static color of the trail. If you are trying to use a gradient, use {@link #setColors(Set)} instead.
     * <br>
     *
     * @param color The color to set the trail to.
     */
    void setColor(@NotNull Color color);

    /**
     * This method is nullable because if the value of {@link #isGradient()} is false, then
     * {@link #getColor()} should be used instead, as our trail is a single static color.
     * <br>
     * However, this method will also be null if the particle type is not colorable.
     *
     * @return The colors of the trail, or null if the trail is not a gradient or non-colorable.
     * @see #getColor()
     * @see Particle
     * @see InterpolationUtils
     * @see Interpolator
     */
    @Nullable
    Set<Color> getColors();

    /**
     * Sets the colors of the trail. If you are trying to use a static color, use {@link #setColor(Color)} instead.
     * <br>
     * This should be used for trails that iterate over a set of colors, such as a rainbow trail.
     *
     * @param colors The colors to set the trail to. It is recommended to use {@link InterpolationUtils} to generate
     *               interpolated gradients for this.
     */
    void setColors(@NotNull Set<Color> colors);

    /**
     * Validates whether this Trail is a gradient or a static trail.
     * <br>
     * This is entirely based on whether {@link #getColors()} returns null or not.
     *
     * @return True if {@link #getColors()} is not null, false otherwise.
     */
    boolean isGradient();

    /**
     * Gets whether the trail is active.
     *
     * @return True if the trail is active, false if it is not.
     */
    boolean isActive();

    /**
     * Turn the trail on or off.
     *
     * @param active True if the trail should be active, false if it should not.
     */
    void setActive(final boolean active);

    /**
     * Spawns a particle (if gradient, the next particle) on the supplied location object.
     */
    void spawnParticle();
}
