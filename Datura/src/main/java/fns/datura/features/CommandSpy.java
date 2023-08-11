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

package fns.datura.features;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpy implements Listener
{

    private final Set<UUID> commandWatchers;

    public CommandSpy()
    {
        this.commandWatchers = new HashSet<>();
    }

    public void spy(final UUID uuid)
    {
        this.commandWatchers.add(uuid);
    }

    public void stop(final UUID uuid)
    {
        this.commandWatchers.remove(uuid);
    }

    public void clear()
    {
        this.commandWatchers.clear();
    }

    public boolean isSpying(final UUID uuid)
    {
        return this.commandWatchers.contains(uuid);
    }

    @EventHandler
    public void commandProcess(final PlayerCommandPreprocessEvent event)
    {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> isSpying(player.getUniqueId()))
                .forEach(player -> player.sendMessage(Component.text(event.getPlayer().getName(), NamedTextColor.GRAY)
                        .append(Component.text(": ", NamedTextColor.GRAY))
                        .append(Component.text(event.getMessage(), NamedTextColor.GRAY))
                )
        );
    }
}
