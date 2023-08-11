/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
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

package fns.patchwork.service;

import fns.patchwork.utils.DurationTools;
import java.time.Duration;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Represents a task that can be run asynchronously or synchronously.
 */
public abstract class Task extends BukkitRunnable
{
    /**
     * The name of the task.
     */
    private final String name;
    /**
     * The delay of the task.
     */
    private final long delay;
    /**
     * The interval of the task.
     */
    private final long interval;

    /**
     * Creates a new task with the given name. This will initialize a task with no initail delay and no interval.
     *
     * @param name The name of the task.
     */
    protected Task(final String name)
    {
        this(name, -1L, -1L);
    }

    /**
     * Creates a new task with the given name, delay, and interval.
     * <br>
     * It's important to note that the delay and interval are in ticks. One tick is equal to 1/20th of a second, which
     * means there are 20 ticks are in one second. If your interval is intended to be anything less than 20 ticks, you
     * should use a {@link Service} instead.
     *
     * @param name     The name of the task.
     * @param delay    The delay of the task.
     * @param interval The interval of the task.
     */
    protected Task(final String name, final long delay, final long interval)
    {
        this.name = name;
        this.delay = delay;
        this.interval = interval;
    }

    /**
     * Creates a new task with the given name and delay. This will intialize a single execute task with an initial delay
     * before execution.
     *
     * @param name  The name of the task.
     * @param delay How long the task should wait before executing.
     */
    protected Task(final String name, final long delay)
    {
        this(name, delay, -1L);
    }

    /**
     * Creates a new task with the given name and delay. This is the same as longs, except that here, we naturally
     * support durations which are automatically converted to ticks for you. This means that using
     * {@link Duration#ofSeconds(long)} will work as expected.
     *
     * @param name  The name of the task.
     * @param delay How long the task should wait before executing.
     */
    protected Task(final String name, final Duration delay)
    {
        this(name, DurationTools.getTicks(delay), -1L);
    }

    /**
     * Creates a new task with the given name, delay, and interval. This is the same as longs, except that here, we
     * naturally support durations which are automatically converted to ticks for you. This means that using
     * {@link Duration#ofSeconds(long)} will work as expected.
     *
     * @param name     The name of the task.
     * @param delay    How long the task should wait before executing.
     * @param interval How long the task should wait between executions.
     */
    protected Task(final String name, final Duration delay, final Duration interval)
    {
        this(name, DurationTools.getTicks(delay), DurationTools.getTicks(interval));
    }

    /**
     * Creates a new task with the given name, delay, and interval. This method is a convenience method to use a
     * {@link Duration} for the interval, while also being able to specify the delay as -1L so the task does not have an
     * initial delay before execution.
     *
     * @param name     The name of the task.
     * @param delay    The delay of the task.
     * @param interval The interval of the task.
     */
    protected Task(final String name, final long delay, final Duration interval)
    {
        this(name, delay, DurationTools.getTicks(interval));
    }

    /**
     * @return True if the task is running, false otherwise.
     */
    public boolean isRunning()
    {
        return !isCancelled();
    }

    /**
     * @return The name of the task.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return True if the task is repeating, false otherwise.
     */
    public boolean isRepeating()
    {
        return this.interval > 0L;
    }

    /**
     * @return True if the task is delayed, false otherwise.
     */
    public boolean isDelayed()
    {
        return this.delay > 0L;
    }

    /**
     * @return The interval between each task execution.
     */
    public long getInterval()
    {
        return interval;
    }

    /**
     * @return The initial delay before the first execution of this task.
     */
    public long getDelay()
    {
        return delay;
    }
}
