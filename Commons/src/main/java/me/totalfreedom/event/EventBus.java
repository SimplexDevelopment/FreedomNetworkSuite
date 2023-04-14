package me.totalfreedom.event;

import me.totalfreedom.base.CommonsBase;
import me.totalfreedom.base.CommonsJavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class EventBus
{
    private final Set<Listener> listenerSet = new HashSet<>();
    private final Map<Listener, Set<FEvent>> listenerEventMap = new HashMap<>();
    private final CommonsBase plugin;

    public EventBus(CommonsBase plugin) {
        this.plugin = plugin;
    }

    void registerListener(Listener listener) {
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
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        listenerEventMap.put(listener, eventSet);
    }

    void unregisterListener(Listener listener) {
        listenerEventMap.remove(listener);
    }

    public void startListening() {
        listenerSet().forEach(this::registerListener);
    }

    public void stopListening() {
        listenerSet().forEach(this::unregisterListener);
    }

    public Set<Listener> listenerSet() {
        return listenerSet;
    }
}
