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

import java.util.function.Function;

@FunctionalInterface
public interface Context<T>
{
    T get();

    default <S> Context<S> map(@NotNull final Function<T, S> mapper)
    {
        return () -> mapper.apply(get());
    }

    default @Nullable String asString()
    {
        if (get() instanceof String string)
        {
            return string;
        } else
        {
            return null;
        }
    }

    default @Nullable Boolean asBoolean()
    {
        if (get() instanceof Boolean bool)
        {
            return bool;
        } else
        {
            return null;
        }
    }

    default @Nullable Double asDouble()
    {
        if (get() instanceof Double doub)
        {
            return doub;
        } else
        {
            return null;
        }
    }

    default @Nullable Integer asInt() {
        if (get() instanceof Integer integer) {
            return integer;
        } else {
            return null;
        }
    }

    default @Nullable Long asLong() {
        if (get() instanceof Long longg) {
            return longg;
        } else {
            return null;
        }
    }

    default @Nullable Float asFloat() {
        if (get() instanceof Float floatt) {
            return floatt;
        } else {
            return null;
        }
    }

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

    default @NotNull String literal()
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
