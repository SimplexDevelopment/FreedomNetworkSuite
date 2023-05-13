package me.totalfreedom.data;

import me.totalfreedom.service.Service;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

import java.util.ArrayList;
import java.util.List;

public class ServiceRegistry
{
    private final List<Service> services;

    public ServiceRegistry()
    {
        this.services = new ArrayList<>();
    }

    public void startAll()
    {
        for (Service service : this.services)
        {
            service.start();
        }
    }

    public void stopAll()
    {
        for (Service service : this.services)
        {
            service.stop();
        }
    }

    @SuppressWarnings("unchecked")
    // Suppressing is fine here; we know that the service is of type T extends Service,
    // and calling getClass() on this object would effectively be Class<T>, though we may lose
    // the identity of the code signature in the process.
    // In this case, that is fine.
    public <T extends Service> void register(Plugin plugin, final T service)
    {
        this.services.add(service);
        if (!service.getClass().isInstance(service))
        {
            throw new UnknownError("""
                    A critical issue has been encountered:
                    The service %s is not an instance of itself.
                    This is a critical issue and should be reported immediately.
                    """.formatted(service.getClass().getName()));
        }
        Bukkit.getServicesManager().register(
                (Class<T>) service.getClass(),
                service,
                plugin,
                ServicePriority.Normal);
    }

    public <T extends Service> RegisteredServiceProvider<T> getService(Class<T> clazz)
    {
        return Bukkit.getServicesManager().getRegistration(clazz);
    }
}
