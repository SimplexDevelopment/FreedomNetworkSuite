package me.totalfreedom.data;

import me.totalfreedom.provider.ModuleProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ModuleRegistry
{
    private final List<JavaPlugin> plugins;

    public ModuleRegistry()
    {
        this.plugins = new ArrayList<>();
    }

    public void addModule(final JavaPlugin plugin)
    {
        if (this.plugins.contains(plugin))
        {
            return;
        }
        this.plugins.add(plugin);
    }

    public void removeModule(final JavaPlugin plugin) {
        this.plugins.remove(plugin);
    }

    @SuppressWarnings("unchecked")
    public <T extends JavaPlugin> ModuleProvider<T> getModule(final Class<T> clazz)
    {
        for (final JavaPlugin plugin : plugins)
        {
            if (clazz.isInstance(plugin))
            {
                return () -> (T) plugin;
            }
        }

        return () -> null;
    }
}
