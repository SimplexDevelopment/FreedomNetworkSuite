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

package fns.patchwork.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is used to provide context to subcommand methods. This class is used by the BukkitDelegate to parse
 * arguments for subcommands. The following types are supported:
 * <ul>
 *     <li>Boolean</li>
 *     <li>Double</li>
 *     <li>Integer</li>
 *     <li>Long</li>
 *     <li>Float</li>
 *     <li>Material</li>
 *     <li>Player</li>
 *     <li>World</li>
 *     <li>Location</li>
 *     <li>CommandSender</li>
 *     <li>Component</li>
 * </ul>
 * All of these types can be parsed from a String input. If the String cannot be parsed into any of
 * these types, then null will be returned.
 *
 * @see #fromString(String, Class)
 */
public class ContextProvider
{
    public <T> T fromString(final String string, final Class<T> clazz)
    {
        return Stream.of(toBoolean(string, clazz),
                         toLong(string, clazz),
                         toDouble(string, clazz),
                         toInt(string, clazz),
                         toFloat(string, clazz),
                         toMaterial(string, clazz),
                         toPlayer(string, clazz),
                         toOfflinePlayer(string, clazz),
                         toWorld(string, clazz),
                         toLocation(string, clazz),
                         toComponent(string, clazz))
                     .filter(Objects::nonNull)
                     .findFirst()
                     .map(clazz::cast)
                     .orElse(null);
    }

    private @Nullable Boolean toBoolean(final String string, final Class<?> clazz)
    {
        if (clazz != Boolean.class)
            return null;

        // Previously we used Boolean#parseBoolean, but that will always return a value and does not throw
        // an exception. This means that if the string is not "true" or "false", it will return false.
        if (string.equalsIgnoreCase("true"))
            return true;
        if (string.equalsIgnoreCase("false"))
            return false;

        return null;
    }

    private @Nullable Double toDouble(final String string, final Class<?> clazz)
    {
        if (clazz != Double.class)
            return null;

        try
        {
            return Double.parseDouble(string);
        }
        catch (NumberFormatException ignored)
        {
            return null;
        }
    }

    private @Nullable Integer toInt(final String string, final Class<?> clazz)
    {
        if (clazz != Integer.class)
            return null;

        try
        {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException ignored)
        {
            return null;
        }
    }

    private @Nullable Long toLong(final String string, final Class<?> clazz)
    {
        if (clazz != Long.class || !string.endsWith("L"))
            return null;

        try
        {
            return Long.parseLong(string);
        }
        catch (NumberFormatException ignored)
        {
            return null;
        }
    }

    private @Nullable Float toFloat(final String string, final Class<?> clazz)
    {
        if (clazz != Float.class || !string.endsWith("F"))
            return null;

        try
        {
            return Float.parseFloat(string);
        }
        catch (NumberFormatException ignored)
        {
            return null;
        }
    }

    private @Nullable Material toMaterial(final String string, final Class<?> clazz)
    {
        if (clazz != Material.class)
            return null;
        return Material.matchMaterial(string);
    }

    private @Nullable Player toPlayer(final String string, final Class<?> clazz)
    {
        if (clazz != Player.class)
            return null;
        return Bukkit.getPlayer(string);
    }

    private OfflinePlayer toOfflinePlayer(final String string, final Class<?> clazz)
    {
        if (clazz != OfflinePlayer.class)
        {
            return null;
        }

        return Bukkit.getOfflinePlayer(string);
    }

    private @Nullable World toWorld(final String string, final Class<?> clazz)
    {
        if (clazz != World.class)
            return null;
        return Bukkit.getWorld(string);
    }

    /**
     * When using this method, the next four arguments must be world, x, y, z.
     * The world must be a valid world name, and x, y, and z must be valid doubles.
     * If any of these are invalid, this will return null.
     *
     * @param string The string to parse
     * @return A location object if xyz is valid
     */
    private @Nullable Location toLocation(final String string, final Class<?> clazz)
    {
        if (clazz != Location.class)
            return null;

        final String[] split = string.split(" ");

        if (split.length != 4 || toWorld(split[0], World.class) == null)
            return null;

        try
        {
            final double x = Double.parseDouble(split[1]);
            final double y = Double.parseDouble(split[2]);
            final double z = Double.parseDouble(split[3]);

            return new Location(toWorld(split[0], World.class), x, y, z);
        }
        catch (NumberFormatException ex)
        {
            return null;
        }
    }

    private @Nullable Component toComponent(final String string, final Class<?> clazz)
    {
        if (clazz != Component.class)
            return null;
        return Component.text(string);
    }

    public @NotNull <T> List<@Nullable T> getList(final List<String> resolvable, final Class<T> clazz)
    {
        final List<T> resolved = new ArrayList<>();
        for (final String entry : resolvable)
        {
            resolved.add(this.fromString(entry, clazz));
        }
        return resolved;
    }
}
