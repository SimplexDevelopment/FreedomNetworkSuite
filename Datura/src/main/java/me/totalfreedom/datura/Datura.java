package me.totalfreedom.datura;

import me.totalfreedom.base.CommonsBase;
import org.bukkit.plugin.java.JavaPlugin;

public class Datura extends JavaPlugin
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
