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

package fns.patchwork.shop;

import fns.patchwork.service.Service;
import fns.patchwork.service.Task;
import fns.patchwork.service.TimedTask;
import java.time.Duration;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Listener;

/**
 * Represents a chat reaction that can be performed by a player.
 */
public abstract class Reaction extends TimedTask implements Reactable
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
        super("CopyCatReaction",
              Duration.ofSeconds(1),
              Duration.ofSeconds(10));
        this.reward = reward;
        this.reactionDuration = duration;
        this.reactionType = reactionType;
    }

    @Override
    public Component getReactionMessage()
    {
        return reactionMessage;
    }

    public void setReactionMessage(final Component message)
    {
        this.reactionMessage = message;
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
}
