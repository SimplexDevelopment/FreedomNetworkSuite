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

package fns.patchwork.display;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a menu that can be opened by a player.
 */
public abstract class AbstractMenu
{
    /**
     * A map of all menus by their UUID.
     */
    private static final Map<UUID, AbstractMenu> invByUUID = new HashMap<>();
    /**
     * A map of all open menus by the player's UUID.
     */
    private static final Map<UUID, UUID> openInvs = new HashMap<>();
    private final Map<Integer, ClickAction> actionMap;
    /**
     * The displayable that represents this menu.
     */
    private final Displayable displayable;
    /**
     * The UUID of the displayable that represents this menu.
     */
    private final UUID displayableUUID;

    /**
     * Creates a new menu with the specified size.
     *
     * @param size The size of the menu.
     */
    protected AbstractMenu(final int size)
    {
        actionMap = new HashMap<>();
        this.displayable = new Displayable(size);
        this.displayableUUID = UUID.randomUUID();

        invByUUID.put(getDisplayableUUID(), this);
    }

    /**
     * @return A map of all menus by their UUID.
     */
    public static Map<UUID, AbstractMenu> getInvByUUID()
    {
        return invByUUID;
    }

    /**
     * @return A map of all open menus by the player's UUID.
     */
    public static Map<UUID, UUID> getOpenInvs()
    {
        return openInvs;
    }

    /**
     * @return The displayable UUID of this menu.
     */
    public UUID getDisplayableUUID()
    {
        return displayableUUID;
    }

    /**
     * Sets the item at the specified slot.
     *
     * @param slot  The slot to set the item at.
     * @param stack The item to set.
     */
    public void setItem(final int slot, final @NotNull ItemStack stack)
    {
        setItem(slot, stack, null);
    }

    /**
     * Sets the item at the specified slot.
     *
     * @param slot   The slot to set the item at.
     * @param stack  The item to set.
     * @param action The action to perform when the item is clicked.
     */
    public void setItem(final int slot, final @NotNull ItemStack stack, final @Nullable ClickAction action)
    {
        getDisplayable().setItem(slot, stack);
        if (action != null)
        {
            actionMap.put(slot, action);
        }
    }

    /**
     * @return The displayable that represents this menu.
     */
    public Displayable getDisplayable()
    {
        return displayable;
    }

    /**
     * Opens this menu for the specified player.
     *
     * @param player The player to open the menu for.
     */
    public void open(final @NotNull Player player)
    {
        player.openInventory(getDisplayable());
        openInvs.put(player.getUniqueId(), getDisplayableUUID());
    }

    /**
     * Deletes this menu.
     */
    public void delete()
    {
        Bukkit.getOnlinePlayers()
              .forEach(player ->
              {
                  if (openInvs.get(player.getUniqueId())
                              .equals(getDisplayableUUID()))
                  {
                      close(player);
                  }
              });

        invByUUID.remove(getDisplayableUUID());
    }

    /**
     * Closes this menu for the specified player.
     *
     * @param player The player to close the menu for.
     */
    public void close(final @NotNull Player player)
    {
        player.closeInventory();
        openInvs.remove(player.getUniqueId());
    }

    /**
     * @return A map of all actions by their slot.
     */
    public Map<Integer, ClickAction> getActions()
    {
        return actionMap;
    }

    /**
     * Creates a new item with the specified material and name.
     *
     * @param material The material of the item.
     * @param name     The name of the item.
     * @return The created item.
     */
    public ItemStack newItem(final @NotNull Material material, final @NotNull Component name)
    {
        return this.newItem(material, name, new Component[0]);
    }

    /**
     * Creates a new item with the specified material, name, and lore.
     *
     * @param material The material of the item.
     * @param name     The name of the item.
     * @param lore     The lore of the item.
     * @return The created item.
     */
    public ItemStack newItem(final @NotNull Material material, final @NotNull Component name,
                             final @NotNull Component... lore)
    {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        if (meta == null)
        {
            return item;
        }
        meta.displayName(name);
        final List<Component> metaLore = Arrays.asList(lore);
        meta.lore(metaLore);
        item.setItemMeta(meta);
        return item;
    }
}
