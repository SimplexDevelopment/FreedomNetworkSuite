package me.totalfreedom.api;

import java.util.function.Consumer;

public interface Interruptable
{
    boolean canInterrupt();

    void interrupt(Consumer<Throwable> callback);
}
