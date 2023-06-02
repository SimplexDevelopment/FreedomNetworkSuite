package me.totalfreedom.provider;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public class ContextProvider
{
    public <T> T fromString(final String string, final Class<T> clazz)
    {
        return Stream.of(toBoolean(string),
                         toDouble(string),
                         toInt(string),
                         toLong(string),
                         toFloat(string),
                         toMaterial(string),
                         toPlayer(string),
                         toWorld(string),
                         toLocation(string),
                         toCommandSender(string),
                         toComponent(string))
                     .filter(Objects::nonNull)
                     .findFirst()
                     .map(clazz::cast)
                     .orElse(null);
    }

    private @Nullable Boolean toBoolean(final String string)
    {
        try
        {
            return Boolean.parseBoolean(string);
        }
        catch (Exception ignored)
        {
            return null;
        }
    }

    private @Nullable Double toDouble(final String string)
    {
        try
        {
            return Double.parseDouble(string);
        }
        catch (Exception ignored)
        {
            return null;
        }
    }

    private @Nullable Integer toInt(final String string)
    {
        try
        {
            return Integer.parseInt(string);
        }
        catch (Exception ignored)
        {
            return null;
        }
    }

    private @Nullable Long toLong(final String string)
    {
        try
        {
            return Long.parseLong(string);
        }
        catch (Exception ignored)
        {
            return null;
        }
    }

    private @Nullable Float toFloat(final String string)
    {
        try
        {
            return Float.parseFloat(string);
        }
        catch (Exception ignored)
        {
            return null;
        }
    }

    private @Nullable Material toMaterial(final String string)
    {
        return Material.matchMaterial(string);
    }

    private @Nullable Player toPlayer(final String string)
    {
        return Bukkit.getPlayer(string);
    }

    private @Nullable World toWorld(final String string)
    {
        return Bukkit.getWorld(string);
    }

    /**
     * When using this method, the input string must be formatted as
     * <br>
     * <code>worldName,x,y,z</code>
     * <br>
     *
     * @param string The string to parse
     * @return A location object if xyz is valid
     */
    private @Nullable Location toLocation(final String string)
    {
        final String[] split = string.split(",");

        if (split.length != 4 || toWorld(split[0]) == null) return null;

        final double x = Double.parseDouble(split[1]);
        final double y = Double.parseDouble(split[2]);
        final double z = Double.parseDouble(split[3]);

        return new Location(toWorld(split[0]), x, y, z);
    }

    private @Nullable CommandSender toCommandSender(final String string)
    {
        if (toPlayer(string) == null) return null;

        return toPlayer(string);
    }

    private @NotNull Component toComponent(final String string)
    {
        return Component.text(string);
    }
}
