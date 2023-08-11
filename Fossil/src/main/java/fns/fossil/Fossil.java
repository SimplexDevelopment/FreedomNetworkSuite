package fns.fossil;

import fns.fossil.trail.Trailer;
import fns.patchwork.base.Registration;
import fns.patchwork.service.SubscriptionProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Fossil extends JavaPlugin
{
    private final Trailer trailer = new Trailer();
    @Override
    public void onEnable()
    {
        Registration.getServiceTaskRegistry()
                    .registerService(
                            SubscriptionProvider.syncService(this, trailer));

        Registration.getModuleRegistry()
                .addModule(this);
    }
}
