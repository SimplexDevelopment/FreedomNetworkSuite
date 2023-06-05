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

public class BossBarDisplay
{
    private BossBar bossBar;

    public BossBarDisplay(final BossBar bossBar)
    {
        this.bossBar = bossBar;
    }

    public static BossBarBuilder builder()
    {
        return new BossBarBuilder();
    }

    public void changeColor(final BossBar.Color color)
    {
        this.bossBar.color(color);
    }

    public void changeOverlay(final BossBar.Overlay overlay)
    {
        this.bossBar.overlay(overlay);
    }

    public void changeName(final Component name)
    {
        this.bossBar.name(name);
    }

    public void changeName(final String name, final TextColor color)
    {
        this.bossBar.name(Component.text(name, color));
    }

    public void changeName(final String name)
    {
        this.bossBar.name(Component.text(name));
    }

    public void showTo(final Audience audience)
    {
        audience.showBossBar(getBossBar());
    }

    public BossBar getBossBar()
    {
        return this.bossBar;
    }

    public void setBossBar(final BossBar bossBar)
    {
        this.bossBar = bossBar;
    }

    public void hideFrom(final Audience audience)
    {
        audience.hideBossBar(getBossBar());
    }

    public void incrementProgress(final @Range(from = 0, to = 100) float progress)
    {
        final float currentProgress = this.bossBar.progress();
        final float newProgress = currentProgress + (progress / 100.0F);

        if (newProgress > 1) this.bossBar.progress(1.0F);
        else this.bossBar.progress(newProgress);
    }

    public void decrementProgress(final @Range(from = 0, to = 100) float progress)
    {
        final float currentProgress = this.bossBar.progress();
        final float newProgress = currentProgress - (progress / 100.0F);

        if (newProgress < 0) this.bossBar.progress(0.0F);
        else this.bossBar.progress(newProgress);
    }

    public void maximumProgress()
    {
        this.bossBar.progress(1.0F);
    }

    public void halfProgress()
    {
        this.bossBar.progress(0.5F);
    }

    public void minimumProgress()
    {
        this.bossBar.progress(0.0F);
    }

    public void showForwarded(final ForwardingAudience forwardingAudience)
    {
        forwardingAudience.showBossBar(getBossBar());
    }

    public void hideForwarded(final ForwardingAudience forwardingAudience)
    {
        forwardingAudience.hideBossBar(getBossBar());
    }

    private static final class BossBarBuilder
    {
        private final Set<BossBar.Flag> flags = new HashSet<>();
        private Component name;
        private BossBar.Color color;
        private BossBar.Overlay overlay;
        @Range(from = 0, to = 1)
        private float progress;

        public BossBarBuilder()
        {
            this.name = Component.empty();
            this.color = BossBar.Color.GREEN;
            this.overlay = BossBar.Overlay.PROGRESS;
            this.progress = 0.0F;
        }

        public BossBarBuilder setName(final Component name)
        {
            this.name = name;
            return this;
        }

        public BossBarBuilder setName(final String name, final TextColor color)
        {
            this.name = Component.text(name, color);
            return this;
        }

        public BossBarBuilder setName(final String name)
        {
            this.name = Component.text(name);
            return this;
        }

        public BossBarBuilder addFlag(final BossBar.Flag flag)
        {
            this.flags.add(flag);
            return this;
        }

        public BossBarBuilder addFlags(final BossBar.Flag... flags)
        {
            this.flags.addAll(List.of(flags));
            return this;
        }

        public BossBarBuilder removeFlag(final BossBar.Flag flag)
        {
            this.flags.remove(flag);
            return this;
        }

        public BossBarBuilder removeFlags(final BossBar.Flag... flags)
        {
            this.flags.removeAll(List.of(flags));
            return this;
        }

        public BossBarBuilder clearFlags()
        {
            this.flags.clear();
            return this;
        }

        public BossBarBuilder setColor(final BossBar.Color color)
        {
            this.color = color;
            return this;
        }

        public BossBarBuilder setOverlay(final BossBar.Overlay overlay)
        {
            this.overlay = overlay;
            return this;
        }

        public BossBarBuilder setProgress(final @Range(from = 0, to = 100) float progress)
        {
            this.progress = progress / 100.0F;
            return this;
        }

        public BossBar build()
        {
            return BossBar.bossBar(this.name, this.progress, this.color, this.overlay, this.flags);
        }
    }
}
