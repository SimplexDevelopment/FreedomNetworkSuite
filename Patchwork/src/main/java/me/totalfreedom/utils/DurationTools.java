package me.totalfreedom.utils;

import java.time.Duration;

public final class DurationTools
{
    // One tick is 1/20th of a second which is about 50ms.
    public static final Duration TICK = Duration.ofMillis(50L);
    // One second is 20 ticks.
    public static final Duration SECOND = TICK.multipliedBy(20L);
    // One minute is 60 seconds.
    public static final Duration MINUTE = SECOND.multipliedBy(60L);

    private DurationTools()
    {
        throw new AssertionError();
    }

    public static final long getTicks(final Duration duration)
    {
        return duration.toMillis() / 50L;
    }

    public static final Duration getTickedSeconds(final long seconds)
    {
        return SECOND.multipliedBy(seconds);
    }
}
