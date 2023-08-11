package fns.patchwork.provider;

import org.bukkit.plugin.java.JavaPlugin;

@FunctionalInterface
public interface ModuleProvider<T extends JavaPlugin>
{
    T getModule();
}
