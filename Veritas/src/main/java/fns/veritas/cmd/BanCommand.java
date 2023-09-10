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
import discord4j.core.object.entity.User;
import fns.patchwork.base.Shortcuts;
import fns.veritas.Veritas;
import fns.veritas.cmd.base.BotCommand;
import java.time.Duration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import reactor.core.publisher.Mono;

public class BanCommand implements BotCommand
{


    @Override
    public String getName()
    {
        return "ban";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event)
    {
        final String playerName = event.getOption("player")
                                       .flatMap(ApplicationCommandInteractionOption::getValue)
                                       .map(ApplicationCommandInteractionOptionValue::asString)
                                       .orElseThrow();
        final String reason = event.getOption("reason")
                                   .flatMap(ApplicationCommandInteractionOption::getValue)
                                   .map(ApplicationCommandInteractionOptionValue::asString)
                                   .orElseThrow();
        final Duration duration = event.getOption("duration")
                                       .flatMap(ApplicationCommandInteractionOption::getValue)
                                       .map(ApplicationCommandInteractionOptionValue::asLong)
                                       .map(Duration::ofMinutes)
                                       .orElse(Duration.ofMinutes(5));

        final User user = event.getInteraction().getUser();
        return Shortcuts.provideModule(Veritas.class)
                        .getAggregate()
                        .getBot()
                        .isAdmin(user)
                        .doOnSuccess(b ->
                                     {
                                         if (Boolean.FALSE.equals(b))
                                             return;

                                         final Player player = Bukkit.getPlayer(playerName);
                                         if (player == null)
                                         {
                                             event.reply()
                                                  .withEphemeral(true)
                                                  .withContent("Player not found")
                                                  .block();
                                             return;
                                         }

                                         player.ban(reason, duration, user.getUsername());
                                         event.reply()
                                              .withContent("Kicked " + playerName)
                                              .withEphemeral(true)
                                              .block();

                                         event.getInteractionResponse()
                                              .createFollowupMessage(user.getUsername() + ": Kicked " + playerName)
                                              .then();
                                     })
                        .then();
    }
}
