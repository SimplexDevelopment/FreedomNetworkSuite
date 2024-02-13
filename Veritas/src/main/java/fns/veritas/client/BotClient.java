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

package fns.veritas.client;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.entity.Guild;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.PartialMember;
import discord4j.core.object.entity.Role;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.TextChannel;
import discord4j.core.spec.MessageCreateSpec;
import fns.veritas.cmd.base.BotCommandHandler;
import java.util.List;
import java.util.Objects;
import reactor.core.publisher.Mono;

public class BotClient
{
    private final GatewayDiscordClient client;
    private final BotConfig config;
    private final List<String> subdomains;

    public BotClient(final BotConfig config)
    {
        this.config = config;
        this.subdomains = List.of("discordapp.com", "discord.com", "discord.gg");

        this.client = DiscordClientBuilder.create(config.getToken())
                                          .build()
                                          .login()
                                          .block();

        if (client == null)
            throw new IllegalStateException();

        final BotCommandHandler handler = new BotCommandHandler(client.getRestClient());

        client.on(ChatInputInteractionEvent.class, handler::handle);
    }
    public GatewayDiscordClient getClient()
    {
        return client;
    }

    public void messageChatChannel(String message, boolean system)
    {
        String channelID = config.getChatChannelId().asString();

        String sanitizedMessage = (system) ? message : sanitizeChatMessage(message);

        if (sanitizedMessage.isBlank())
            return;

        if (!channelID.isEmpty())
        {
            MessageCreateSpec spec = MessageCreateSpec.builder()
                                                      .content(sanitizedMessage)
                                                      .build();

            Mono<Message> sentMessage = getClient()
                .getChannelById(config.getChatChannelId())
                .ofType(TextChannel.class)
                .flatMap(c -> c.createMessage(spec));

            sentMessage.subscribe();
        }
    }

    private String sanitizeChatMessage(String message)
    {
        String newMessage = message;

        if (message.contains("@"))
        {
            newMessage = message.replace("@", "@\u200B");
        }

        if (message.toLowerCase().contains("discord.gg")) // discord.gg/invite works as an invite
        {
            return "";
        }

        for (String subdomain : subdomains)
        {
            if (message.toLowerCase().contains(subdomain + "/invite"))
            {
                return "";
            }
        }

        if (message.contains("§"))
        {
            newMessage = message.replace("§", "");
        }

        return deformat(newMessage);
    }

    public Mono<Boolean> isAdmin(final User user)
    {
        return getGuild().flatMap(guild -> guild.getMemberById(user.getId()))
            .flatMapMany(PartialMember::getRoles)
            .filter(role -> getConfig().getAdminRoleId().asLong() == role.getId().asLong())
            .filter(Objects::nonNull)
            .next()
            .hasElement();
    }

    public Mono<Channel> getLogsChannel() {
        return getGuild().flatMap(guild -> guild.getChannelById(getConfig().getLogChannelId()));
    }

    public Mono<Channel> getChatChannel() {
        return getGuild().flatMap(guild -> guild.getChannelById(getConfig().getChatChannelId()));
    }

    public Mono<Guild> getGuild() {
        return getClient().getGuildById(getConfig().getGuildId());
    }

    public BotConfig getConfig()
    {
        return config;
    }

    public String deformat(String input)
    {
        return input.replaceAll("([_\\\\`*>|])", "\\\\$1");
    }
}
