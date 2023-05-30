package me.totalfreedom.base;

import me.totalfreedom.event.EventBus;
import me.totalfreedom.service.FreedomExecutor;
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
        getRegistrations().getServiceRegistry()
                          .stopAll();
        getRegistrations().getServiceRegistry()
                          .unregister(EventBus.class, eventBus);
    }

    @Override
    public void onEnable()
    {
        getRegistrations().getServiceRegistry()
                          .register(this, eventBus);
        getExecutor().getSync()
                     .execute(() -> getRegistrations()
                                        .getServiceRegistry()
                                        .startAll());
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
