package me.totalfreedom.base;

import me.totalfreedom.event.EventBus;
import me.totalfreedom.service.FreedomExecutor;
import me.totalfreedom.service.SubscriptionProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CommonsBase extends JavaPlugin
{
    private final EventBus eventBus = new EventBus(this);
    private final Registration registration = new Registration();
    private final FreedomExecutor executor = new FreedomExecutor();

    public static CommonsBase getInstance()
    {
        return JavaPlugin.getPlugin(CommonsBase.class);
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler().runTaskLater(this, () -> getRegistrations()
                .getServiceRegistry()
                .stopAllServices(), 1L);

        getRegistrations().getServiceRegistry()
                          .unregisterService(EventBus.class);
    }

    @Override
    public void onEnable()
    {
        getRegistrations().getServiceRegistry()
                          .registerService(SubscriptionProvider.asyncService(this, eventBus));
        getExecutor().getSync()
                     .execute(() -> getRegistrations()
                                        .getServiceRegistry()
                                        .startAllServices());
    }

    public FreedomExecutor getExecutor()
    {
        return executor;
    }

    public Registration getRegistrations()
    {
        return registration;
    }

    public EventBus getEventBus()
    {
        return eventBus;
    }
}
