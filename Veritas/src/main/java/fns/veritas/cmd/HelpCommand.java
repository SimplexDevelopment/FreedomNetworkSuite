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
import reactor.core.publisher.Mono;

public class HelpCommand implements BotCommand
{
    @Override
    public String getName()
    {
        return "help";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event)
    {
        final List<Embed> content = new ArrayList<>();
        final EmbedWrapper e = new EmbedWrapper();

        content.add(embedContent("help",
                                 "Shows this message. \n" +
                                     "Use /help info to see information about the server.",
                                 false));
        content.add(embedContent("tps",
                                 "Shows the server's current TPS.",
                                 false));
        content.add(embedContent("list",
                                 "Shows a list of all online players. \n" +
                                     "Use /list staff to show online staff.",
                                 false));
        e.quickEmbed("Command List:",
                     "A list of all currently supported commands",
                     content);

        return event.reply()
                    .withContent("Here is a list of all currently supported commands:")
                    .withEmbeds(e.getEmbeds())
                    .withEphemeral(true)
                    .then();
    }
}
