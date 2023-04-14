package me.totalfreedom.base;

import me.totalfreedom.event.EventBus;
import me.totalfreedom.module.Module;
import me.totalfreedom.utils.Identity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class CommonsBase implements Module<CommonsBase>
{
    private final EventBus eventBus = new EventBus(this);


    @Override
    public void enable()
    {
        Bukkit.getServicesManager().register(EventBus.class,
                eventBus,
                JavaPlugin.getPlugin(CommonsJavaPlugin.class),
                ServicePriority.High);
    }

    @Override
    public void disable()
    {

    }

    @Override
    public Identity getIdentity()
    {
        return null;
    }

    @Override
    public Class<CommonsBase> getRuntimeClass()
    {
        return CommonsBase.class;
    }

    @Override
    public CommonsBase getRuntimeInstance()
    {
        return this;
    }

    public RegisteredServiceProvider<EventBus> getEventBus() {
        return Bukkit.getServicesManager().getRegistration(EventBus.class);
    }
}
