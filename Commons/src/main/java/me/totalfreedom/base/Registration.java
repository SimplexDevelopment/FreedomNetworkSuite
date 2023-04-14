package me.totalfreedom.base;

import me.totalfreedom.data.*;

public class Registration
{
    private static final Registration INSTANCE = new Registration();
    private final ModuleRegistry moduleRegistry;
    private final CommandRegistry commandRegistry;
    private final EventRegistry eventRegistry;
    private final UserRegistry userRegistry;
    private final ServiceRegistry serviceRegistry;

    private Registration() {
        this.commandRegistry = new CommandRegistry();
        this.eventRegistry = new EventRegistry();
        this.userRegistry = new UserRegistry();
        this.serviceRegistry = new ServiceRegistry();
        this.moduleRegistry = new ModuleRegistry();
    }

    public static Registration getInstance()
    {
        return INSTANCE;
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

    public CommonsBase getCommonsBase() {
        return getModuleRegistry().getModule(CommonsBase.class);
    }
}
