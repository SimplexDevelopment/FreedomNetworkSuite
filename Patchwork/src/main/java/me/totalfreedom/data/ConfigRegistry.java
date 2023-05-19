package me.totalfreedom.data;

import me.totalfreedom.config.Configuration;

import java.util.HashMap;
import java.util.Map;

public class ConfigRegistry
{
    private final Map<String, Configuration> configurationList = new HashMap<>();

    public void register(String name, Configuration configuration)
    {
        configurationList.put(name, configuration);
    }

    public void unregister(String name)
    {
        configurationList.remove(name);
    }

    public Configuration getConfiguration(String name)
    {
        return configurationList.get(name);
    }
}
