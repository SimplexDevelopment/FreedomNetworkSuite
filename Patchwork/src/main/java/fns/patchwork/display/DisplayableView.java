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

package fns.patchwork.display;

import fns.patchwork.kyori.PlainTextWrapper;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * A view of a {@link Displayable} inventory.
 * <p>
 * This class can be used to display two separate {@link Displayable} objects to the Player.
 */
public class DisplayableView extends InventoryView
{
    /**
     * The upper inventory involved in this transaction.
     */
    private final Displayable top;
    /**
     * The lower inventory involved in this transaction.
     */
    private final Displayable bottom;
    /**
     * The player viewing the inventories involved in this transaction.
     */
    private final Player player;
    /**
     * The type of inventory this transaction is for.
     */
    private final InventoryType type;
    /**
     * The title of the main inventory involved in this transaction. The main inventory should always be the top
     * inventory.
     */
    private String title;

    /**
     * Creates a new DisplayableView.
     *
     * @param player The player viewing the inventories involved in this transaction.
     * @param top    The upper inventory involved in this transaction.
     * @param bottom The lower inventory involved in this transaction.
     */
    public DisplayableView(final Player player, final Displayable top, final Displayable bottom)
    {
        this.player = player;
        this.top = top;
        this.bottom = bottom;
        this.type = InventoryType.CHEST;
        this.title = PlainTextWrapper.toPlainText(type.defaultTitle());
    }

    @Override
    public @NotNull Inventory getTopInventory()
    {
        return top;
    }

    @Override
    public @NotNull Inventory getBottomInventory()
    {
        return bottom;
    }

    @Override
    public @NotNull HumanEntity getPlayer()
    {
        return player;
    }

    @Override
    public @NotNull InventoryType getType()
    {
        return type;
    }

    @Override
    @Deprecated(forRemoval = true, since = "1.16")
    public @NotNull String getTitle()
    {
        return title;
    }

    @Override
    public void setTitle(final @NotNull String title)
    {
        this.title = title;
    }

    @Override
    public @NotNull String getOriginalTitle()
    {
        return PlainTextWrapper.toPlainText(type.defaultTitle());
    }
}
