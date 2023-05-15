package me.totalfreedom.corvo;

import me.totalfreedom.base.CommonsBase;
import org.bukkit.plugin.java.JavaPlugin;

public class Corvo extends JavaPlugin
{
    @Override
    public void onEnable() {
        CommonsBase.getInstance()
                .getRegistrations()
                .getModuleRegistry()
                .addModule(this);
    }

    @Override
    public void onDisable() {
        CommonsBase.getInstance()
                .getRegistrations()
                .getModuleRegistry()
                .removeModule(this);
    }
}
