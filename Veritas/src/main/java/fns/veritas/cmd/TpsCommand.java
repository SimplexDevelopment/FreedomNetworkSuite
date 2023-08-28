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

package fns.veritas.cmd;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import fns.veritas.cmd.base.BotCommand;
import fns.veritas.messaging.Embed;
import fns.veritas.messaging.EmbedWrapper;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import reactor.core.publisher.Mono;

public class TpsCommand implements BotCommand
{
    @Override
    public String getName()
    {
        return "tps";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event)
    {
        final double[] tps = Bukkit.getServer().getTPS();
        final EmbedWrapper e = new EmbedWrapper();

        final List<Embed> embeds = new ArrayList<>();

        embeds.add(embedContent("1 Minute:", String.valueOf(tps[0]), false));
        embeds.add(embedContent("5 Minutes:", String.valueOf(tps[1]), false));
        embeds.add(embedContent("15 Minutes:", String.valueOf(tps[2]), false));

        e.quickEmbed("Server TPS:",
                     "Current TPS (1m, 5m, 15m)",
                     embeds);

        return event.reply()
                    .withEmbeds(e.getEmbeds())
                    .withEphemeral(true)
                    .then();
    }
}
