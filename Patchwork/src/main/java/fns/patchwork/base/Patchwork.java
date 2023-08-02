package fns.patchwork.base;

import fns.patchwork.display.adminchat.AdminChatDisplay;
import fns.patchwork.event.EventBus;
import fns.patchwork.service.FreedomExecutor;
import fns.patchwork.service.SubscriptionProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The base class for Patchwork.
 */
public class Patchwork extends JavaPlugin
{
    /**
     * The {@link EventBus} for this plugin.
     */
    private final EventBus eventBus = new EventBus(this);
    /**
     * The {@link Registration} object for this plugin.
     */
    private final Registration registration = new Registration();
    /**
     * The {@link FreedomExecutor} for this plugin.
     */
    private final FreedomExecutor executor = new FreedomExecutor();
    /**
     * The {@link AdminChatDisplay} for this plugin.
     */
    private final AdminChatDisplay acdisplay = new AdminChatDisplay();

    /**
     * Provides this plugin instance through a safe static method. This is effectively the same thing as using
     * {@link JavaPlugin#getPlugin(Class)}
     *
     * @return the plugin instance
     */
    public static Patchwork getInstance()
    {
        return JavaPlugin.getPlugin(Patchwork.class);
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler()
              .runTaskLater(this, () -> getRegistrations()
                      .getServiceTaskRegistry()
                      .stopAllServices(), 1L);

        getRegistrations().getServiceTaskRegistry()
                          .unregisterService(EventBus.class);
    }

    @Override
    public void onEnable()
    {
        getRegistrations().getServiceTaskRegistry()
                          .registerService(SubscriptionProvider.asyncService(this, eventBus));
        getExecutor().getSync()
                     .execute(() -> getRegistrations()
                             .getServiceTaskRegistry()
                             .startAllServices());
    }

    /**
     * Gets the {@link FreedomExecutor} for this plugin.
     *
     * @return the {@link FreedomExecutor}
     */
    public FreedomExecutor getExecutor()
    {
        return executor;
    }

    /**
     * Get's the Registration object for this plugin. This object contains every registry class for the various features
     * provided by this plugin.
     *
     * @return the Registration object
     */
    public Registration getRegistrations()
    {
        return registration;
    }

    /**
     * Gets the {@link EventBus} for this plugin. The EventBus is used to register and listen to custom events provided
     * by Freedom Network Suite.
     *
     * @return the {@link EventBus}
     */
    public EventBus getEventBus()
    {
        return eventBus;
    }

    /**
     * Gets the {@link AdminChatDisplay} for this plugin. The AdminChatDisplay is used to display messages sent in
     * adminchat.
     *
     * @return the {@link AdminChatDisplay}
     */
    public AdminChatDisplay getAdminChatDisplay()
    {
        return acdisplay;
    }
}
