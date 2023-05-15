package me.totalfreedom.fossil;

import me.totalfreedom.base.CommonsBase;
import org.bukkit.plugin.java.JavaPlugin;

public class Fossil extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        CommonsBase.getInstance()
                .getRegistrations()
                .getModuleRegistry()
                .addModule(this);
    }
}
