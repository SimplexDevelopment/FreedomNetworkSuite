package me.totalfreedom.display;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Range;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is a wrapper for {@link BossBar} objects. It provides some handy methods for changing the boss bar's
 * properties, displaying the bar to {@link Audience}s, and a {@link BossBarBuilder} to easily create new boss bars.
 */
public class BossBarDisplay
{
    private BossBar bossBar;

    /**
     * Creates a new {@link BossBarDisplay} object.
     *
     * @param bossBar The {@link BossBar} to wrap.
     */
    public BossBarDisplay(final BossBar bossBar)
    {
        this.bossBar = bossBar;
    }

    /**
     * @return A new {@link BossBarBuilder} object.
     */
    public static BossBarBuilder builder()
    {
        return new BossBarBuilder();
    }

    /**
     * Changes the boss bar's color.
     *
     * @param color The new color.
     */
    public void changeColor(final BossBar.Color color)
    {
        this.bossBar.color(color);
    }

    /**
     * Changes the boss bar's color.
     *
     * @param overlay The new overlay.
     */
    public void changeOverlay(final BossBar.Overlay overlay)
    {
        this.bossBar.overlay(overlay);
    }

    /**
     * Changes the boss bar's name using a {@link Component}.
     *
     * @param name The new name.
     */
    public void changeName(final Component name)
    {
        this.bossBar.name(name);
    }

    /**
     * Changes the boss bar's name with a String and a {@link TextColor}.
     *
     * @param name  The new name.
     * @param color The name color.
     */
    public void changeName(final String name, final TextColor color)
    {
        this.bossBar.name(Component.text(name, color));
    }

    /**
     * Changes the boss bar's name with a String.
     *
     * @param name The new name.
     */
    public void changeName(final String name)
    {
        this.bossBar.name(Component.text(name));
    }

    /**
     * Shows this Boss Bar to the specified {@link Audience}.
     *
     * @param audience The {@link Audience} to show the Boss Bar to.
     */
    public void showTo(final Audience audience)
    {
        audience.showBossBar(getBossBar());
    }

    /**
     * @return The {@link BossBar} object that this class wraps.
     */
    public BossBar getBossBar()
    {
        return this.bossBar;
    }

    /**
     * Sets the {@link BossBar} object that this class wraps.
     *
     * @param bossBar The new {@link BossBar} object.
     */
    public void setBossBar(final BossBar bossBar)
    {
        this.bossBar = bossBar;
    }

    /**
     * Hides this Boss Bar from the specified {@link Audience}.
     *
     * @param audience The {@link Audience} to hide the Boss Bar from.
     */
    public void hideFrom(final Audience audience)
    {
        audience.hideBossBar(getBossBar());
    }

    /**
     * Increments the Bar's progress by the specified amount. This must be a range from 0 to 100.
     *
     * @param progress The new progress.
     */
    public void incrementProgress(final @Range(from = 0, to = 100) float progress)
    {
        final float currentProgress = this.bossBar.progress();
        final float newProgress = currentProgress + (progress / 100.0F);

        if (newProgress > 1) this.bossBar.progress(1.0F);
        else this.bossBar.progress(newProgress);
    }

    /**
     * Decrements the Bar's progress by the specified amount. This must be a range from 0 to 100.
     *
     * @param progress The new progress.
     */
    public void decrementProgress(final @Range(from = 0, to = 100) float progress)
    {
        final float currentProgress = this.bossBar.progress();
        final float newProgress = currentProgress - (progress / 100.0F);

        if (newProgress < 0) this.bossBar.progress(0.0F);
        else this.bossBar.progress(newProgress);
    }

    /**
     * Sets the Bar's progress to the maximum amount (full bar).
     */
    public void maximumProgress()
    {
        this.bossBar.progress(1.0F);
    }

    /**
     * Sets the Bar's progress to half of the maximum amount (half bar).
     */
    public void halfProgress()
    {
        this.bossBar.progress(0.5F);
    }

    /**
     * Sets the Bar's progress to the minimum amount (empty bar).
     */
    public void minimumProgress()
    {
        this.bossBar.progress(0.0F);
    }

    /**
     * Shows this Boss Bar to the specified {@link ForwardingAudience}.
     *
     * @param forwardingAudience The {@link ForwardingAudience} to show the Boss Bar to.
     */
    public void showForwarded(final ForwardingAudience forwardingAudience)
    {
        forwardingAudience.showBossBar(getBossBar());
    }

