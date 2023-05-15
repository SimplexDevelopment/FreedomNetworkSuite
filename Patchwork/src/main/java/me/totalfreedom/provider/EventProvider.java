package me.totalfreedom.provider;

import me.totalfreedom.event.FEvent;

@FunctionalInterface
public interface EventProvider<T extends FEvent>
{
    T getEvent();
}
