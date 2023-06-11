package me.totalfreedom.base;

import me.totalfreedom.provider.ModuleProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Shortcuts
{
    private Shortcuts() {
        throw new AssertionError();
    }

    public static <T extends JavaPlugin> ModuleProvider<T> provideModule(final Class<T> pluginClass) {
        return CommonsBase.getInstance().getRegistrations().getModuleRegistry().getProvider(pluginClass);
    }
}
