package me.totalfreedom.provider;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;

public class ContextProvider
{
    public Object fromString(String string)
    {
        return Stream.of(toBoolean(string),
                        toDouble(string),
                        toInt(string),
                        toLong(string),
                        toFloat(string),
                        toPlayer(string),
                        toWorld(string),
                        toLocation(string),
                        toCommandSender(string),
                        toComponent(string))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(string);
    }

    private @Nullable Boolean toBoolean(String string)
    {
        try
        {
            return Boolean.parseBoolean(string);
        } catch (Exception e)
        {
            return null;
        }
    }

    private @Nullable Double toDouble(String string)
    {
        try
        {
            return Double.parseDouble(string);
        } catch (Exception e)
        {
            return null;
        }
    }

    private @Nullable Integer toInt(String string)
    {
        try
        {
            return Integer.parseInt(string);
        } catch (Exception e)
        {
            return null;
        }
    }

    private @Nullable Long toLong(String string)
    {
        try
        {
            return Long.parseLong(string);
        } catch (Exception e)
        {
            return null;
        }
    }

    private @Nullable Float toFloat(String string)
    {
        try
        {
            return Float.parseFloat(string);
        } catch (Exception e)
        {
            return null;
        }
    }

    private @Nullable Player toPlayer(String string)
    {
        return Bukkit.getPlayer(string);
    }

    private @Nullable CommandSender toCommandSender(String string)
    {
        if (toPlayer(string) == null) return null;

        return toPlayer(string);
    }

    private @Nullable World toWorld(String string)
    {
        return Bukkit.getWorld(string);
    }

    private @Nullable Location toLocation(String string)
    {
        String[] split = string.split(",");
        if (split.length != 4 || toWorld(split[0]) == null) return null;
        if (toDouble(split[1]) == null
                || toDouble(split[2]) == null
                || toDouble(split[3]) == null) return null;

        return new Location(toWorld(split[0]), toDouble(split[1]), toDouble(split[2]), toDouble(split[3]));
    }

    private @Nullable Component toComponent(String string)
    {
        return Component.text(string);
    }
}
