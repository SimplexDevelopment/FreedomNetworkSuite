package me.totalfreedom.fossil;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.base.Registration;
import me.totalfreedom.fossil.trail.Trailer;
import me.totalfreedom.service.SubscriptionProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Fossil extends JavaPlugin
{
    private final Trailer trailer = new Trailer();
    private final Registration registration = CommonsBase.getInstance()
                                                         .getRegistrations();

    @Override
    public void onEnable()
    {
        registration.getModuleRegistry()
                    .addModule(this);

        registration.getServiceTaskRegistry()
                    .registerService(
                        SubscriptionProvider.syncService(this, trailer));
    }
}
