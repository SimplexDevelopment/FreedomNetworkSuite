package me.totalfreedom.service;

public abstract class Service
{
    private final String name;

    protected Service(final String name)
    {
        this.name = name;
    }

    public abstract void tick();

    public String getName()
    {
        return name;
    }
}
