package me.totalfreedom.shop;

import me.totalfreedom.display.BossBarDisplay;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;

import java.time.Duration;

/**
 * Represents a chat reaction that can be performed by a player.
 */
public abstract class Reaction implements Reactable
{
    private final Duration reactionDuration;
    private final ReactionType reactionType;
    private final long reward;
    private Component reactionMessage = Component.empty();

    protected Reaction(final ReactionType type)
    {
        this(50L, type);
    }

    protected Reaction(final long reward, final ReactionType type)
    {
        this(30L, reward, type);
    }

    protected Reaction(final long seconds, final long reward, final ReactionType reactionType)
    {
        this(Duration.ofSeconds(seconds), reward, reactionType);
    }

    protected Reaction(final Duration duration, final long reward, final ReactionType reactionType)
    {
        this.reward = reward;
        this.reactionDuration = duration;
        this.reactionType = reactionType;
    }

    @Override
    public Component getReactionMessage()
    {
        return reactionMessage;
    }

    @Override
    public Duration getReactionDuration()
    {
        return reactionDuration;
    }

    @Override
    public ReactionType getReactionType()
    {
        return reactionType;
    }

    @Override
    public BossBarDisplay getBossBarDisplay()
    {

    }

    public void setReactionMessage(final Component message)
    {
        this.reactionMessage = message;
    }
}
