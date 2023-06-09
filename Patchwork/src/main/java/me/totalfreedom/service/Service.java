package me.totalfreedom.service;

/**
 * Represents a ticking service. Services may be asynchronous or synchronous, however there are some restrictions:
 * <ul>
 *     <li>Sync services may not have a complexity greater than 5.</li>
 *     <li>Async services may not interact with the Bukkit API in any form.</li>
 * </ul>
 */
public abstract class Service
{
    /**
     * The name of the service.
     */
    private final String name;

    /**
     * Creates a new service with the given name.
     *
     * @param name The name of the service.
     */
    protected Service(final String name)
    {
        this.name = name;
    }

    /**
     * This method is called every single tick, or at least, every tick interval described by the holding
     * {@link ServiceSubscription}. Since this runs every single tick, the body of this method should not have a
     * complexity higher than 5. This includes Cyclomatic, Cognitive, and NPath complexities. If the service is
     * asynchronous, there is a bit more flexibility with the complexity rating, extending to no more than 10. However,
     * it's generally good practice to keep the complexity of ticking services as low as possible to avoid extensive
     * resource consumption.
     *
     * @see ServiceSubscription
     * @see SubscriptionProvider
     */
    public abstract void tick();

    /**
     * @return The name of the service.
     */
    public String getName()
    {
        return name;
    }
}
