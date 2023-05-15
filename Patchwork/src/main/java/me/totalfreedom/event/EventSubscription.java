package me.totalfreedom.event;

import me.totalfreedom.api.Context;

import java.util.function.Supplier;

public final class EventSubscription<T extends FEvent>
{
    private final T event;
    private final Callback<T> callback;

    public EventSubscription(T event, Callback<T> callback)
    {
        this.event = event;
        this.callback = callback;
    }

    public T getEvent()
    {
        return event;
    }

    public boolean cancel()
    {
        return getEvent().cancel();
    }

    public boolean isCancelled()
    {
        return getEvent().isCancelled();
    }

    public Callback<T> getCallback() {
        return callback;
    }
}
