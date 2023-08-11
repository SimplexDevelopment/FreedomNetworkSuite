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

package fns.patchwork.shop;

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Registration;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.display.BossBarDisplay;
import fns.patchwork.display.BossBarTimer;
import fns.patchwork.economy.EconomicEntity;
import fns.patchwork.service.Task;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ReactionTask extends Task implements Listener
{
    private final Reaction reaction;
    private final BossBarDisplay bossBarDisplay;

    public ReactionTask(final String name, final Reaction reaction)
    {
        super(name, -1L, -1);
        this.reaction = reaction;
        final BossBar bossBar = BossBarDisplay.builder()
                                              .setName(reaction.getReactionMessage())
                                              .setColor(BossBar.Color.GREEN)
                                              .setProgress(0.0F)
                                              .build();

        this.bossBarDisplay = new BossBarDisplay(bossBar);
    }

    @Override
    public void run()
    {
        if (isCancelled())
        {
        }

        final BossBarTimer timer = new BossBarTimer(bossBarDisplay, reaction.getReactionDuration());
        timer.runTaskTimer(Shortcuts.provideModule(Patchwork.class), 0L, timer.getInterval());
    }

    @EventHandler
    public void onPlayerChat(final AsyncChatEvent event)
    {
        if (event.message()
                 .equals(reaction.getReactionMessage()))
        {
            final EconomicEntity entity = Registration.getUserRegistry()
                                                   .getUser(event.getPlayer());

            reaction.onReact(entity);
        }
    }
}
