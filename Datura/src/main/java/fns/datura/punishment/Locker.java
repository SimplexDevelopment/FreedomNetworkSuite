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

package fns.datura.punishment;

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Shortcuts;
import fns.patchwork.service.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.SplittableRandom;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class Locker extends Service
{
    private final Set<UUID> lockedPlayers = new HashSet<>();

    public Locker()
    {
        super("locker-service");
    }

    public void lock(final Player player)
    {
        lockedPlayers.add(player.getUniqueId());
    }

    public void unlock(final Player player)
    {
        lockedPlayers.remove(player.getUniqueId());
    }

    @Override
    public void tick()
    {
        lockedPlayers.removeIf(uuid -> !Shortcuts.provideModule(Patchwork.class)
                                                 .getServer()
                                                 .getOfflinePlayer(uuid)
                                                 .isOnline());

        for (final UUID uuid : lockedPlayers)
        {
            final Player player = Bukkit.getPlayer(uuid);
            if (player == null) continue;

            lockingMethod(player);
        }
    }

    private void lockingMethod(@NotNull final Player player)
    {
        final double x = player.getLocation()
                               .getX();
        final double z = player.getLocation()
                               .getZ();

        if ((x / z % 0.001) < 1)
        {
            player.setVelocity(new Vector(x % 12, 0, z % 12));
        }

        player.setWalkSpeed(0.0f);
        player.setFlySpeed(0.0f);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setInvulnerable(true);
        player.setCollidable(false);
        player.setGliding(false);
        player.setGlowing(true);
        player.setSilent(true);
        player.setCanPickupItems(false);
        player.setInvisible(true);

        player.openInventory(Bukkit.createInventory(null, 54));
        player.closeInventory(InventoryCloseEvent.Reason.UNKNOWN);
        player.teleport(player.getLocation()
                              .clone());

        final SplittableRandom random = new SplittableRandom();
        player.getEyeLocation()
              .add(new Vector(
                      random.nextDouble(-1.0, 1.0),
                      random.nextDouble(-1.0, 1.0),
                      random.nextDouble(-1.0, 1.0)
              ));
    }
}
