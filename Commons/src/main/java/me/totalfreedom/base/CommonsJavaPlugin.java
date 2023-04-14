package me.totalfreedom.base;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class CommonsJavaPlugin extends JavaPlugin
{
    private final File moduleFolder = new File(getDataFolder(), "modules");

    @Override
    public void onEnable()
    {

    }

    @Override
    public void onDisable()
    {
        Registration.getInstance()
                .getModuleRegistry()
                .unloadModules(moduleFolder);
    }
}
