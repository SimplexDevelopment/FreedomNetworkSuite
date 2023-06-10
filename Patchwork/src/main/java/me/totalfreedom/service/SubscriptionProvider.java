package me.totalfreedom.service;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Provides static methods for creating {@link ServiceSubscription} and {@link TaskSubscription} objects.
 */
public final class SubscriptionProvider
{
    /**
     * Prevents instantiation of this class.
     */
    private SubscriptionProvider()
    {
        throw new AssertionError();
    }

    /**
     * Creates a new {@link ServiceSubscription} object that will run the given {@link Service} object on the main
     * thread a single time.
     *
     * @param plugin  The plugin that owns the service.
     * @param service The service to run.
     * @param <S>     Type inference to maintain the service type.
     * @return The new {@link ServiceSubscription} object.
     */
    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> syncService(@NotNull final JavaPlugin plugin,
                                                                               @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service);
    }

    /**
     * Creates a new {@link ServiceSubscription} object that will run the given {@link Service} object on the main
     * thread at the given interval.
     *
     * @param plugin   The plugin that owns the service.
     * @param interval The interval to run the service at.
     * @param service  The service to run.
     * @param <S>      Type inference to maintain the service type.
     * @return The new {@link ServiceSubscription} object.
     */
    @NotNull
    @Contract(value = "_,_,_ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> syncService(@NotNull final JavaPlugin plugin,
                                                                               final long interval,
                                                                               @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service, interval);
    }

    /**
     * Creates a new {@link ServiceSubscription} object that will run the given {@link Service} object on the default
     * tick interval, which is a single tick. This method will create an asynchronous service.
     *
     * @param plugin  The plugin that owns the service.
     * @param service The service to run.
     * @param <S>     Type inference to maintain the service type.
     * @return The new {@link ServiceSubscription} object.
     */
    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> asyncService(@NotNull final JavaPlugin plugin,
                                                                                @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service, true);
    }

    /**
     * Creates a new {@link ServiceSubscription} object that will run the given {@link Service} object on the given
     * interval. This method will create an asynchronous service.
     *
     * @param plugin   The plugin that owns the service.
     * @param interval The interval to run the service at.
     * @param service  The service to run.
     * @param <S>      Type inference to maintain the service type.
     * @return The new {@link ServiceSubscription} object.
     */
    @NotNull
    @Contract(value = "_,_,_ -> new", pure = false)
    public static final <S extends Service> ServiceSubscription<S> asyncService(@NotNull final JavaPlugin plugin,
                                                                                final long interval,
                                                                                @NotNull final S service)
    {
        return new ServiceSubscription<>(plugin, service, interval, true);
    }

    /**
     * Creates a new {@link TaskSubscription} object that will run the given {@link Task} object synchronously on the
     * main thread.
     *
     * @param plugin The plugin that owns the task.
     * @param task   The task to run.
     * @param <T>    Type inference to maintain the task type.
     * @return The new {@link TaskSubscription} object.
     */
    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <T extends Task> TaskSubscription<T> runSyncTask(@NotNull final JavaPlugin plugin,
                                                                         @NotNull final T task)
    {
        return new TaskSubscription<>(plugin, task, false);
    }

    /**
     * Creates a new {@link TaskSubscription} object that will run the given {@link Task} object asynchronously on the
     * main thread.
     *
     * @param plugin The plugin that owns the task.
     * @param task   The task to run.
     * @param <T>    Type inference to maintain the task type.
     * @return The new {@link TaskSubscription} object.
     */
    @NotNull
    @Contract(value = "_, _ -> new", pure = false)
    public static final <T extends Task> TaskSubscription<T> runAsyncTask(@NotNull final JavaPlugin plugin,
                                                                          @NotNull final T task)
    {
        return new TaskSubscription<>(plugin, task, true);
    }
}
