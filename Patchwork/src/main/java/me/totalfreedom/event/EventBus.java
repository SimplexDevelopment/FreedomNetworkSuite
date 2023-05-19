package me.totalfreedom.event;

import me.totalfreedom.api.Context;
import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.service.Service;

import java.util.HashSet;
import java.util.Set;

public class EventBus extends Service
{
    private final CommonsBase plugin;
    private final Set<FEvent> eventSet = new HashSet<>();
    private final SubscriptionBox<?> runningSubscriptions = new SubscriptionBox<>();

    public EventBus(CommonsBase plugin)
    {
        super("event_bus");
        this.plugin = plugin;
    }

    public void addEvent(FEvent event)
    {
        eventSet.add(event);
    }

    public <T extends FEvent> T getEvent(Class<T> eventClass)
    {
        FEvent e = eventSet.stream()
                .filter(event -> event.getEventClass().equals(eventClass))
                .findFirst()
                .orElse(null);

        return eventClass.cast(e);
    }

    public <T extends FEvent> EventSubscription<T> subscribe(Class<T> eventClass, Callback<T> callback)
    {
        Context<T> eventContext = () -> eventSet.stream()
                .filter(event -> event.getEventClass().equals(eventClass))
                .findFirst()
                .map(eventClass::cast)
                .orElse(null);

        if (eventContext.get() == null)
        {
            throw new IllegalArgumentException("Event class " + eventClass.getName() + " is not registered.");
        }

        return new EventSubscription<>(eventContext.get(), callback);
    }

    public void unsubscribe(EventSubscription<?> subscription)
    {
        runningSubscriptions.removeSubscription(subscription);
    }

    public CommonsBase getCommonsBase()
    {
        return plugin;
    }

    @Override
    public void tick()
    {
        runningSubscriptions.tick();
    }
}
