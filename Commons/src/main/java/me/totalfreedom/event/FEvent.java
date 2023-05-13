package me.totalfreedom.event;

import me.totalfreedom.api.Context;

public abstract class FEvent
{
    protected FEvent()
    {
    }

    public abstract void call(Context<?>... contexts);

    public abstract void cancel();
}
