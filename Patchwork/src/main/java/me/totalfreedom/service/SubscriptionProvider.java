package me.totalfreedom.service;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class SubscriptionProvider
{
    private SubscriptionProvider()
    {
        throw new AssertionError();
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> syncService(@NotNull final JavaPlugin plugin,
        @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service);
    }

    @NotNull
    @Contract(value = "_,_,_ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> syncService(@NotNull final JavaPlugin plugin,
        final long interval,
        @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service, interval);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> asyncService(@NotNull final JavaPlugin plugin,
        @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service, true);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <T extends Task> TaskSubscription<T> runSyncTask(@NotNull final JavaPlugin plugin,
        @NotNull final T task)
    {
        return new TaskSubscription<>(plugin, task, false);
    }

    @NotNull
    @Contract(value = "_,_,_ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> asyncService(@NotNull final JavaPlugin plugin,
        final long interval,
        @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service, interval, true);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <T extends Task> TaskSubscription<T> runAsyncTask(@NotNull final JavaPlugin plugin,
        @NotNull final T task)
    {
        return new TaskSubscription<>(plugin, task, true);
    }
}
