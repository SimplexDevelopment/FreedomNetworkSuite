package me.totalfreedom.service;

import me.totalfreedom.base.CommonsBase;
import org.bukkit.Bukkit;

import java.util.concurrent.Executor;

public class FreedomExecutor
{
    private final Executor syncExecutor;
    private final Executor asyncExecutor;

    public FreedomExecutor()
    {
        syncExecutor = r -> Bukkit.getScheduler().runTask(CommonsBase.getInstance(), r);
        asyncExecutor = r -> Bukkit.getScheduler().runTaskAsynchronously(CommonsBase.getInstance(), r);
    }

    public Executor getSync()
    {
        return syncExecutor;
    }

    public Executor getAsync()
    {
        return asyncExecutor;
    }

    public Executor scheduled(long delay, long period)
    {
        return r -> Bukkit.getScheduler()
                .runTaskTimer(
                        CommonsBase.getInstance(),
                        r,
                        delay,
                        period);
    }

    public Executor scheduledAsync(long delay, long period)
    {
        return r -> Bukkit.getScheduler()
                .runTaskTimerAsynchronously(
                        CommonsBase.getInstance(),
                        r,
                        delay,
                        period);
    }

    public void runSync(Task task)
    {
        getSync().execute(task);
    }

    public void runAsync(Task task)
    {
        getAsync().execute(task);
    }
}
