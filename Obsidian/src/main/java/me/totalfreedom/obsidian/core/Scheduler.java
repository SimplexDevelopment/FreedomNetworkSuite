package me.totalfreedom.obsidian.core;

import java.util.concurrent.TimeUnit;

public interface Scheduler
{
    Disposable schedule(Runnable task);

    Disposable schedule(Runnable task, long delay, TimeUnit unit);

    Disposable schedulePeriodically(Runnable task, long initialDelay, long period, TimeUnit unit);

    void dispose();
}

