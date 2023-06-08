package me.totalfreedom.data;

import me.totalfreedom.config.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry for all the configurations.
 */
public class ConfigRegistry
{
    /**
     * A map of all the configurations.
     */
    private final Map<String, Configuration> configurationList = new HashMap<>();

    /**
     * Registers a configuration.
     * @param name The name of the configuration.
     * @param configuration The configuration.
     */
    public void register(final String name, final Configuration configuration)
    {
        configurationList.put(name, configuration);
    }

    /**
     * Unregisters a configuration.
     * @param name The name of the configuration.
     */
    public void unregister(final String name)
    {
        configurationList.remove(name);
    }

    /**
     * Gets a configuration.
     * @param name The name of the configuration.
     * @return The configuration.
     */
    public Configuration getConfiguration(final String name)
    {
        return configurationList.get(name);
    }
}
