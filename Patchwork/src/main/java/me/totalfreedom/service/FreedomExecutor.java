package me.totalfreedom.service;

import me.totalfreedom.base.CommonsBase;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class FreedomExecutor
{
    private final Executor syncExecutor;
    private final Executor asyncExecutor;

    public FreedomExecutor()
    {
        syncExecutor = r -> Bukkit.getScheduler()
                                  .runTask(CommonsBase.getInstance(), r);
        asyncExecutor = r -> Bukkit.getScheduler()
                                   .runTaskAsynchronously(CommonsBase.getInstance(), r);
    }

    public Executor scheduled(final long delay, final long period)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskTimer(
                              CommonsBase.getInstance(),
                              r,
                              delay,
                              period);
    }

    public Executor scheduledAsync(final long delay, final long period)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskTimerAsynchronously(
                              CommonsBase.getInstance(),
                              r,
                              delay,
                              period);
    }

    public void runSync(@NotNull final Task task)
    {
        getSync().execute(task);
    }

    public Executor getSync()
    {
        return syncExecutor;
    }

    public void runAsync(@NotNull final Task task)
    {
        getAsync().execute(task);
    }

    public Executor getAsync()
    {
        return asyncExecutor;
    }
}
