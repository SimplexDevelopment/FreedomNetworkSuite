package fns.patchwork.data;

import fns.patchwork.event.FEvent;
import fns.patchwork.provider.EventProvider;
import java.util.ArrayList;
import java.util.List;

/**
 * A registry for {@link FEvent}s.
 */
public class EventRegistry
{
    /**
     * The list of events.
     */
    private final List<FEvent> events;

    /**
     * Creates a new event registry.
     */
    public EventRegistry()
    {
        this.events = new ArrayList<>();
    }

    /**
     * Registers an event.
     *
     * @param event The event to register.
     */
    public void register(final FEvent event)
    {
        this.events.add(event);
    }

    /**
     * Unregisters an event.
     *
     * @param event The event to unregister.
     */
    public void unregister(final FEvent event)
    {
        this.events.remove(event);
    }

    /**
     * Gets an {@link EventProvider} for the specified event class which contains the actual {@link FEvent} instance.
     *
     * @param clazz The event class.
     * @param <T>   The event type.
     * @return The event provider.
     */
    public <T extends FEvent> EventProvider<T> getEvent(final Class<T> clazz)
    {
        for (final FEvent event : this.events)
        {
            if (clazz.isInstance(event))
            {
                return () -> clazz.cast(event);
            }
        }
        return null;
    }
}
