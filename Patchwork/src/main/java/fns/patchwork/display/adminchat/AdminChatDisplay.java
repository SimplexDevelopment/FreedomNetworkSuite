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

package fns.patchwork.display.adminchat;

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Registration;
import fns.patchwork.permissible.Groups;
import fns.patchwork.user.UserData;
import io.papermc.paper.event.player.AsyncChatEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AdminChatDisplay
{
    protected static final String ACPERM = "patchwork.adminchat";
    private final Map<UUID, AdminChatFormat> adminChatFormat = new HashMap<>();
    private final Set<UUID> toggledChat = new HashSet<>();

    public AdminChatDisplay(final Patchwork patchwork)
    {
        new ACListener(this, patchwork);
    }

    public void addPlayer(final Player player, final AdminChatFormat format)
    {
        adminChatFormat.put(player.getUniqueId(), format);
    }

    public void removePlayer(final Player player)
    {
        adminChatFormat.remove(player.getUniqueId());
    }

    public boolean hasPlayer(final Player player)
    {
        return adminChatFormat.containsKey(player.getUniqueId());
    }

    public void updateFormat(final Player player, final AdminChatFormat newFormat)
    {
        adminChatFormat.put(player.getUniqueId(), newFormat);
    }

    public AdminChatFormat getFormat(final Player player)
    {
        return adminChatFormat.get(player.getUniqueId());
    }

    public Set<UUID> getPlayers()
    {
        return adminChatFormat.keySet();
    }

    public Map<UUID, AdminChatFormat> getAdminChatFormat()
    {
        return adminChatFormat;
    }

    public boolean isToggled(final Player player)
    {
        return toggledChat.contains(player.getUniqueId());
    }

    public void toggleChat(final Player player)
    {
        if (toggledChat.contains(player.getUniqueId()))
        {
            toggledChat.remove(player.getUniqueId());
        } else
        {
            toggledChat.add(player.getUniqueId());
        }
    }

    public void adminChatMessage(final CommandSender sender, final Component message)
    {
        Bukkit.getOnlinePlayers()
              .forEach(player ->
              {
                  if (player.hasPermission(ACPERM))
                  {
                      final Component formatted = Component.empty();
                      formatted.append(getFormat(player).format(sender.getName(), Groups.fromSender(sender)))
                               .append(Component.space())
                               .append(message);

                      player.sendMessage(formatted);
                  }
              });
    }

    public static final class ACListener implements Listener
    {
        private final AdminChatDisplay display;

        public ACListener(final AdminChatDisplay display, final Patchwork patchwork)
        {
            this.display = display;
            Bukkit.getPluginManager()
                  .registerEvents(this, patchwork);
        }

        @EventHandler
        public void playerChat(final AsyncChatEvent event)
        {
            if (display.isToggled(event.getPlayer()))
            {
                event.setCancelled(true);
                display.adminChatMessage(event.getPlayer(), event.message());
            }
        }

        @EventHandler
        public void playerJoin(final PlayerJoinEvent event)
        {
            final Player player = event.getPlayer();
            if (player.hasPermission(ACPERM))
            {
                final UserData data = Registration.getUserRegistry()
                                               .fromPlayer(player);
                if (data.hasCustomACFormat())
                {
                    display.addPlayer(player, data.getCustomACFormat());
                } else
                {
                    display.addPlayer(player, AdminChatFormat.DEFAULT);
                }
            }
        }
    }
}
