package me.totalfreedom.service;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

/**
 * Represents a subscription to a {@link Service}.
 * <p>
 * Subscriptions contain some information about the service itself and it's presence on the scheduler. For example,
 * {@link #getServiceId()} will return the ID of the task which was returned by the scheduler. Subscriptions also manage
 * the state of the service, using {@link #isActive()} to determine if the service is currently running.
 * <br>
 * <br>
 * The subscription itself provides type inference to safely store the actual service instance. This is useful for when
 * we need to access the service itself, without calling to the service directly.
 *
 * @param <T> The type of service this subscription is for.
 */
public final class ServiceSubscription<T extends Service>
{
    /**
     * The service this subscription is for.
     */
    private final T service;
    /**
     * Whether this is an asynchronous service.
     */
    private final boolean async;
    /**
     * The executor used to schedule the service.
     */
    private final Executor executor;
    /**
     * The ID of the service from the associated {@link BukkitTask} which was returned by the Scheduler.
     */
    private final int serviceId;

    /**
     * Whether the service is currently running.
     */
    private boolean isActive = false;

    /**
     * Creates a new subscription for the given service. By default, this method will mark this service as a synchronous
     * service. This will also initialize the default interval to a single tick.
     * <br>
     * If you are trying to create an asynchronous service, use
     * {@link #ServiceSubscription(JavaPlugin, Service, boolean)} instead.
     * <br>
     * If you would like to define a custom interval, use either {@link #ServiceSubscription(JavaPlugin, Service, long)}
     * or {@link #ServiceSubscription(JavaPlugin, Service, long, boolean)} (for asynchronous services).
     *
     * @param plugin  The plugin which owns the service.
     * @param service The service to subscribe to.
     */
    ServiceSubscription(@NotNull final JavaPlugin plugin, @NotNull final T service)
    {
        this(plugin, service, 1L, false);
    }

    /**
     * Creates a new subscription for the given service. This will initialize the default interval to a single tick.
     * <br>
     * If you would like to define a custom interval, use either {@link #ServiceSubscription(JavaPlugin, Service, long)}
     * or {@link #ServiceSubscription(JavaPlugin, Service, long, boolean)} (for asynchronous services).
     *
     * @param plugin  The plugin which owns the service.
     * @param service The service to subscribe to.
     * @param async   Whether the service should be scheduled asynchronously.
     */
    ServiceSubscription(@NotNull final JavaPlugin plugin, @NotNull final T service, final boolean async)
    {
        this(plugin, service, 1L, async);
    }

    /**
     * Creates a new subscription for the given service. By default, this will mark the service as synchronous. When
     * defining a custom interval, the interval should be less than 20L (the number of ticks in a second). For anything
     * that requires an interval greater than 1 second, use a {@link Task} instead.
     * <br>
     * If you are trying to create an asynchronous service, use
     * {@link #ServiceSubscription(JavaPlugin, Service, long, boolean)} instead.
     *
     * @param plugin   The plugin which owns the service.
     * @param service  The service to subscribe to.
     * @param interval The interval at which the service should be scheduled.
     */
    ServiceSubscription(@NotNull final JavaPlugin plugin, @NotNull final T service, final long interval)
    {
        this(plugin, service, interval, false);
    }

    /**
     * Creates a new subscription for the given service. When defining a custom interval, the interval should be less
     * than 20L (the number of ticks in a second). For anything that requires an interval greater than 1 second, use a
     * {@link Task} instead.
     *
     * @param plugin   The plugin which owns the service.
     * @param service  The service to subscribe to.
     * @param interval The interval at which the service should be scheduled.
     * @param async    Whether the service should be scheduled asynchronously.
     */
    ServiceSubscription(@NotNull final JavaPlugin plugin, @NotNull final T service,
                        final long interval, final boolean async)
    {
        this.service = service;
        this.async = async;

        final int[] tempId = new int[1];

        if (async)
        {
            this.executor = r ->
            {
                final BukkitTask task = Bukkit.getScheduler()
                                              .runTaskTimerAsynchronously(plugin, r, 0, interval);
                tempId[0] = task.getTaskId();
            };
        } else
        {
            this.executor = r ->
            {
                final BukkitTask task = Bukkit.getScheduler()
                                              .runTaskTimer(plugin, r, 0, interval);
                tempId[0] = task.getTaskId();
            };
        }

        this.serviceId = tempId[0];
    }

    /**
     * Starts the service.
     */
    public void start()
    {
        this.isActive = true;
        this.executor.execute(service::tick);
    }

    /**
     * Stops the service.
     */
    public void stop()
    {
        this.isActive = false;
        Bukkit.getScheduler()
              .cancelTask(this.getServiceId());
    }

    /**
     * Returns the ID of the service from the associated {@link BukkitTask} which was returned by the Scheduler.
     *
     * @return The ID of the service.
     */
    public int getServiceId()
    {
        return serviceId;
    }

    /**
     * @return The service this subscription is for.
     */
    @NotNull
    public T getService()
    {
        return service;
    }

    /**
     * @return Whether this is an asynchronous service.
     */
    public boolean isAsync()
    {
        return async;
    }

    /**
     * @return Whether the service is currently running.
     */
    public boolean isActive()
    {
        return isActive;
    }
}
