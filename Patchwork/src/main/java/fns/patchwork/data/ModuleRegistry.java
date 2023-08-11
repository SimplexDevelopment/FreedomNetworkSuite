package fns.patchwork.data;

import fns.patchwork.provider.ModuleProvider;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A registry for modules.
 */
public class ModuleRegistry
{
    /**
     * The list of modules.
     */
    private final List<JavaPlugin> plugins;

    /**
     * Creates a new module registry.
     */
    public ModuleRegistry()
    {
        this.plugins = new ArrayList<>();
    }

    /**
     * Adds a module to the registry.
     *
     * @param plugin The module to add.
     */
    public void addModule(final JavaPlugin plugin)
    {
        if (this.plugins.contains(plugin))
        {
            return;
        }
        this.plugins.add(plugin);
    }

    /**
     * Removes a module from the registry.
     *
     * @param plugin The module to remove.
     */
    public void removeModule(final JavaPlugin plugin)
    {
        this.plugins.remove(plugin);
    }

    /**
     * Gets a module from the registry wrapped in a {@link ModuleProvider}.
     *
     * @param clazz The class of the module.
     * @param <T>   The type of the module.
     * @return The module.
     */
    public <T extends JavaPlugin> ModuleProvider<T> getProvider(final Class<T> clazz)
    {
        for (final JavaPlugin plugin : plugins)
        {
            if (clazz.isInstance(plugin))
            {
                return () -> clazz.cast(plugin);
            }
        }

        return () -> null;
    }
}
