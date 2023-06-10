package me.totalfreedom.base;

import me.totalfreedom.data.ConfigRegistry;
import me.totalfreedom.data.EventRegistry;
import me.totalfreedom.data.GroupRegistry;
import me.totalfreedom.data.ModuleRegistry;
import me.totalfreedom.data.ServiceTaskRegistry;
import me.totalfreedom.data.UserRegistry;

/**
 * This class is a holder for each registry in the data package.
 * <br>
 * Registries such as {@link ModuleRegistry} and {@link ServiceTaskRegistry} can be found as final objects in this
 * class. These registries should only ever be accessed through the single Registration object in CommonsBase using
 * {@link CommonsBase#getRegistrations()}
 */
public class Registration
{
    /**
     * The {@link EventRegistry}
     */
    private final EventRegistry eventRegistry;
    /**
     * The {@link UserRegistry}
     */
    private final UserRegistry userRegistry;
    /**
     * The {@link ServiceTaskRegistry}
     */
    private final ServiceTaskRegistry serviceTaskRegistry;
    /**
     * The {@link ModuleRegistry}
     */
    private final ModuleRegistry moduleRegistry;
    /**
     * The {@link GroupRegistry}
     */
    private final GroupRegistry groupRegistry;
    /**
     * The {@link ConfigRegistry}
     */
    private final ConfigRegistry configRegistry;

    /**
     * Constructs a new Registration object and initializes all registries.
     */
    Registration()
    {
        this.eventRegistry = new EventRegistry();
        this.userRegistry = new UserRegistry();
        this.serviceTaskRegistry = new ServiceTaskRegistry();
        this.moduleRegistry = new ModuleRegistry();
        this.groupRegistry = new GroupRegistry();
        this.configRegistry = new ConfigRegistry();
    }

    /**
     * @return The {@link ModuleRegistry}
     */
    public ModuleRegistry getModuleRegistry()
    {
        return moduleRegistry;
    }

    /**
     * @return The {@link EventRegistry}
     */
    public EventRegistry getEventRegistry()
    {
        return eventRegistry;
    }

    /**
     * @return The {@link UserRegistry}
     */
    public UserRegistry getUserRegistry()
    {
        return userRegistry;
    }

    /**
     * @return The {@link ServiceTaskRegistry}
     */
    public ServiceTaskRegistry getServiceTaskRegistry()
    {
        return serviceTaskRegistry;
    }

    /**
     * @return The {@link GroupRegistry}
     */
    public GroupRegistry getGroupRegistry()
    {
        return groupRegistry;
    }

    /**
     * @return The {@link ConfigRegistry}
     */
    public ConfigRegistry getConfigRegistry()
    {
        return configRegistry;
    }
}
