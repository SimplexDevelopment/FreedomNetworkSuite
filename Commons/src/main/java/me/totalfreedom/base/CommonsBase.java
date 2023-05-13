package me.totalfreedom.base;

import me.totalfreedom.event.EventBus;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class CommonsBase extends JavaPlugin
{
    private final EventBus eventBus = new EventBus(this);

    public static CommonsBase getInstance()
    {
        return JavaPlugin.getPlugin(CommonsBase.class);
    }

    @Override
    public void onEnable()
    {
        Bukkit.getServicesManager().register(EventBus.class,
                eventBus,
                this,
                ServicePriority.High);
    }

    @Override
    public void onDisable()
    {

    }

    public RegisteredServiceProvider<EventBus> getEventBus()
    {
        return Bukkit.getServicesManager().getRegistration(EventBus.class);
    }
}