    /**
     * Hides this Boss Bar from the specified {@link ForwardingAudience}.
     *
     * @param forwardingAudience The {@link ForwardingAudience} to hide the Boss Bar from.
     */
    public void hideForwarded(final ForwardingAudience forwardingAudience)
    {
        forwardingAudience.hideBossBar(getBossBar());
    }

    /**
     * A Builder class for {@link BossBar} objects.
     */
    public static final class BossBarBuilder
    {
        /**
         * The flags that this Boss Bar will have.
         */
        private final Set<BossBar.Flag> flags = new HashSet<>();
        /**
         * The Boss Bar's name.
         */
        private Component name;
        /**
         * The Boss Bar's color.
         */
        private BossBar.Color color;
        /**
         * The Boss Bar's overlay.
         */
        private BossBar.Overlay overlay;
        /**
         * The Boss Bar's progress.
         */
        @Range(from = 0, to = 1)
        private float progress;

        /**
         * Initializes this Builder object.
         */
        public BossBarBuilder()
        {
            this.name = Component.empty();
            this.color = BossBar.Color.GREEN;
            this.overlay = BossBar.Overlay.PROGRESS;
            this.progress = 0.0F;
        }

        /**
         * Sets the name of the boss bar.
         *
         * @param name The name of the boss bar.
         * @return The builder.
         */
        public BossBarBuilder setName(final Component name)
        {
            this.name = name;
            return this;
        }

        /**
         * Sets the name of the boss bar using a String and a {@link TextColor}.
         *
         * @param name  The name of the boss bar.
         * @param color The color of the boss bar.
         * @return The builder.
         */
        public BossBarBuilder setName(final String name, final TextColor color)
        {
            this.name = Component.text(name, color);
            return this;
        }

        /**
         * Sets the name of the boss bar using a String.
         *
         * @param name The name of the boss bar.
         * @return The builder.
         */
        public BossBarBuilder setName(final String name)
        {
            this.name = Component.text(name);
            return this;
        }

        /**
         * Adds a flag to the boss bar.
         * @param flag The flag to add.
         * @return The builder.
         */
        public BossBarBuilder addFlag(final BossBar.Flag flag)
        {
            this.flags.add(flag);
            return this;
        }

        /**
         * Adds multiple flags to the boss bar.
         * @param flags The flags to add.
         * @return The builder.
         */
        public BossBarBuilder addFlags(final BossBar.Flag... flags)
        {
            this.flags.addAll(List.of(flags));
            return this;
        }

        /**
         * Removes a flag from the boss bar.
         * @param flag The flag to remove.
         * @return The builder.
         */
        public BossBarBuilder removeFlag(final BossBar.Flag flag)
        {
            this.flags.remove(flag);
            return this;
        }

        /**
         * Removes multiple flags from the boss bar.
         * @param flags The flags to remove.
         * @return The builder.
         */
        public BossBarBuilder removeFlags(final BossBar.Flag... flags)
        {
            this.flags.removeAll(List.of(flags));
            return this;
        }

        /**
         * Clears all flags from the boss bar.
         * @return The builder.
         */
        public BossBarBuilder clearFlags()
        {
            this.flags.clear();
            return this;
        }

        /**
         * Sets the color of the boss bar.
         * @param color The color of the boss bar.
         * @return The builder.
         */
        public BossBarBuilder setColor(final BossBar.Color color)
        {
            this.color = color;
            return this;
        }

        /**
         * Sets the overlay of the boss bar.
         * @param overlay The overlay of the boss bar.
         * @return The builder.
         */
        public BossBarBuilder setOverlay(final BossBar.Overlay overlay)
        {
            this.overlay = overlay;
            return this;
        }

        /**
         * Sets the progress of the boss bar. This must satisfy {@code 0 <= progress <= 100}.
         * @param progress The progress of the boss bar.
         * @return The builder.
         */
        public BossBarBuilder setProgress(final @Range(from = 0, to = 100) float progress)
        {
            this.progress = progress / 100.0F;
            return this;
        }

        /**
         * Builds the boss bar.
         * @return The {@link BossBar}.
         */
        public BossBar build()
        {
            return BossBar.bossBar(this.name, this.progress, this.color, this.overlay, this.flags);
        }
    }
}
