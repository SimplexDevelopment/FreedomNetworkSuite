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

import fns.veritas.Veritas;
import fns.veritas.client.BotClient;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitNative implements Listener
{
    private final BotClient bot;
    private final Veritas plugin;

    public BukkitNative(final Veritas plugin)
    {
        this.plugin = plugin;
        this.bot = plugin.getAggregate().getBot();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        plugin.getAggregate()
              .getBot()
              .messageChatChannel("**" + plugin.getAggregate()
                                               .getBot()
                                               .deformat(event.getPlayer().getName())
                                      + " joined the server" + "**", true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        plugin.getAggregate()
              .getBot()
              .messageChatChannel("**"
                                      + plugin.getAggregate()
                                              .getBot()
                                              .deformat(event.getPlayer().getName())
                                      + " left the server" + "**", true);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        //Avoiding NPE Unboxing Warnings
        Boolean b = event.getEntity()
                         .getWorld()
                         .getGameRuleValue(GameRule.SHOW_DEATH_MESSAGES);
        if (b == null || !b)
        {
            return;
        }

        Component deathMessage = event.deathMessage();

        if (deathMessage != null)
        {
            plugin.getAggregate()
                  .getBot()
                  .messageChatChannel("**"
                                          + plugin.getAggregate()
                                                  .getBot()
                                                  .deformat(
                                                      PlainTextComponentSerializer.plainText()
                                                                                  .serialize(
                                                                                      deathMessage))
                                          + "**", true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(final AsyncChatEvent event)
    {
        Player player = event.getPlayer();
        String message = PlainTextComponentSerializer.plainText().serialize(event.message());

        if (!plugin.getServer().hasWhitelist() && bot != null)
        {
            plugin.getAggregate().getBot().messageChatChannel(player.getName()
                                                                  + " \u00BB "
                                                                  + message, true);
        }
    }
}
