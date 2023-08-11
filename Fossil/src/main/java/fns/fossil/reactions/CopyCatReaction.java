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

package fns.fossil.reactions;

import fns.patchwork.display.BossBarDisplay;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.shop.Reaction;
import fns.patchwork.shop.ReactionType;
import java.util.SplittableRandom;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;

/**
 * Represents a single chat reaction that can be performed by a player.
 */
public final class CopyCatReaction extends Reaction
{
    private final long reward;

    public CopyCatReaction(final long reward)
    {
        super(ReactionType.COPYCAT);
        this.reward = reward;
    }

    @Override
    public long getReward()
    {
        return reward;
    }

    @Override
    public void display(final Audience audience)
    {
        final BossBar bossBar = BossBarDisplay.builder()
                                              .setName(getRandomCharacterString())
                                              .setProgress(0.0F)
                                              .build();
    }

    @Override
    public void onReact(final EconomicEntity entity)
    {
        //
    }

    public String getRandomCharacterString()
    {
        final SplittableRandom random = new SplittableRandom();
        final StringBuilder sb = new StringBuilder(10);

        final String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < 10; i++)
        {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }
}
