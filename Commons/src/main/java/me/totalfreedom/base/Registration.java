package me.totalfreedom.base;

import me.totalfreedom.data.CommandRegistry;
import me.totalfreedom.data.EventRegistry;
import me.totalfreedom.data.ServiceRegistry;
import me.totalfreedom.data.UserRegistry;

public class Registration
{
    private final CommandRegistry commandRegistry;
    private final EventRegistry eventRegistry;
    private final UserRegistry userRegistry;
    private final ServiceRegistry serviceRegistry;

    public Registration()
    {
        this.commandRegistry = new CommandRegistry();
        this.eventRegistry = new EventRegistry();
        this.userRegistry = new UserRegistry();
        this.serviceRegistry = new ServiceRegistry();
    }

    public CommandRegistry getCommandRegistry()
    {
        return commandRegistry;
    }

    public EventRegistry getEventRegistry()
    {
        return eventRegistry;
    }

    public UserRegistry getUserRegistry()
    {
        return userRegistry;
    }

    public ServiceRegistry getServiceRegistry()
    {
        return serviceRegistry;
    }
}
