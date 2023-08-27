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

package fns.veritas.bukkit;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Attachment;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import fns.veritas.Veritas;
import fns.veritas.client.BotClient;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;

public class ServerListener
{

    private final BotClient bot;
    private final Veritas plugin;

    public ServerListener(final Veritas plugin)
    {
        this.plugin = plugin;
        this.bot = plugin.getAggregate().getBot();
    }

    public void minecraftChatBound()
    {
        bot.getClient()
           .getEventDispatcher()
           .on(MessageCreateEvent.class)
           .filter(m -> m.getMessage()
                         .getChannelId()
                         .equals(bot.getChatChannelId()))
           .filter(m -> m.getMember().orElse(null) != null)
           .filter(m -> !m.getMessage()
                          .getAuthor()
                          .orElseThrow(IllegalAccessError::new)
                          .getId()
                          .equals(plugin.getAggregate().getBot().getClient().getSelfId()))
           .doOnError(plugin.getAggregate().getLogger()::error)
           .subscribe(this::doMessageBodyDetails);
    }

    private void doMessageBodyDetails(MessageCreateEvent m)
    {
        Member member = m.getMember().orElseThrow();
        Message msg = m.getMessage();

        TextComponent.Builder builder = Component.text();
        TextComponent prefix = Component.text("[", NamedTextColor.DARK_GRAY)
                                        .append(Component.text("Discord", NamedTextColor.DARK_AQUA)
                                                         .hoverEvent(HoverEvent.showText(
                                                             Component.text("Click to join our Discord server!")))
                                                         .clickEvent(ClickEvent.openUrl(
                                                             plugin.getAggregate().getBot().getInviteLink())))
                                        .append(Component.text("] ", NamedTextColor.DARK_GRAY));
        TextComponent user = Component.empty();

        user = user.append(Component.text(member.getDisplayName().trim()));

        TextComponent message = Component.text(": ", NamedTextColor.DARK_GRAY)
                                         .append(
                                             Component.text(msg.getContent(), NamedTextColor.WHITE));

        // Attachments
        if (!msg.getAttachments().isEmpty())
        {
            if (!msg.getContent().isEmpty())
            {
                message = message.append(Component.space());
            }

            for (Attachment attachment : msg.getAttachments())
            {
                message = message.append(Component.text("[Media] ", NamedTextColor.YELLOW)
                                                  .hoverEvent(HoverEvent.showText(Component.text(attachment.getUrl())))
                                                  .clickEvent(ClickEvent.openUrl(attachment.getUrl())));
            }
        }

        Bukkit.broadcast(builder.append(prefix, user, message).build());
    }
}
