package me.totalfreedom.service;

import me.totalfreedom.base.CommonsBase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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

    public Executor singleExecutor(final JavaPlugin plugin)
    {
        return r -> Bukkit.getScheduler()
                          .runTask(plugin, r);
    }

    public Executor delayedExecutor(final JavaPlugin plugin, final long delay)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskLater(plugin, r, delay);
    }

    public Executor periodicExecutor(final JavaPlugin plugin, final long delay, final long period)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskTimer(plugin, r, delay, period);
    }

    public Executor asynchronousSingleExecutor(final JavaPlugin plugin)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskAsynchronously(plugin, r);
    }

    public Executor asynchronousDelayedExecutor(final JavaPlugin plugin, final long delay)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskLaterAsynchronously(plugin, r, delay);
    }

    public Executor asynchronousPeriodicExecutor(final JavaPlugin plugin, final long delay, final long period)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskTimerAsynchronously(plugin, r, delay, period);
    }

    public Executor getSync()
    {
        return syncExecutor;
    }

    public Executor getAsync()
    {
        return asyncExecutor;
    }
}
