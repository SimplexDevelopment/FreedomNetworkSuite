package fns.patchwork.api;

import fns.patchwork.provider.ContextProvider;
import java.util.function.Function;
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

/**
 * Represents an object context. This class is a simple generic type wrapper that can be used to ensure data types. This
 * class is also used to provide a simple way to map data types.
 *
 * @param <T> The type of the context.
 * @see ContextProvider
 */
@FunctionalInterface
public interface Context<T>
{
    /**
     * Maps the context to another context.
     *
     * @param mapper The mapper function.
     * @param <S>    The type of the mapped context.
     * @return The mapped context.
     */
    default <S> Context<S> map(@NotNull final Function<T, S> mapper)
    {
        return () -> mapper.apply(get());
    }

    /**
     * Gets the context.
     *
     * @return The context.
     */
    T get();

    /**
     * Gets the context as a string.
     *
     * @return The context as a string.
     */
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

    /**
     * Gets the context as a boolean.
     *
     * @return The context as a boolean.
     */
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

    /**
     * @return The context as a {@link Double}.
     */
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

    /**
     * @return The context as a {@link Integer}.
     */
    default @Nullable Integer asInt()
    {
        if (get() instanceof Integer integer)
        {
            return integer;
        } else
        {
            return null;
        }
    }

    /**
     * @return The context as a {@link Byte}.
     */
    default @Nullable Long asLong()
    {
        if (get() instanceof Long longg)
        {
            return longg;
        } else
        {
            return null;
        }
    }

    /**
     * @return The context as a {@link Float}.
     */
    default @Nullable Float asFloat()
    {
        if (get() instanceof Float floatt)
        {
            return floatt;
        } else
        {
            return null;
        }
    }

    /**
     * @return The context as a {@link Player}.
     */
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

    /**
     * @return The context as a {@link CommandSender}.
     */
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

    /**
     * This is the same as calling {@link #get()} and then calling {@link Object#toString()} on the result.
     *
     * @return The context as a {@link String} literal.
     */
    default @NotNull String literal()
    {
        return get().toString();
    }

    /**
     * @return The context as a {@link World}.
     */
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

    /**
     * @return The context as a {@link Location}.
     */
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

    /**
     * @return The context as a {@link LivingEntity}.
     */
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

    /**
     * @return The context as a {@link Component}.
     */
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

    /**
     * @return The context as a {@link Projectile}.
     */
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

    /**
     * @return The context as an {@link Action}.
     */
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

    /**
     * Gets the context as a custom class. This will cast the object to the class if it is an instance of it.
     * <br>
     * Typically, Context objects are useful when used to collect unknown data and then cast it to a known type.
     * <br>
     * In the case where we know what the data should be but the compiler or the runtime does not, the object is wrapped
     * in a Context which then exposes multiple methods to get the data as one of the known types.
     * <p>
     * For example, if we have a Context&lt;Object&gt; and we already know that the wrapped object should be of type X,
     * we can use <code>X.class</code> on this method to retrieve the actual object. That is, to say, if there is not
     * already a predefined method to get the object as the type we want.
     *
     * @param clazz
     * @param <U>
     * @return
     */
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
