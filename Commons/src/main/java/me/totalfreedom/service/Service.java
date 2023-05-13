package me.totalfreedom.service;

public abstract class Service
{
    private final String name;

    protected Service(String name)
    {
        this.name = name;

    }

    public abstract void start();

    public abstract void stop();
}
