package me.totalfreedom.service;

import me.totalfreedom.base.CommonsBase;

public abstract class Service
{
    private final String name;
    private boolean isActive = false;

    protected Service(final String name)
    {
        this.name = name;

    }

    public void start()
    {
        isActive = true;
        CommonsBase.getInstance()
                .getExecutor()
                .getSync()
                .execute(() ->
                {
                    while (isActive)
                    {
                        tick();
                    }
                });
    }

    public void stop()
    {
        isActive = false;
    }

    public abstract void tick();

    public String getName()
    {
        return name;
    }

    public boolean isActive()
    {
        return isActive;
    }
}
