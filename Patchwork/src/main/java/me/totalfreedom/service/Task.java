package me.totalfreedom.service;

import me.totalfreedom.utils.DurationTools;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;

public abstract class Task extends BukkitRunnable
{
    private final String name;
    private long delay;
    private long interval;

    protected Task(final String name)
    {
        this(name, -1L, -1L);
    }

    protected Task(final String name, final long delay, final long interval)
    {
        this.name = name;
        this.delay = delay;
        this.interval = interval;
    }

    protected Task(final String name, final long delay)
    {
        this(name, delay, -1L);
    }

    protected Task(final String name, final Duration delay)
    {
        this(name, DurationTools.getTickedSeconds(delay), -1L);
    }

    protected Task(final String name, final Duration delay, final Duration interval)
    {
        this(name, DurationTools.getTickedSeconds(delay), DurationTools.getTickedSeconds(interval));
    }

    protected Task(final String name, final long delay, final Duration interval)
    {
        this(name, delay, DurationTools.getTickedSeconds(interval));
    }

    public boolean isRunning()
    {
        return !isCancelled();
    }

    public String getName()
    {
        return name;
    }

    public boolean isRepeating()
    {
        return interval != -1L;
    }

    public void setRepeating(final long interval)
    {
        this.interval = interval;
    }

    public boolean isDelayed()
    {
        return this.delay != -1;
    }

    public void setDelayed(final long delay)
    {
        this.delay = delay;
    }

    public long getInterval()
    {
        return interval;
    }

    public long getDelay()
    {
        return delay;
    }
}
