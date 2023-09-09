/*
 * This file is part of FreedomNetworkSuite - https://github.com/SimplexDevelopment/FreedomNetworkSuite
 * Copyright (C) 2023 Simplex Development and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.patchwork.provider;

import fns.patchwork.base.Patchwork;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is a holder class for {@link Executor} objects that are used to delegate runnable tasks to the Bukkit Scheduler.
 * This class is here for both convenience purposes, and also for the sake of providing easy access to executors for
 * {@link CompletableFuture} invocations.
 */
public class ExecutorProvider
{
    /**
     * An executor which runs tasks synchronously.
     */
    private final Executor syncExecutor;
    /**
     * An executor which runs tasks asynchronously.
     */
    private final Executor asyncExecutor;

    /**
     * Creates a new {@link ExecutorProvider} instance.
     */
    public ExecutorProvider(final Patchwork patchwork)
    {
        syncExecutor = r -> Bukkit.getScheduler()
                                  .runTask(patchwork, r);
        asyncExecutor = r -> Bukkit.getScheduler()
                                   .runTaskAsynchronously(patchwork, r);
    }

    /**
     * Creates a new {@link Executor} that is capable of executing a runnable one singular time, synchronously.
     *
     * @param plugin The plugin to run the task for.
     * @return A new {@link Executor} instance.
     */
    public Executor singleExecutor(final JavaPlugin plugin)
    {
        return r -> Bukkit.getScheduler()
                          .runTask(plugin, r);
    }

    /**
     * Creates a new {@link Executor} that is capable of executing a runnable one singular time, synchronously. This
     * Executor will wait for the supplied delay before executing the runnable.
     *
     * @param plugin The plugin to run the task for.
     * @param delay  The delay to wait before executing the runnable.
     * @return A new {@link Executor} instance.
     */
    public Executor delayedExecutor(final JavaPlugin plugin, final long delay)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskLater(plugin, r, delay);
    }

    /**
     * Creates a new {@link Executor} tthat is capable of executing a runnable on a periodic basis, synchronously. This
     * executor can also be supplied a delay to indicate it should wait the specified amount of time before executing
     * the runnable for the first time.
     *
     * @param plugin       The plugin to run the task for.
     * @param initialDelay The delay to wait before executing the runnable for the first time.
     * @param period       The period to wait between each execution of the runnable.
     * @return A new {@link Executor} instance.
     */
    public Executor periodicExecutor(final JavaPlugin plugin, final long initialDelay, final long period)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskTimer(plugin, r, initialDelay, period);
    }

    /**
     * Creates a new {@link Executor} that is capable of executing a runnable one singular time, asynchronously.
     *
     * @param plugin The plugin to run the task for.
     * @return A new {@link Executor} instance.
     */
    public Executor asynchronousSingleExecutor(final JavaPlugin plugin)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskAsynchronously(plugin, r);
    }

    /**
     * Creates a new {@link Executor} that is capable of executing a runnable one singular time, asynchronously. This
     * Executor will wait for the supplied delay before executing the runnable.
     *
     * @param plugin The plugin to run the task for.
     * @param delay  The delay to wait before executing the runnable.
     * @return A new {@link Executor} instance.
     */
    public Executor asynchronousDelayedExecutor(final JavaPlugin plugin, final long delay)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskLaterAsynchronously(plugin, r, delay);
    }

    /**
     * Creates a new {@link Executor} tthat is capable of executing a runnable on a periodic basis, asynchronously. This
     * executor can also be supplied a delay to indicate it should wait the specified amount of time before executing
     * the runnable for the first time.
     *
     * @param plugin The plugin to run the task for.
     * @param delay  The delay to wait before executing the runnable for the first time.
     * @param period The period to wait between each execution of the runnable.
     * @return A new {@link Executor} instance.
     */
    public Executor asynchronousPeriodicExecutor(final JavaPlugin plugin, final long delay, final long period)
    {
        return r -> Bukkit.getScheduler()
                          .runTaskTimerAsynchronously(plugin, r, delay, period);
    }

    /**
     * Gets the synchronous executor instance. This is a convenience for {@link CompletableFuture} invocations, when
     * defining a custom executor for the {@link CompletableFuture}.
     *
     * @return The synchronous executor instance.
     */
    public Executor getSync()
    {
        return syncExecutor;
    }

    /**
     * Gets the asynchronous executor instance. This is a convenience for {@link CompletableFuture} invocations, when
     * defining a custom executor for the {@link CompletableFuture}.
     *
     * @return The asynchronous executor instance.
     */
    public Executor getAsync()
    {
        return asyncExecutor;
    }
}
