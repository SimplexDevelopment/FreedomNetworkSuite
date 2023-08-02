package fns.fossil;

import fns.fossil.trail.Trailer;
import fns.patchwork.base.Patchwork;
import fns.patchwork.base.Registration;
import fns.patchwork.service.SubscriptionProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Fossil extends JavaPlugin
{
    private final Trailer trailer = new Trailer();
    private final Registration registration = Patchwork.getInstance()
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
