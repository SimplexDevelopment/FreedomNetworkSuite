package me.totalfreedom.data;

import me.totalfreedom.event.FEvent;
import me.totalfreedom.provider.EventProvider;

import java.util.ArrayList;
import java.util.List;

public class EventRegistry
{
    private final List<FEvent> events;

    public EventRegistry()
    {
        this.events = new ArrayList<>();
    }

    public void register(final FEvent event)
    {
        this.events.add(event);
    }

    public void unregister(final FEvent event)
    {
        this.events.remove(event);
    }

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
