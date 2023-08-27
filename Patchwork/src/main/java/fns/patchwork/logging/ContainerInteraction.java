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

package fns.patchwork.logging;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class ContainerInteraction implements Interaction<List<ItemStack>>
{
    private final UUID whoClicked;
    private final List<ItemStack> originalState;
    private final List<ItemStack> newState;
    private final Instant when;
    private final Location location;

    public ContainerInteraction(final Player player, final Container originalState, final Container newState)
    {
        this.whoClicked = player.getUniqueId();
        this.originalState = Collections.unmodifiableList(Arrays.asList(originalState.getInventory()
                                                                                     .getContents()));
        this.newState = Collections.unmodifiableList(Arrays.asList(newState.getInventory()
                                                                           .getContents()));
        this.location = originalState.getLocation();
        this.when = Instant.now();
    }

    @Override
    public @NotNull UUID getWhoClicked()
    {
        return whoClicked;
    }

    @Override
    public @NotNull List<ItemStack> getOriginalState()
    {
        return originalState;
    }

    @Override
    public @NotNull List<ItemStack> getNewState()
    {
        return newState;
    }

    @Override
    public @NotNull Instant getWhen()
    {
        return when;
    }

    @Override
    public @NotNull Location getLocation()
    {
        return location;
    }
}
