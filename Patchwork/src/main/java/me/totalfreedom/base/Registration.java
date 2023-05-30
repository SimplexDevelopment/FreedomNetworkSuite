package me.totalfreedom.base;

import me.totalfreedom.data.ConfigRegistry;
import me.totalfreedom.data.EventRegistry;
import me.totalfreedom.data.GroupRegistry;
import me.totalfreedom.data.ModuleRegistry;
import me.totalfreedom.data.ServiceRegistry;
import me.totalfreedom.data.UserRegistry;

public class Registration
{
    private final EventRegistry eventRegistry;
    private final UserRegistry userRegistry;
    private final ServiceRegistry serviceRegistry;
    private final ModuleRegistry moduleRegistry;
    private final GroupRegistry groupRegistry;
    private final ConfigRegistry configRegistry;

    public Registration()
    {
        this.eventRegistry = new EventRegistry();
        this.userRegistry = new UserRegistry();
        this.serviceRegistry = new ServiceRegistry();
        this.moduleRegistry = new ModuleRegistry();
        this.groupRegistry = new GroupRegistry();
        this.configRegistry = new ConfigRegistry();
    }

    public ModuleRegistry getModuleRegistry()
    {
        return moduleRegistry;
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

    public ConfigRegistry getConfigRegistry()
    {
        return configRegistry;
    }
}
