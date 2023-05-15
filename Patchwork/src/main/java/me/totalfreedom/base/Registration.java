package me.totalfreedom.base;

import me.totalfreedom.data.*;

public class Registration
{
    private final CommandRegistry commandRegistry;
    private final EventRegistry eventRegistry;
    private final UserRegistry userRegistry;
    private final ServiceRegistry serviceRegistry;
    private final ModuleRegistry moduleRegistry;
    private final GroupRegistry groupRegistry;

    public Registration()
    {
        this.commandRegistry = new CommandRegistry();
        this.eventRegistry = new EventRegistry();
        this.userRegistry = new UserRegistry();
        this.serviceRegistry = new ServiceRegistry();
        this.moduleRegistry = new ModuleRegistry();
        this.groupRegistry = new GroupRegistry();
    }

    public ModuleRegistry getModuleRegistry()
    {
        return moduleRegistry;
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

    public GroupRegistry getGroupRegistry()
    {
        return groupRegistry;
    }
}
