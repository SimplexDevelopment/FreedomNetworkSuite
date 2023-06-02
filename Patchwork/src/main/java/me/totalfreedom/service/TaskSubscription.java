package me.totalfreedom.service;

import me.totalfreedom.utils.Pair;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.Executor;

public final class TaskSubscription<T extends Task>
{
    private final T task;
    private final int taskId;
    private final boolean async;
    private final Executor executor;

    private boolean isActive = false;

    TaskSubscription(final JavaPlugin plugin, final T task, final boolean async)
    {
        this.task = task;
        this.async = async;

        final long delay = (task.isDelayed()
                            ? task.getDelay()
                            : 0);
        final long period = (task.isRepeating()
                             ? task.getInterval()
                             : 0);

        final Pair<Integer, Executor> integerExecutorPair = async
                                                            ? getAsync(plugin, delay, period)
                                                            : getSync(plugin, delay, period);

        this.executor = integerExecutorPair.value();
        this.taskId = integerExecutorPair.key();
    }

    private Pair<Integer, Executor> getAsync(final JavaPlugin plugin, final long delay, final long period)
    {
        final Executor executor1;
        final int[] tempId = new int[1];
        if (period != 0)
        {
            executor1 = r ->
            {
                final BukkitTask task1 = Bukkit.getScheduler()
                                               .runTaskTimerAsynchronously(plugin, r, delay, period);
                tempId[0] = task1.getTaskId();
            };
        } else if (delay != 0)
        {
            executor1 = r ->
            {
                final BukkitTask task1 = Bukkit.getScheduler()
                                               .runTaskLaterAsynchronously(plugin, r, delay);
                tempId[0] = task1.getTaskId();
            };
        } else
        {
            executor1 = r ->
            {
                final BukkitTask task1 = Bukkit.getScheduler()
                                               .runTaskAsynchronously(plugin, r);
                tempId[0] = task1.getTaskId();
            };
        }

        return new Pair<>(tempId[0], executor1);
    }

    private Pair<Integer, Executor> getSync(final JavaPlugin plugin, final long delay, final long period)
    {
        final Executor executor1;
        final int[] tempId = new int[1];

        if (period != 0)
        {
            executor1 = r ->
            {
                final BukkitTask task1 = Bukkit.getScheduler()
                                               .runTaskTimer(plugin, r, delay, period);
                tempId[0] = task1.getTaskId();
            };
        } else if (delay != 0)
        {
            executor1 = r ->
            {
                final BukkitTask task1 = Bukkit.getScheduler()
                                               .runTaskLater(plugin, r, delay);
                tempId[0] = task1.getTaskId();
            };
        } else
        {
            executor1 = r ->
            {
                final BukkitTask task1 = Bukkit.getScheduler()
                                               .runTask(plugin, r);
                tempId[0] = task1.getTaskId();
            };
        }

        return new Pair<>(tempId[0], executor1);
    }

    public void start()
    {
        this.isActive = true;
        executor.execute(task);
    }

    public void stop()
    {
        this.isActive = false;
        Bukkit.getScheduler()
              .cancelTask(this.getTaskId());
    }

    public int getTaskId()
    {
        return taskId;
    }

    public T getTask()
    {
        return task;
    }

    public boolean isAsync()
    {
        return async;
    }

    public Executor getExecutor()
    {
        return executor;
    }

    public boolean isActive()
    {
        return isActive;
    }
}
