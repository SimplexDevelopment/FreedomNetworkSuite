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
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import fns.patchwork.kyori.PlainTextWrapper;
import fns.veritas.cmd.base.BotCommand;
import fns.veritas.messaging.Embed;
import fns.veritas.messaging.EmbedWrapper;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import reactor.core.publisher.Mono;

public class ListCommand implements BotCommand
{
    @Override
    public String getName()
    {
        return "list";
    }

    @Override
    public Mono<Void> handle(final ChatInputInteractionEvent event)
    {
        final boolean showStaff = event.getOption("staff")
                                       .flatMap(ApplicationCommandInteractionOption::getValue)
                                       .map(ApplicationCommandInteractionOptionValue::asBoolean)
                                       .orElse(false);

        if (showStaff)
            return staffList(event);

        final EmbedWrapper e = new EmbedWrapper();
        final List<Embed> embeds = new ArrayList<>();

        Bukkit.getOnlinePlayers()
              .forEach(player ->
                       {
                           final String display = PlainTextWrapper.toPlainText(player.displayName());
                           final String actual = PlainTextWrapper.toPlainText(player.name());

                           final Embed embed = new Embed(display, actual, false);
                           embeds.add(embed);
                       });

        e.quickEmbed("Player List", "List of currently online players:", embeds);

        return event.reply()
                    .withEmbeds(e.getEmbeds())
                    .withEphemeral(true)
                    .then();
    }

    private Mono<Void> staffList(final ChatInputInteractionEvent event)
    {
        final EmbedWrapper wrapper = new EmbedWrapper();
        final List<Embed> embeds = new ArrayList<>();

        Bukkit.getOnlinePlayers()
              .stream()
              .filter(player -> player.hasPermission("fns.marker.staff"))
              .forEach(player ->
                       {
                           final String display = PlainTextWrapper.toPlainText(player.displayName());
                           final String actual = PlainTextWrapper.toPlainText(player.name());

                           final Embed embed = new Embed(display, actual, false);
                           embeds.add(embed);
                       });

        wrapper.quickEmbed("Staff List", "List of currently online staff members:", embeds);

        return event.reply()
                    .withEmbeds(wrapper.getEmbeds())
                    .withEphemeral(true)
                    .then();
    }
}
