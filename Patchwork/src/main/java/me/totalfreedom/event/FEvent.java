package me.totalfreedom.event;

import me.totalfreedom.api.Context;

public abstract class FEvent
{
    private boolean isCancelled;

    protected FEvent()
    {
        this.isCancelled = false;
    }

    public abstract void call(Callback<FEvent> callback);

    public boolean cancel()
    {
        this.isCancelled = true;
        return isCancelled();
    }

    public boolean isCancelled()
    {
        return isCancelled;
    }

    public abstract Class<? extends FEvent> getEventClass();
}
