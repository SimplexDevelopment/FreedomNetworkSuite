package fns.patchwork.provider;

import fns.patchwork.event.FEvent;

@FunctionalInterface
public interface EventProvider<T extends FEvent>
{
    T getEvent();
}
