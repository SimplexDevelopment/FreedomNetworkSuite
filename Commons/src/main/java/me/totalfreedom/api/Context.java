package me.totalfreedom.api;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.block.Action;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface Context<T>
{
    T get();

    default @Nullable Player asPlayer()
    {
        if (get() instanceof Player player)
        {
            return player;
        } else
        {
            return null;
        }
    }

    default @Nullable CommandSender asCommandSender()
    {
        if (get() instanceof CommandSender commandSender)
        {
            return commandSender;
        } else
        {
            return null;
        }
    }

    default @NotNull String asLiteral()
    {
        return get().toString();
    }

    default @Nullable World asWorld()
    {
        if (get() instanceof World world)
        {
            return world;
        } else
        {
            return null;
        }
    }

    default @Nullable Location asLocation()
    {
        if (get() instanceof Location location)
        {
            return location;
        } else
        {
            return null;
        }
    }

    default @Nullable LivingEntity asLivingEntity()
    {
        if (get() instanceof LivingEntity livingEntity)
        {
            return livingEntity;
        } else
        {
            return null;
        }
    }

    default @Nullable Component asComponent()
    {
        if (get() instanceof Component component)
        {
            return component;
        } else
        {
            return null;
        }
    }

    default @Nullable Projectile asProjectile()
    {
        if (get() instanceof Projectile projectile)
        {
            return projectile;
        } else
        {
            return null;
        }
    }

    default @Nullable Action asAction()
    {
        if (get() instanceof Action action)
        {
            return action;
        } else
        {
            return null;
        }
    }

    default <U> @Nullable U asCustom(Class<U> clazz)
    {
        if (clazz.isInstance(get()))
        {
            return clazz.cast(get());
        } else
        {
            return null;
        }
    }
}
