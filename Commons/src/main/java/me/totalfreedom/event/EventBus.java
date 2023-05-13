package me.totalfreedom.event;

import me.totalfreedom.base.CommonsBase;
import org.bukkit.event.Listener;

import java.lang.reflect.Executable;
import java.util.*;
import java.util.stream.Collectors;

public class EventBus
{
    private final Set<Listener> listenerSet = new HashSet<>();
    private final Map<Listener, Set<FEvent>> listenerEventMap = new HashMap<>();
    private final CommonsBase plugin;

    public EventBus(CommonsBase plugin)
    {
        this.plugin = plugin;
    }

    void registerListener(Listener listener)
    {
        Set<FEvent> eventSet = Arrays.stream(listener.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Handler.class))
                .map(Executable::getParameters)
                .filter(p -> p.length == 1)
                .filter(p -> FEvent.class.isAssignableFrom(p[0].getType()))
                .map(p ->
                {
                    try
                    {
                        return (FEvent) p[0].getType().getDeclaredConstructor().newInstance();
                    } catch (Exception exception)
                    {
                        exception.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        listenerEventMap.put(listener, eventSet);
    }

    void unregisterListener(Listener listener)
    {
        listenerEventMap.remove(listener);
    }

    public void startListening()
    {
        listenerSet().forEach(this::registerListener);
    }

    public void stopListening()
    {
        listenerSet().forEach(this::unregisterListener);
    }

    public Set<Listener> listenerSet()
    {
        return listenerSet;
    }

    public Map<Listener, Set<FEvent>> listenerEventMap()
    {
        return listenerEventMap;
    }

    public CommonsBase getCommonsBase()
    {
        return plugin;
    }
}
