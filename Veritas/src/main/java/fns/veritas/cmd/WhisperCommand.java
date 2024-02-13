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

package fns.veritas.cmd;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import fns.patchwork.kyori.MiniMessageWrapper;
import fns.veritas.cmd.base.BotCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import reactor.core.publisher.Mono;

public class WhisperCommand implements BotCommand
{
    @Override
    public String getName()
    {
        return "whisper";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event)
    {
        final String player = event.getOption("player")
                                   .flatMap(ApplicationCommandInteractionOption::getValue)
                                   .map(ApplicationCommandInteractionOptionValue::asString)
                                   .orElseThrow();

        final String message = event.getOption("message")
                                    .flatMap(ApplicationCommandInteractionOption::getValue)
                                    .map(ApplicationCommandInteractionOptionValue::asString)
                                    .orElseThrow();
        final Component c = MiniMessageWrapper.deserialize(true,
                                                           "<gray>[<yellow>Whisper<gray>] <white>"
                                                               + event.getInteraction().getUser().getUsername()
                                                               + "<gray>: "
                                                               + message);

        final Player actual = Bukkit.getPlayer(player);
        if (actual == null) {
            return event.reply("Player not found!")
                 .withEphemeral(true)
                 .then();
        }

        actual.sendMessage(c);

        return event.reply("Sent!")
                    .withEphemeral(true)
                    .then();
    }
}
