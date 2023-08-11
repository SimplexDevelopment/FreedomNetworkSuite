package fns.corvo;

import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Registration;
import org.bukkit.plugin.java.JavaPlugin;

public class Corvo extends JavaPlugin
{
    @Override
    public void onDisable()
    {
        Registration.getModuleRegistry()
                 .removeModule(this);
    }

    @Override
    public void onEnable()
    {
        Registration.getModuleRegistry()
                 .addModule(this);
    }
}
