package me.totalfreedom.event;

@FunctionalInterface
public interface Callback<T extends FEvent>
{
    void call(T event);
}
