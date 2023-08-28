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

package fns.datura.features;

import fns.patchwork.service.Service;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Fuckoff extends Service
{
    private final Map<UUID, Integer> players = new ConcurrentHashMap<>();

    public Fuckoff()
    {
        super("fuckoff-service");
    }

    public void add(final Player player, final int radius)
    {
        players.put(player.getUniqueId(), radius);
    }

    public void remove(final Player player)
    {
        players.remove(player.getUniqueId());
    }

    @Override
    public void tick()
    {
        for (final Map.Entry<UUID, Integer> entry : players.entrySet())
        {
            final var player = Bukkit.getPlayer(entry.getKey());

            if (player == null)
            {
                players.remove(entry.getKey());
                continue;
            }

            pushPlayers(player, entry.getValue());
        }
    }

    private void pushPlayers(@NotNull final Player player, final int radius)
    {
        Bukkit.getOnlinePlayers()
              .stream()
              .filter(onlinePlayer -> onlinePlayer.getLocation()
                                                  .clone()
                                                  .distanceSquared(player.getLocation()) < Math.pow(radius, 2))
              .forEach(onlinePlayer ->
                           onlinePlayer.setVelocity(player.getLocation()
                                                          .toVector()
                                                          .add(onlinePlayer.getLocation().toVector())
                                                          .normalize()
                                                          .multiply(radius)));
    }
}
